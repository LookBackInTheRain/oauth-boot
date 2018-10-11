package club.yuit.boot.mapper;

import club.yuit.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  15:44
 * @description
 * @modify by
 * @modify time
 **/
@Repository
public interface UserMapper extends BootBaseMapper<User> {

    @Select("select * from user where username=#{u1}")
    User findByUserName(@Param("u1") String username);

}
