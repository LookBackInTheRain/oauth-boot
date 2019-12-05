package club.yuit.oauth.boot.filter;

import club.yuit.oauth.boot.exception.AuthFailureException;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.code.BootCodeService;
import club.yuit.oauth.boot.support.properities.BootBaseLoginProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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

public class BootPictureCodeAuthenticationFilter extends OncePerRequestFilter {


    private AntPathMatcher pathMatcher= new AntPathMatcher();
    private BootSecurityProperties properties;
    private BootCodeService<String> bootCodeService;
    private AuthenticationFailureHandler failureHandler;

    public BootPictureCodeAuthenticationFilter(BootSecurityProperties properties, BootCodeService<String> bootCodeService, AuthenticationFailureHandler failureHandler) {
        this.properties = properties;
        this.bootCodeService = bootCodeService;
        this.failureHandler = failureHandler;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //本次请求url
        String path = request.getRequestURI();
        BootBaseLoginProperties base = this.properties.getBaseLogin();

        if (pathMatcher.match(base.getLoginProcessUrl(),path)) {

            // 图片验证码值
            String pCode = request.getParameter(base.getPictureCodeParameterName());
            String queryString = request.getQueryString();
            String key = request.getParameter("key");

            if(StringUtils.isBlank(pCode)){
                request.setAttribute("error","error");
                this.failureHandler.onAuthenticationFailure(request,response,new AuthFailureException("验证码错误"));
                return;
            }
            if (!this.bootCodeService.verification(key,pCode,true)){
               this.failureHandler.onAuthenticationFailure(request,response,new AuthFailureException("验证码错误"));
               return;
            }
        }


        filterChain.doFilter(request,response);

    }





}
