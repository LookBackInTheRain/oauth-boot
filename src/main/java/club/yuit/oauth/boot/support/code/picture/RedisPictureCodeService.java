package club.yuit.oauth.boot.support.code.picture;

import club.yuit.oauth.boot.support.code.BootCodeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yuit
 * @date 2019/11/20 15:37
 **/
@Component
public class RedisPictureCodeService implements BootCodeService<String> {

    private StringRedisTemplate template;

    public RedisPictureCodeService(StringRedisTemplate template) {
        this.template = template;
    }

    @Override
    public String getCodeValue(String key) {
        return this.template.opsForValue().get(key);
    }

    @Override
    public void setCodeValue(String key, String value) {
        this.template.opsForValue().set(key,value);
    }
}
