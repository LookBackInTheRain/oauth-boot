package club.yuit.oauth.boot.handler;


import club.yuit.oauth.boot.exception.ArgumentsFailureException;
import club.yuit.oauth.boot.exception.AuthFailureException;
import club.yuit.oauth.boot.exception.NotAuthException;
import club.yuit.oauth.boot.exception.NotAuthorityException;
import club.yuit.oauth.boot.response.BaseResponse;
import club.yuit.oauth.boot.response.SimpleResponse;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static club.yuit.oauth.boot.response.HttpResponse.baseResponse;
import static club.yuit.oauth.boot.response.HttpResponse.simpleResponse;


/**
 * @author yuit
 * @create time  2018/3/30 20:37
 * @description 错误同意拦截
 * @modify
 * @modify time
 */
@RestControllerAdvice
public final class ExceptionAdviceHandler {

    private final static String SERVER_ERROR_TXT = "服务器内部错误";
    private final static String ARGUMENTS_ERROR_TXT = "参数错误";
    private final static String BAD_REQUEST_TXT = "错误的请求";

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse unKnowExceptionHandler() {
        return this.serverErrorHandler();
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse runtimeExceptionHandler(RuntimeException ex) {
        return this.serverErrorHandler();
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse nullPointerExceptionHandler(Exception e) {

        e.printStackTrace();

        return this.serverErrorHandler();
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse classCastExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse iOExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     *  未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse noSuchMethodExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse indexOutOfBoundsExceptionHandler() {
        return this.serverErrorHandler();
    }

    /**
     * 400错误
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestNotReadable() {
        return baseResponse(400, BAD_REQUEST_TXT);
    }

    /**
     * 400错误 类型不匹配
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestTypeMismatch() {
        return this.argumentsError();
    }

    /**
     * 400错误 缺少参数
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse requestMissingServletRequest() {
        return this.argumentsError();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse methodArgumentNotValidExceptionHandler() {
        return baseResponse(400, "参数错误");
    }

    @ExceptionHandler(value = NotAuthorityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse notAuthority(NotAuthorityException ex) {
        return this.authErrorHandler(2, ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse usernameNotFound(UsernameNotFoundException ex){
        return baseResponse(400,ex.getMessage());
    }


    @ExceptionHandler(value = NotAuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse notAuth(NotAuthException ex) {
        return this.authErrorHandler(1, ex.getMessage());
    }

    @ExceptionHandler(value = AuthFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse authFieald(AuthFailureException ex) {
        return this.authErrorHandler(1, ex.getMessage());
    }


    /**
     * 405错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public BaseResponse request405(HttpServletResponse resp) {
        return baseResponse(405, "请求方法不正确");
    }

    /**
     * 406错误
     */

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public BaseResponse request406(HttpServletResponse resp) {
        return baseResponse(405, "不接受该请求");

    }

    /**
     * 500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse server500(HttpServletResponse resp,Exception e) {
        return this.serverErrorHandler();
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public BaseResponse httpMediaTypeNotSupportedExceptionHandler(HttpServletResponse resp) {
        return baseResponse(415, "服务器无法处理请求附带的媒体格式");
    }


    @ExceptionHandler(value = ArgumentsFailureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse argsErrorExceptionHandler(ArgumentsFailureException ex, HttpServletResponse response) {
        return baseResponse(400, ex.getMessage());
    }

    /**
     * 404
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse notFoundException(HttpServletResponse response) {
        return baseResponse(404, "找不到服务");
    }

    @ExceptionHandler(value = ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse serverErrorExceptionHandler(HttpServletResponse response) {
        return this.serverErrorHandler();
    }


    private BaseResponse serverErrorHandler() {
        return baseResponse(500, SERVER_ERROR_TXT);
    }

    private BaseResponse argumentsError() {
        return baseResponse(400, ARGUMENTS_ERROR_TXT);
    }

    /**
     * @param code 1 认证错误（未认证）、2 未授权/没有权限
     * @param msg
     * @return
     */
    private SimpleResponse authErrorHandler(int code, String msg) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("code", code);
        return simpleResponse(401, msg, mp);
    }

}
