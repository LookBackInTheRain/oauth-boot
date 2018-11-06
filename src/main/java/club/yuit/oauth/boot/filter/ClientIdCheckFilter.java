package club.yuit.oauth.boot.filter;

import club.yuit.oauth.boot.response.BaseResponse;
import club.yuit.oauth.boot.response.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * @author yuit
 * @create 2018/11/5 11:38
 * @description 认证不带客户端信息参数处理 filter
 * @modify
 */
public class ClientIdCheckFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (this.isHasClientDetails(request, response)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json,charset=utf-8");

            ObjectMapper objectMapper = new ObjectMapper();
            BaseResponse bs = HttpResponse.baseResponse(HttpStatus.UNAUTHORIZED.value(), "请求中未包含客户端信息");

            objectMapper.writeValue(response.getOutputStream(), bs);

            return;
        }

        filterChain.doFilter(request, response);
    }

    // 判断请求头中是否包含client信息，不包含返回false
    private boolean isHasClientDetails(HttpServletRequest request, HttpServletResponse response) {

        String h = request.getParameter("client_id");

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {

            String basic = header.substring(0, 5);

            if (basic.toLowerCase().contains("basic")) {

                String tmp = header.substring(6);
                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));

                String[] clientArrays = defaultClientDetails.split(":");

                return clientArrays.length != 2;

            }
        }

        if(header == null && request.getParameter("client_id") != null)
            return false;


        return true;
    }


}
