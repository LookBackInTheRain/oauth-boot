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
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/*","/index").permitAll().and().csrf().disable();


    }
}
