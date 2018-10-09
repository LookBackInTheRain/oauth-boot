package club.yuit.boot.response;

import lombok.Data;

import java.util.List;

/**
 * @author yuit
 * @create Time 2018/8/6 15:56
 * @description
 * @modify by
 * @modify time
 **/
@Data
public class Items<T> {

    long count;
    List<T> items;

    public Items() {

    }

    public Items(long count, List<T> items) {
        this.count = count;
        this.items = items;
    }


}
