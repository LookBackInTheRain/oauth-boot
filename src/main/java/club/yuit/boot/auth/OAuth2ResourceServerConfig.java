package club.yuit.boot.auth;

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
@Order(1)
public class OAuth2ResourceServerConfig  extends ResourceServerConfigurerAdapter{

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .authorizeRequests()
                .antMatchers("/login","/oauth/*")
                .permitAll()
                .antMatchers
                        ("/swagger-ui.html/**","/webjars/**",
                        "/swagger-resources/**","/v2/api-docs/**",
                        "/swagger-resources/configuration/ui/**","/swagger-resources/configuration/security/**",
                        "/images/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().csrf().disable();
    }
}
