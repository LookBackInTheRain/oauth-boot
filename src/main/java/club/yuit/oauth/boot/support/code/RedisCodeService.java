package club.yuit.oauth.boot.support.code;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuit
 * @date 2019/11/20 15:37
 **/
@Component
public class RedisCodeService implements BootCodeService<String> {

    private StringRedisTemplate template;
    public RedisCodeService(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public String getCodeValue(String key) {
        return this.template.opsForValue().get(key);
    }

    @Override
    public void setCodeValue(String key, String value,long expire) {
        this.template.opsForValue().set(key,value,expire);
    }
}
