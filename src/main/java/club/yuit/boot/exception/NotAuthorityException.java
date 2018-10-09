package club.yuit.boot.exception;

/**
 * @author yuit
 * @create Time 2018/8/6 15:56
 * @description 没有权限
 * @modify by
 * @modify time
 **/
public class NotAuthorityException extends RuntimeException{

    public NotAuthorityException() {
        this("没有权限！");
    }

    public NotAuthorityException(String message) {
        super(message);
    }
}
