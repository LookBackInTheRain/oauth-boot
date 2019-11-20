package club.yuit.oauth.boot.support.code;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2018/10/19 10:42
 *
 */
@Getter
@Setter
public  class VerificationCode {

    private String code;
    private Integer expirationTime;

    public VerificationCode() {
    }


    public VerificationCode(String code, Integer expirationTime) {
        this.code = code;
        this.expirationTime = expirationTime;
    }
}
