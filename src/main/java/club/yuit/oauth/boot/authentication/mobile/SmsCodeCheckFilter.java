package club.yuit.oauth.boot.authentication.mobile;

import club.yuit.oauth.boot.exception.VerificationCodeFailureException;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther yuit
 * @create 2018/10/19 15:56
 * @description
 * @modify
 */
@Getter
@Setter
public class SmsCodeCheckFilter extends OncePerRequestFilter {


    private AuthenticationFailureHandler authenticationFailureHandler;


    private BootSecurityProperties properties;

    private boolean isDebug = false;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public SmsCodeCheckFilter() {

        if(properties.getLogging().getLevel().toUpperCase().equals("DEBUG")){
            isDebug = true;
        }

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(this.isDebug){
            logger.debug("--------------> request method "+ request.getMethod());
        }

        if(StringUtils.equals("/authentication/mobile",request.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(request.getMethod(),"post")){

            try {
                check(new ServletWebRequest(request));

            }catch (VerificationCodeFailureException ex){
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
            }


        }else {

            filterChain.doFilter(request,response);
        }
    }

    private void check(ServletWebRequest request) throws VerificationCodeFailureException{



    }



}
