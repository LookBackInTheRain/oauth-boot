package club.yuit.oauth.boot.response;

import lombok.Data;

/**
 * @author yuit
 * @date   2018/3/30 20:37
 *
 */
@Data
public  class BaseResponse  {

    private int status;
    private String msg;

    protected BaseResponse() {
    }

    protected BaseResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
