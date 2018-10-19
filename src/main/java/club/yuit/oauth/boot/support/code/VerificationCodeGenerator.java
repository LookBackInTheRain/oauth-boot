package club.yuit.oauth.boot.support.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @auther yuit
 * @create 2018/10/19 17:42
 * @description 验证码生成 父接口
 * @modify
 */
public interface VerificationCodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    VerificationCode generator(ServletWebRequest request);

}
