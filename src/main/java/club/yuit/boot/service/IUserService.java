package club.yuit.boot.service;

import club.yuit.boot.entity.User;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  16:55
 * @description
 * @modify by
 * @modify time
 **/
public interface IUserService {

    /**
     * 获取所有用户
     * @return
     */
    public List<User> getUsers();

}
