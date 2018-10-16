package club.yuit.boot.auth.support.handler;

import club.yuit.boot.auth.support.BootClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @create time 2018/10/16  17:57
 * @description
 * @modify by
 * @modify time
 **/
@Component
public class BootLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private BootClientDetailsService clientDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    }
}
