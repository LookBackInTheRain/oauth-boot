package club.yuit.oauth.boot.config;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author yuit
 * @create time 2018/10/17  16:38
 * @description
 * @modify by
 * @modify time
 **/
@Configuration
public class TokenStoreConfig {

    @Autowired
    private BootSecurityProperties properties;

    @Autowired(required = false)
    private RedisConnectionFactory factory;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;

    @Bean
    public TokenStore tokenStore() throws Exception {

        TokenStore store = null;

        switch (properties.getTokenStoreType()) {
            case jwt:
                store = new JwtTokenStore(converter);
                break;
            case redis:
                if (factory == null) {
                    throw new ClassNotFoundException("RedisConnectionFactory.class not fount");
                }
                store = new RedisTokenStore(factory);
                break;
            default:
                store = new InMemoryTokenStore();

        }

        return store;
    }

    @Bean
    @Primary
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        converter.setSigningKey("123qwe");

        return converter;
    }
}
