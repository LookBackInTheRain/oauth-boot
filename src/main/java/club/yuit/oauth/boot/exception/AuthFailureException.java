package club.yuit.oauth.boot.exception;

/**
 * @author yuit
 * @date  2018/8/6 15:56
 *  认证失败
 **/
public class AuthFailureException extends RuntimeException {

    public AuthFailureException() {
        this("认证失败！");
    }

    public AuthFailureException(String message) {
        super(message);
    }
}
