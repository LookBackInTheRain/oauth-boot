package club.yuit.oauth.boot.support.code;

import lombok.Data;

/**
 * @auther yuit
 * @create 2018/10/19 10:42
 * @description
 * @modify
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
