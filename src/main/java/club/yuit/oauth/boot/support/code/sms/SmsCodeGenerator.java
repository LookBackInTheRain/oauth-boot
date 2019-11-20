package club.yuit.oauth.boot.support.code.sms;

import club.yuit.oauth.boot.support.code.VerificationCode;
import club.yuit.oauth.boot.support.code.VerificationCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author yuit
 * @date 2019/11/20 15:46
 **/
public class SmsCodeGenerator implements VerificationCodeGenerator {
    @Override
    public VerificationCode generator(ServletWebRequest request) {
        return null;
    }
}
