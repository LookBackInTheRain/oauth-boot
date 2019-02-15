package club.yuit.oauth.boot.support.oauth2;

import club.yuit.oauth.boot.response.HttpResponse;
import club.yuit.oauth.boot.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @date 2018/11/1 18:15
 *  请求拒绝，没有权限
 */
@Component
public class BootAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        HttpUtils.writerError(HttpResponse.baseResponse(HttpStatus.FORBIDDEN.value(),"没有权限"),response);
    }
}
