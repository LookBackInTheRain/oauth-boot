package club.yuit.oauth.boot.config.auth2;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @author yuit
 * @date  2018/10/17  16:38
 *
 * token 存储方式配置
 *
 **/
@Configuration
public class TokenStoreConfig {

    private BootSecurityProperties properties;

    private RedisConnectionFactory factory;



    private DataSource dataSource;

    @Autowired(required = false)
    public TokenStoreConfig(BootSecurityProperties properties, RedisConnectionFactory factory, DataSource dataSource) {
        this.properties = properties;
        this.factory = factory;

        this.dataSource = dataSource;
    }

    @Bean
    public TokenStore tokenStore() throws Exception {

        TokenStore store = null;

        switch (properties.getTokenStoreType()) {
            case jwt:
                store = new JwtTokenStore(jwtAccessTokenConverter());
                break;
            case redis:
                if (factory == null) {
                    throw new BeanCreationException("配置Redis存储Token需要redisConnectionFactory bean，未找到");
                }
                store = new RedisTokenStore(factory);
                break;
            case jdbc:

                if(dataSource==null){
                    throw new BeanCreationException("配置jdbc存储Token需要dataSource bean，未找到");
                }
                store=new JdbcTokenStore(dataSource);
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

        converter.setSigningKey(properties.getTokenSigningKey());

        return converter;
    }
}
