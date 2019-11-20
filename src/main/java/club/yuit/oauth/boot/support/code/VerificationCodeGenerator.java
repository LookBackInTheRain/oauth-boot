package club.yuit.oauth.boot.support.code;

import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @author yuit
 * @date 2018/10/19 17:42
 *
 */
public interface VerificationCodeGenerator<T> {

    /**
     * 生成验证码
     * @return
     */
    void generator(BootCodeService<T> codeService) throws Exception;

}
