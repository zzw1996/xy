package run.halo.starter.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ReactiveSettingFetcher;
import run.halo.app.plugin.SettingFetcher;
import run.halo.starter.entity.WxUser;

import java.util.Map;
@Component
@Service
@RequiredArgsConstructor
public class WxUserLoginServiceImpl implements WxUserLoginService {

    private String appid;
    private String appsecret;
    private String openid;

    private final ReactiveSettingFetcher settingFetcher;

    private final WebClient webClient = WebClient.create();

    @Override
    public Mono<Map<String, JsonNode>> getAppConfigs() {
        return this.settingFetcher.getValues();
    }

    @Override
    public Mono<JsonNode> getAppConfigsByGroupName(String groupName) {
        return this.settingFetcher.get(groupName);
    }

    @Override
    public Mono<WxUser> login(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=" + getAppConfigsByGroupName("wxconfig").flatMap(jsonNode -> Mono.just(jsonNode.get("appid").asText()))
                + "&secret=" + getAppConfigsByGroupName("wxconfig").flatMap(jsonNode -> Mono.just(jsonNode.get("appsecret").asText()))
                + "&code=" + code
                + "&grant_type=authorization_code";

        return webClient.get()
               .uri(url)
               .retrieve()
               .bodyToMono(Map.class)
               .flatMap(response -> {
                    if (response.containsKey("errcode")) {
                        return Mono.error(new RuntimeException("微信登录失败: " + response.get("errmsg")));
                    }
                    String accessToken = (String) response.get("access_token");
                    String openid = (String) response.get("openid");

                    String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo"
                            + "?access_token=" + accessToken
                            + "&openid=" + openid;

                    return webClient.get()
                           .uri(userInfoUrl)
                           .retrieve()
                           .bodyToMono(Map.class)
                           .map(userInfo -> {
                                WxUser wxUser = new WxUser();
                                wxUser.setOpenid((String) userInfo.get("openid"));
                                wxUser.setNickname((String) userInfo.get("nickname"));
                                wxUser.setAvatarUrl((String) userInfo.get("headimgurl"));
                                wxUser.setUnionid((String) userInfo.get("unionid"));
                                wxUser.setGender((String) userInfo.get("sex")); // 微信返回的性别字段可能是 'sex'
                                wxUser.setCity((String) userInfo.get("city"));
                                return wxUser;
                            });
                });
    }
}