package club.yuit.oauth.boot.config.auth2;

import club.yuit.oauth.boot.filter.ClientIdCheckFilter;
import club.yuit.oauth.boot.support.oauth2.BootAccessDeniedHandler;
import club.yuit.oauth.boot.support.oauth2.BootClientDetailsService;
import club.yuit.oauth.boot.support.oauth2.BootOAuth2AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WebResponseExceptionTranslator bootWebResponseExceptionTranslator;

    @Autowired
    private BootClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;

    @Autowired
    private BootAccessDeniedHandler handler;

    @Autowired
    private BootOAuth2AuthExceptionEntryPoint point;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单登录
        security.allowFormAuthenticationForClients();

        security.addTokenEndpointAuthenticationFilter(new ClientIdCheckFilter());

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

        endpoints.exceptionTranslator(bootWebResponseExceptionTranslator);

        endpoints.pathMapping("/oauth/confirm_access","/custom/confirm_access");

    }


}
