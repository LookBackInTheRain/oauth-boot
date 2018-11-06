package club.yuit.oauth.boot.config.auth2;

import club.yuit.oauth.boot.filter.ClientIdCheckFilter;
import club.yuit.oauth.boot.support.oauth2.BootAccessDeniedHandler;
import club.yuit.oauth.boot.support.oauth2.BootClientDetailsService;
import club.yuit.oauth.boot.support.oauth2.BootOAuth2AuthExceptionEntryPoint;
import club.yuit.oauth.boot.support.oauth2.BootOAuth2WebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author yuit
 * @create time 2018/10/15  14:52
 * @description
 * @modify by
 * @modify time
 **/
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    private BootClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    private WebResponseExceptionTranslator bootWebResponseExceptionTranslator;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {


        // 允许表单登录
        security.allowFormAuthenticationForClients();

        security.authenticationEntryPoint(authenticationEntryPoint);

        security.addTokenEndpointAuthenticationFilter(new ClientIdCheckFilter());

        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {



        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);

        if (this.converter != null) {
            endpoints.accessTokenConverter(converter);
        }

        // 处理 ExceptionTranslationFilter 抛出的异常
        endpoints.exceptionTranslator(bootWebResponseExceptionTranslator);


        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");
    }


}
