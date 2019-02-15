package club.yuit.oauth.boot.mapper;

import club.yuit.oauth.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yuit
 * @date 2018/10/9  15:44
 *
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where username=#{u1}")
    User findByUserName(@Param("u1") String username);

}
