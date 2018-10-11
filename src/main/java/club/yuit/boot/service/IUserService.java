package club.yuit.boot.service;

import club.yuit.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  16:55
 * @description
 * @modify by
 * @modify time
 **/
public interface IUserService extends IBaseService<User>{

    /**
     * 获取所有用户
     * @return
     */
    public List<User> getUsers();


    User findByUserName(String userName);

}
