package run.halo.starter.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import run.halo.app.extension.AbstractExtension;
import run.halo.app.extension.GVK;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@GVK(group = "xy.halo.run",
    version = "v1alpha1",
    kind = "WxUser",
    plural = "wxuser",
    singular = "wxuser")
public class WxUser extends AbstractExtension {
    @Schema(description = "微信用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
    private String openid;

    @Schema(description = "会话密钥")
    private String sessionKey;

    @Schema(description = "用户在开放平台的唯一标识符")
    private String unionid;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像URL")
    private String avatarUrl;

    @Schema(description = "用户性别")
    private String gender;

    @Schema(description = "用户所在城市")
    private String city;
}

