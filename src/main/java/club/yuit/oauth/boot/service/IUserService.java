package club.yuit.oauth.boot.service;

import club.yuit.oauth.boot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author yuit
 * @date 2018/10/9  16:55
 *
 **/
public interface IUserService  extends IService<User> {




    User findByUserName(String userName);

}
