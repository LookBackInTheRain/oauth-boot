package club.yuit.oauth.boot.support.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yuit
 * @date 2018/10/19 17:42
 *
 */
public interface VerificationCodeGenerator {

    /**
     * 生成验证码
     * @return
     */
    VerificationCode generator(ServletWebRequest request);

}
