package club.yuit.oauth.boot.support.oauth2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author yuit
 * @create 2018/11/5 18:00
 * @description
 * @modify
 */
@JsonSerialize(using = BootOAuthExceptionJacksonSerializer.class)
public class BootOAuth2Exception extends OAuth2Exception {
    public BootOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public BootOAuth2Exception(String msg) {
        super(msg);
    }

    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    public int getHttpErrorCode() {
        return 403;
    }
}
