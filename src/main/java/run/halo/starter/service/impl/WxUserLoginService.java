package run.halo.starter.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import run.halo.starter.entity.WxUser;

import reactor.core.publisher.Mono;
import java.util.Map;

public interface WxUserLoginService {
    Mono<WxUser> login(String code);

    Mono<Map<String, JsonNode>> getAppConfigs();

    Mono<JsonNode> getAppConfigsByGroupName(String groupName);
}