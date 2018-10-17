package club.yuit.boot.auth;

import club.yuit.boot.auth.support.BootUserDetailService;
import club.yuit.boot.auth.support.handler.BootLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private BootLoginSuccessHandler bootLoginSuccessHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 登录成功处理
                .successHandler(bootLoginSuccessHandler)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**","/login").permitAll()
                .anyRequest().authenticated();
    }
}
