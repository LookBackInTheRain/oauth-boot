package club.yuit.oauth.boot.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author yuit
 * @create time 2018/10/15  14:57
 * @description
 * @modify by
 * @modify time
 **/
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig  extends ResourceServerConfigurerAdapter{





    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/authorize**","/oauth/confirm_access**")
                .authenticated()
                .antMatchers("/login","/oauth/token**","/client/register")
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }



}
