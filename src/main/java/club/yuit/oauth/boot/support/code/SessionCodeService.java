package club.yuit.oauth.boot.support.code;

import club.yuit.oauth.boot.support.code.BootCodeService;
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
public class SessionCodeService implements BootCodeService<String> {


    private HttpSession session;

    public SessionCodeService(HttpSession session) {
        this.session = session;
    }

    @Override
    public String getCodeValue(String key){
        return (String) this.session.getAttribute(key);
    }

    @Override
    public void setCodeValue(String key, String value,long expire) {
        this.session.setAttribute(key,value);
    }

}
