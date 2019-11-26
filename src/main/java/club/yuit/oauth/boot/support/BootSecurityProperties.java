package club.yuit.oauth.boot.support;

import club.yuit.oauth.boot.support.properities.*;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
public class BootSecurityProperties {


    /**
     * 登录页面
     */
    private String loginPage="/auth/login";

    /**
     * 获取验证码
     */
    private String codePath = "/auth/code";

    /**
     * 验证码存储位置 {session}，{redis}
     */
    private CodeStoreType codeStoreType = CodeStoreType.session;


    /**
     * 日志输出等级，默认 INFO {@NestedConfigurationProperty} 生成嵌套类的配置元数据信息
     * 更友好的提示
     */
    @NestedConfigurationProperty
    private BootLogLevelProperties logging = new BootLogLevelProperties();

    /**
     * 短信验证码登录相关配置
     */
    @NestedConfigurationProperty
    private BootSmsLoginProperties smsLogin =  new BootSmsLoginProperties();

    /**
     * 用户名密码登录相关配置
     */
    @NestedConfigurationProperty
    private BootBaseLoginProperties baseLogin = new BootBaseLoginProperties();

    /**
     * oauth2 相关配置
     */
    @NestedConfigurationProperty
    private BootOAuth2Properties oauth2 = new BootOAuth2Properties();



}
