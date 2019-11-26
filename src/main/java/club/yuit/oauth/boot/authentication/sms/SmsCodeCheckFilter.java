package club.yuit.oauth.boot.authentication.sms;

import club.yuit.oauth.boot.exception.VerificationCodeFailureException;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @date 2018/10/19 15:33
 */
@Getter
@Setter
@Slf4j
public class SmsCodeCheckFilter extends OncePerRequestFilter {


    private AuthenticationFailureHandler failureHandler;
    private BootSecurityProperties properties;
    private StringRedisTemplate template;
    private AuthenticationSuccessHandler successHandler;
    private PathMatcher pathMatcher;



    public SmsCodeCheckFilter(BootSecurityProperties properties) {
        setProperties(properties);
        this.pathMatcher = new AntPathMatcher();

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(this.pathMatcher.match("/authentication/mobile",request.getRequestURI())
                && StringUtils.equalsAnyIgnoreCase(request.getMethod(),"post")){
            try {
                check(request,response,filterChain);
            }catch (Exception ex){
                if (ex instanceof VerificationCodeFailureException){
                    failureHandler.onAuthenticationFailure(request,response, (AuthenticationException) ex);
                }
                throw ex;
            }

        }else {
            filterChain.doFilter(request,response);
        }
    }

    private void check(HttpServletRequest request, HttpServletResponse response,FilterChain chain) throws VerificationCodeFailureException, IOException, ServletException {

        String mobile = request.getParameter(properties.getSmsLogin().getCodeParameterName());
        String code = request.getParameter(properties.getSmsLogin().getCodeParameterName());
        if (mobile.trim().length()==0) {
            throw new VerificationCodeFailureException("手机号不能为空");
        }

        if (this.template.hasKey(mobile)) {
            throw new VerificationCodeFailureException("验证码过期或手机号错误");
        }

       Long expireTime= this.template.getExpire(mobile);

        if (expireTime==0){
            throw new VerificationCodeFailureException("验证码过期");
        }

        String redisCode = this.template.opsForValue().get(mobile);

        if (!code.equals(redisCode)) {
            throw new VerificationCodeFailureException("验证码错误");
        }

        chain.doFilter(request,response);
    }



}
