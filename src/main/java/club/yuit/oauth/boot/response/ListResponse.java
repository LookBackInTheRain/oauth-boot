package club.yuit.oauth.boot.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @create time  2018/3/30 20:37
 * @description
 * @modify
 * @modify time
 */
@Getter
@Setter
public class ListResponse extends BaseResponse {

    private long count;
    private List items;

    protected ListResponse(){

    }

}
