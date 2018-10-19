package club.yuit.oauth.boot.support;

import club.yuit.oauth.boot.support.common.TokenStoreType;
import club.yuit.oauth.boot.support.properities.BootLogLevelProperties;
import club.yuit.oauth.boot.support.properities.BootSmsCodeProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @auther yuit
 * @create 2018/10/19 9:44
 * @description
 * @modify
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "boot.oauth")
@Getter @Setter
public class BootSecurityProperties {

    /**
     * 定义token存储类型
     */
    private TokenStoreType tokenStoreType = TokenStoreType.memory;

    /**
     * 日志输出等级，默认 INFO {@NestedConfigurationProperty} 生成嵌套类的配置元数据信息
     * 更友好的提示
     */
    @NestedConfigurationProperty
    private BootLogLevelProperties logging = new BootLogLevelProperties();

    @NestedConfigurationProperty
    private BootSmsCodeProperties sms =  new BootSmsCodeProperties();


}
