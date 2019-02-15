package club.yuit.oauth.boot.support.code;

import lombok.Data;

/**
 * @author yuit
 * @date 2018/10/19 10:42
 *
 */
@Data
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
