package club.yuit.boot.auth;

import club.yuit.boot.entity.User;
import club.yuit.boot.mapper.UserMapper;
import club.yuit.boot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/11  9:13
 * @description
 * @modify by
 * @modify time
 **/
@Component
public class BootUserDetailService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= this.userService.findByUserName(username);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");

        List <GrantedAuthority>authorities = new ArrayList<>();
        authorities.add(authority);
        user.setAuthorities(authorities);

        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return user;
    }
}
