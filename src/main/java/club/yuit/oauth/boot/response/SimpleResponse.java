package club.yuit.oauth.boot.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date  2018/3/30 20:37
 *
 */
@Getter
@Setter
public class SimpleResponse<T> extends BaseResponse {

    private T item;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, T item) {
        super(status, msg);
        this.item = item;
    }


}
