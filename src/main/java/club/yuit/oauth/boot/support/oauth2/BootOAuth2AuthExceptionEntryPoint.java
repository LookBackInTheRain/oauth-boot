package club.yuit.oauth.boot.support.oauth2;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @create 2018/11/2 10:48
 * @description
 * @modify
 */
@Component
public class BootOAuth2AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        System.out.println("BootOAuth2AuthExceptionEntryPoint:--------------->"+e.getClass().getSimpleName());
    }
}
