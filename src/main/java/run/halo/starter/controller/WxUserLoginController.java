package run.halo.starter.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ApiVersion;
import run.halo.starter.entity.WxUser;
import run.halo.starter.service.impl.WxUserLoginService;

@ApiVersion("xy.halo.run/v1alpha1")
@RestController
public class WxUserLoginController {
    private final WxUserLoginService wxUserLoginService;

    public WxUserLoginController(WxUserLoginService wxUserLoginService) {
        this.wxUserLoginService = wxUserLoginService;
    }

    @PostMapping("/wx/login")
    public Mono<WxUser> login(@RequestParam String code) {
        return wxUserLoginService.login(code);
    }
}