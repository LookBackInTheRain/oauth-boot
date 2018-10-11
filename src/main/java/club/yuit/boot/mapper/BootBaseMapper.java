package club.yuit.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author yuit
 * @create time 2018/10/11  9:36
 * @description
 * @modify by
 * @modify time
 **/
@Repository("bootBaseMapper")
public interface BootBaseMapper<T> extends BaseMapper<T> {
}
