package club.yuit.oauth.boot.support.code;

import club.yuit.oauth.boot.support.code.BootCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author yuit
 * @date 2019/4/9 18:09
 */
@Slf4j
public class SessionCodeService implements BootCodeService<String> {





    public SessionCodeService() {
    }

    @Override
    public String getCodeValue(String key){
        HttpSession session = this.getSession();
        if (session==null){
            log.error("当前请求获取不到Session");
            return null;
        }
        return  (String)session.getAttribute(key);
    }

    @Override
    public void setCodeValue(String key, String value) {
        HttpSession session = this.getSession();
        if (session==null){
            log.error("当前请求获取不到Session");
            return;
        }
        session.setAttribute(key,value);
    }

    private HttpSession getSession() {

       HttpServletRequest request =  ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();

       if (request==null) {
           log.error("当前请求获取不到Session");
           return null;
       }
       return request.getSession();
    }



}
