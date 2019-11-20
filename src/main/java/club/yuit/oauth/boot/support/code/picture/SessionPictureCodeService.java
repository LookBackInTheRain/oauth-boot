package club.yuit.oauth.boot.support.code.picture;

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
@Component
public class SessionPictureCodeService implements BootCodeService<String> {


    private HttpSession session;

    public SessionPictureCodeService(HttpSession session) {
        this.session = session;
    }

    @Override
    public String getCodeValue(String key){
        return (String) this.session.getAttribute(key);
    }

    @Override
    public void setCodeValue(String key, String value) {
        this.session.setAttribute(key,value);
    }

}
