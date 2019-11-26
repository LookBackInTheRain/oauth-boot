package club.yuit.oauth.boot.config;

import club.yuit.oauth.boot.authentication.sms.SmsAuthenticationProvider;
import club.yuit.oauth.boot.authentication.sms.SmsSecurityConfig;
import club.yuit.oauth.boot.filter.BootPictureCodeAuthenticationFilter;
import club.yuit.oauth.boot.support.BootLoginFailureHandler;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.BootUserDetailService;
import club.yuit.oauth.boot.support.properities.BootBaseLoginProperties;
import club.yuit.oauth.boot.support.properities.BootSmsLoginProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author yuit
 * @date 2018/10/10  11:48
 **/
@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private BootUserDetailService userDetailService;


    private BootSecurityProperties properties;


    private BootLoginFailureHandler handler;

    private BootPictureCodeAuthenticationFilter pictureCodeAuthenticationFilter;
    private SmsSecurityConfig smsSecurityConfig;


    public SecurityConfig(BootUserDetailService userDetailService,
                          BootSecurityProperties properties,
                          BootLoginFailureHandler handler,
                          BootPictureCodeAuthenticationFilter pictureCodeAuthenticationFilter,
                          SmsSecurityConfig smsSecurityConfig) {
        this.userDetailService = userDetailService;
        this.properties = properties;
        this.handler = handler;
        this.pictureCodeAuthenticationFilter = pictureCodeAuthenticationFilter;
        this.smsSecurityConfig = smsSecurityConfig;
    }

    /**
     * 让Security 忽略这些url，不做拦截处理
     *
     * @param
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers
                ("/swagger-ui.html/**", "/webjars/**",
                        "/swagger-resources/**", "/v2/api-docs/**",
                        "/swagger-resources/configuration/ui/**","/statics/**",properties.getCodePath(), "/swagger-resources/configuration/security/**",
                        "/images/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        BootBaseLoginProperties base = properties.getBaseLogin();
        BootSmsLoginProperties sms = properties.getSmsLogin();
        http
                // http security 要拦截的url，这里这拦截，oauth2相关和登录登录相关的url，其他的交给资源服务处理
                .requestMatchers()
                .antMatchers( "/oauth/**",properties.getLoginPage(),
                        base.getLoginProcessUrl(),sms.getLoginProcessUrl())
                .and()
                .authorizeRequests()
                // 自定义页面或处理url是，如果不配置全局允许，浏览器会提示服务器将页面转发多次
                .antMatchers(properties.getLoginPage(),base.getLoginProcessUrl(),sms.getLoginProcessUrl())
                .permitAll()
                .anyRequest()
                .authenticated();

        // 表单登录
        http.formLogin()
                .failureHandler(handler)
                // 请求 {用户名} 参数名称
                .usernameParameter(base.getUsernameParameterName())
                // 请求 {密码} 参数名
                .passwordParameter(base.getPasswordParameterName())
                // 登录页面
                .loginPage(properties.getLoginPage())
                // 登录处理url
                .loginProcessingUrl(base.getLoginProcessUrl());

        http.httpBasic().disable();

        http.apply(this.smsSecurityConfig);

        // 用户密码验证之前校验验证码
        //http.addFilterBefore(pictureCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SmsAuthenticationProvider smsAuthenticationProvider (){
        return new SmsAuthenticationProvider();
    }



}
