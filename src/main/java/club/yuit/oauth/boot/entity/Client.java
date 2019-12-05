package club.yuit.oauth.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author yuit
 * @date  2018/10/16  9:23
 *
 **/
@Data
@TableName("clients")
public class Client  {
    @TableId
    private String id;

    private String name;

    @TableField("clientId")
    @NotNull
    private String clientId;
    @TableField("resourceIds")
    private String resourceIds;
    @TableField("isSecretRequired")
    private Boolean isSecretRequired;
    @TableField("clientSecret")
    @NotNull
    private String clientSecret;
    @TableField("isScoped")
    private Boolean isScoped;
    @TableField("scope")
    private String scope;
    @TableField("authorizedGrantTypes")
    @NotNull
    private String authorizedGrantTypes;
    @TableField("registeredRedirectUri")
    @NotNull
    private String registeredRedirectUri;
    @TableField("authorities")
    private String authorities;
    @TableField("isAutoApprove")
    private Boolean isAutoApprove;
    @TableField("accessTokenValiditySeconds")
    @NotNull
    private Integer accessTokenValiditySeconds;
    @TableField("refreshTokenValiditySeconds")
    @NotNull
    private Integer refreshTokenValiditySeconds;

}
