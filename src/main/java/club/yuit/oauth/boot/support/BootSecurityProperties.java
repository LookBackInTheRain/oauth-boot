package club.yuit.oauth.boot.support;

import club.yuit.oauth.boot.support.common.TokenStoreType;
import club.yuit.oauth.boot.support.properities.BootLogLevelProperties;
import club.yuit.oauth.boot.support.properities.BootSmsCodeProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuit
 * @date  2018/10/19 9:44
 *
 * 配置
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "boot.oauth")
public class BootSecurityProperties {



    /**
     * 定义token存储类型
     */
    private TokenStoreType tokenStoreType = TokenStoreType.memory;

    private String loginProcessUrl="/auth/authorize";


    /**
     * 日志输出等级，默认 INFO {@NestedConfigurationProperty} 生成嵌套类的配置元数据信息
     * 更友好的提示
     */
    @NestedConfigurationProperty
    private BootLogLevelProperties logging = new BootLogLevelProperties();

    @NestedConfigurationProperty
    private BootSmsCodeProperties sms =  new BootSmsCodeProperties();

    private String tokenSigningKey = "OAUTHBOOT@IUY09&098#UIOKNJJ-YUIT.CLUB";



    /*****--------------getter  setter----------------------******/
    public TokenStoreType getTokenStoreType() {
        return tokenStoreType;
    }

    public void setTokenStoreType(TokenStoreType tokenStoreType) {
        this.tokenStoreType = tokenStoreType;
    }

    public String getLoginProcessUrl() {
        return loginProcessUrl;
    }

    public void setLoginProcessUrl(String loginProcessUrl) {
        this.loginProcessUrl = loginProcessUrl;
    }

    public BootLogLevelProperties getLogging() {
        return logging;
    }

    public void setLogging(BootLogLevelProperties logging) {
        this.logging = logging;
    }

    public BootSmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(BootSmsCodeProperties sms) {
        this.sms = sms;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }


}
