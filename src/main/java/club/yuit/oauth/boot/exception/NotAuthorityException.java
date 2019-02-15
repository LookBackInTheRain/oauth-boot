package club.yuit.oauth.boot.exception;

/**
 * @author yuit
 * @date Time 2018/8/6 15:56
 *  没有权限
 **/
public class NotAuthorityException extends RuntimeException{

    public NotAuthorityException() {
        this("没有权限！");
    }

    public NotAuthorityException(String message) {
        super(message);
    }
}
