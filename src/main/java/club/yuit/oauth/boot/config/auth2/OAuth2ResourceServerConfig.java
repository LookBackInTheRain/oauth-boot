package club.yuit.oauth.boot.config.auth2;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.oauth2.BootAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;


/**
 * @author yuit
 * @date  2018/10/15  14:57
 * 资源服务配置
 **/
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig  extends ResourceServerConfigurerAdapter{

    private AuthenticationEntryPoint point;
    private BootAccessDeniedHandler handler;
    private TokenStore tokenStore;
    private BootSecurityProperties properties;

    public OAuth2ResourceServerConfig(AuthenticationEntryPoint point, BootAccessDeniedHandler handler, TokenStore tokenStore, BootSecurityProperties properties) {
        this.point = point;
        this.handler = handler;
        this.tokenStore = tokenStore;
        this.properties = properties;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.tokenStore(tokenStore)
                .resourceId("boot-server");

        resources.authenticationEntryPoint(point).accessDeniedHandler(handler);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                // Allows restricting access based upon the {@link HttpServletRequest} using
                .authorizeRequests()
                    .antMatchers("/favicon.ico","/oauth/**",
                            properties.getBaseLogin().getLoginProcessUrl(),
                            properties.getLoginPage(),
                            properties.getSmsLogin().getLoginProcessUrl())
                    .permitAll()
                    .anyRequest().permitAll();
                    //.access("#oauth2.hasAnyScope('all','select')");
    }



}
