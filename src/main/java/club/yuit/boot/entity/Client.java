package club.yuit.boot.entity;

import club.yuit.boot.utils.CommonUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yuit
 * @create time 2018/10/16  9:23
 * @description
 * @modify by
 * @modify time
 **/
@Data
@TableName("clients")
public class Client  {

    @TableId
    private String id;
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
