package club.yuit.oauth.boot.config.auth2;

import club.yuit.oauth.boot.support.oauth2.BootAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthenticationEntryPoint point;

    @Autowired
    private BootAccessDeniedHandler handler;

    @Autowired
    private TokenStore tokenStore;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

        resources.tokenStore(tokenStore)
                .resourceId("boot-server");

        resources.authenticationEntryPoint(point).accessDeniedHandler(handler);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/favicon.ico")
                    .permitAll()
                    .anyRequest()
                    .access("#oauth2.hasAnyScope('all','select')");
    }



}
