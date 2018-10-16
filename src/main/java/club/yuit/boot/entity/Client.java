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
public class Client implements ClientDetails {

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

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return  CommonUtils.transformStringToSet(this.resourceIds,String.class);
    }

    @Override
    public boolean isSecretRequired() {
        return this.isSecretRequired;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.isScoped;
    }

    @Override
    public Set<String> getScope() {
        return  CommonUtils.transformStringToSet(this.scope,String.class);
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return CommonUtils.transformStringToSet(this.authorizedGrantTypes,String.class);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return  CommonUtils.transformStringToSet(this.registeredRedirectUri,String.class);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return (this.authorities!=null&&this.authorities.trim().length()>0)
                ?AuthorityUtils.commaSeparatedStringToAuthorityList(this.authorities):null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return this.isAutoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
