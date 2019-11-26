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
    void setCodeValue(String key, T value, long expire);
}
