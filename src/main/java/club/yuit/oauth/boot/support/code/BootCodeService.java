package club.yuit.oauth.boot.support.code;

/**
 * @author yuit
 * @date 2019/4/9 17:43
 */
public interface BootCodeService<T> {
    /**
     * 获取验证码
     * @param key
     * @return
     */
    T getCodeValue(String key);

    /**
     * 设置验证码值
     * @param key
     * @param value
     */
    void setCodeValue(String key, T value);

    /**
     * 校验验证码
     * @param key key
     * @param value 值
     * @param ignore 是否忽略大小写
     * @return
     */
    boolean verification(String key,T value,boolean ignore);
}
