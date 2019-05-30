package club.yuit.oauth.boot.service.impl;

import club.yuit.oauth.boot.entity.User;
import club.yuit.oauth.boot.mapper.UserMapper;
import club.yuit.oauth.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuit
 * @date  2018/10/9  16:56
 *
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public User findByUserName(String userName) {
        return this.baseMapper.findByUserName(userName);
    }


}
