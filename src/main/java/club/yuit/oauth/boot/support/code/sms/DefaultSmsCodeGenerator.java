package club.yuit.oauth.boot.support.code.sms;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.DefaultBeanName;
import club.yuit.oauth.boot.support.code.BootCodeService;
import club.yuit.oauth.boot.support.code.VerificationCode;
import club.yuit.oauth.boot.support.code.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author yuit
 * @date 2019/11/26 14:56
 **/
@Component(DefaultBeanName.DEFAULT_SMS_CODE_GENERATOR_BEAN)
@Slf4j
public class DefaultSmsCodeGenerator implements VerificationCodeGenerator<String,String> {

    private BootSecurityProperties properties;
    private BootCodeService<String> codeService;

    private final static String CODE_STR="1234567890";

    public DefaultSmsCodeGenerator(BootSecurityProperties properties, BootCodeService<String> codeService) {
        this.properties = properties;
        this.codeService = codeService;
    }

    @Override
    public VerificationCode<String> generator(String key) throws Exception {
        VerificationCode<String> vc = new VerificationCode<>();
        String code = RandomStringUtils.random(6,CODE_STR);
        vc.setContent(code);
        vc.setExpirationTime(properties.getSmsLogin().getExpirationTime());
        codeService.setCodeValue(key,code);

        log.info("[OAUTH·BOOT]：登录验证码:{},{}分钟内有效",vc.getContent(),vc.getExpirationTime()/60);

        return vc;
    }



}
