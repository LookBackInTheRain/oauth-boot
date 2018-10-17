package club.yuit.oauth.boot.exception;

/**
 * @author yuit
 * @create Time 2018/8/6 15:56
 * @description 没有认证或是token过期
 * @modify by
 * @modify time
 **/
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        this("没有认证！");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
