package club.yuit.boot.service.impl;

import club.yuit.boot.entity.User;
import club.yuit.boot.mapper.UserMapper;
import club.yuit.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  16:56
 * @description
 * @modify by
 * @modify time
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getUsers() {

        List<User> dd= this.userMapper.findByUserName("yuit");

        return this.userMapper.selectList(null);
    }
}
