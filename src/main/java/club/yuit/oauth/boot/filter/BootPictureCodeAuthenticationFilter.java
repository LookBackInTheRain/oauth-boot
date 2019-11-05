package club.yuit.oauth.boot.filter;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.code.picture.BootSessionPictureCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @date 2019/4/9 16:10
 */
@Component
public class BootPictureCodeAuthenticationFilter extends OncePerRequestFilter {


    private AntPathMatcher pathMatcher= new AntPathMatcher();
    private BootSecurityProperties properties;
    private BootSessionPictureCodeService pictureCodeService;

    public BootPictureCodeAuthenticationFilter(BootSecurityProperties properties, BootSessionPictureCodeService pictureCodeService) {
        this.properties = properties;
        this.pictureCodeService = pictureCodeService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //本次请求url
        String path = request.getRequestURI();


        if (pathMatcher.match(properties.getLoginProcessUrl(),path)) {

            // 图片验证码值
            String pCode = request.getParameter(properties.getPictureCodeParameterName());


            if(StringUtils.isBlank(pCode)){
                response.sendRedirect(properties.getLoginPage()+"?error=code is blank");
                return;
            }


            String pRealCode=this.pictureCodeService.getCodeValue(properties.getPictureCodeParameterName());

            if (pRealCode==null){
                response.sendRedirect(properties.getLoginPage()+"?error=code is expire");
                return;
            }

            if (!StringUtils.equalsIgnoreCase(pCode,pRealCode)){
                response.sendRedirect(properties.getLoginPage()+"?error=code is error");
                return;
            }

        }


        filterChain.doFilter(request,response);

    }


}
