package club.yuit.oauth.boot.support;

import club.yuit.oauth.boot.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author yuit
 * @date 2019/11/26 9:38
 **/
@Component
public class BootSmsUserDetailService implements UserDetailsService {

    private IUserService userService;

    public BootSmsUserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        return userService.findUserByMobile(mobile);
    }
}
