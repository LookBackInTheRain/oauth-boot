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
public class BootSessionPictureCodeService implements BootCodeService<String> {



    public BootSessionPictureCodeService() {


    }

    @Override
    public String getCodeValue(String key){

        RequestAttributes requestAttributes= RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = (HttpServletRequest)(RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION));
        HttpSession session = request.getSession();
        return (String) session.getAttribute(key);
    }

    @Override
    public void setCodeValue(String key, String value) {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(key,value);
    }

}
