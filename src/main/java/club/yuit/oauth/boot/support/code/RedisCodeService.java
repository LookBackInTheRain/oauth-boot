package club.yuit.oauth.boot.support.code;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author yuit
 * @date 2019/11/20 15:37
 **/
public class RedisCodeService implements BootCodeService<String> {

    private StringRedisTemplate template;
    private long expire;

    public RedisCodeService(StringRedisTemplate template, long expire) {
        this.template = template;
        this.expire = expire;
    }

    @Override
    public String getCodeValue(String key) {
        return this.template.opsForValue().get(key);
    }

    @Override
    public void setCodeValue(String key, String value) {
        this.template.opsForValue().set(key, value, this.expire, TimeUnit.SECONDS);
    }


    @Override
    public boolean verification(String key, String value, boolean ignore) {

        if (value == null || value.trim().equals("")) {
            return false;
        }

        Boolean has = this.template.hasKey(key);

        if (has == null || !has) {
            return false;
        }

        Long expire = this.template.getExpire(key);

        if (expire == null || expire <= 0) {
            return false;
        }

        String tmpValue = this.getCodeValue(key);

        if (ignore) {
            return value.equalsIgnoreCase(tmpValue);
        }
        return value.equals(tmpValue);
    }


}
