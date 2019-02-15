package club.yuit.oauth.boot.exception;

/**
 * @author yuit
 * @date  2018/8/6 15:56
 *  参数不正确
 **/
public class ArgumentsFailureException extends RuntimeException {

    public ArgumentsFailureException() {
        this("参数错误");
    }

    public ArgumentsFailureException(String message) {
        super(message);
    }
}
