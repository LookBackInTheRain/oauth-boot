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
public  class VerificationCode<T> {

    private T content;
    private long expirationTime;

    public VerificationCode() {
    }


    public VerificationCode(T content, long expirationTime) {
        this.content = content;
        this.expirationTime = expirationTime;
    }
}
