package club.yuit.boot.mapper;

import club.yuit.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yuit
 * @create time 2018/10/9  15:44
 * @description
 * @modify by
 * @modify time
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
