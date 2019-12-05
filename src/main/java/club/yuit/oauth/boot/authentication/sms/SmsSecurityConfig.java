package club.yuit.oauth.boot.authentication.sms;

import club.yuit.oauth.boot.handler.BootLoginFailureHandler;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.BootSmsUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author yuit
 * @date 2019/11/26 9:27
 **/
@Configuration
public class SmsSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private SmsAuthenticationProvider authenticationProvider;
    private SmsCodeAuthenticationFilter authenticationFilter;
    private SmsCodeCheckFilter codeCheckFilter;


    public SmsSecurityConfig(BootSmsUserDetailService userDetailsService,
                             StringRedisTemplate redisTemplate,
                             @Autowired(required = false)
                                     BootLoginFailureHandler failureHandler,
                             @Autowired(required = false)
                                     AuthenticationSuccessHandler successHandler,
                             BootSecurityProperties properties) {


        this.authenticationFilter = new SmsCodeAuthenticationFilter(properties.getSmsLogin().getLoginProcessUrl());
        this.authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        if (successHandler!=null) {
            this.authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        }

        this.authenticationProvider = new SmsAuthenticationProvider();
        this.authenticationProvider.setUserDetailsService(userDetailsService);

        this.codeCheckFilter = new SmsCodeCheckFilter(properties);
        this.codeCheckFilter.setFailureHandler(failureHandler);
        this.codeCheckFilter.setTemplate(redisTemplate);
        this.codeCheckFilter.setSuccessHandler(successHandler);


    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        this.authenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.authenticationProvider(this.authenticationProvider)
                .addFilterBefore(this.codeCheckFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
