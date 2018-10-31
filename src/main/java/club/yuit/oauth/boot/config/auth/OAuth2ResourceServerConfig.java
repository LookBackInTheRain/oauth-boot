package club.yuit.oauth.boot.config.auth;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


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

    @Autowired
    private BootSecurityProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/auth/authorize").permitAll()
                    .anyRequest()
                    .authenticated();

        http.formLogin().loginProcessingUrl("/auth/authorize");
    }

}
