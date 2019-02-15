package club.yuit.oauth.boot.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yuit
 * @date   2018/3/30 20:37
 *
 */
@Getter
@Setter
public class ListResponse extends BaseResponse {

    private long count;
    private List items;

    protected ListResponse(){

    }

}
