package club.yuit.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author yuit
 * @create time 2018/10/9  15:10
 * @description 资源服务配置
 * @modify by
 * @modify time
 **/
@Configuration
@EnableResourceServer
public class OAuth2ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers()
                .anyRequest()
                .and()
                .anonymous()
                .and().anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/index")
                    .access("#oauth2.hasScope('select') and hasRole('USER')")
                .antMatchers("/other").authenticated().
                and().authorizeRequests().antMatchers("/oauth/*").permitAll();;
    }
}
