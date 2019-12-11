package club.yuit.oauth.boot.controller.boot;

import ch.qos.logback.core.rolling.helper.TimeBasedArchiveRemover;
import club.yuit.oauth.boot.response.BaseResponse;
import club.yuit.oauth.boot.response.HttpResponse;
import club.yuit.oauth.boot.response.SimpleResponse;
import club.yuit.oauth.boot.utils.HttpUtils;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuit
 * @date 2019/12/10 10:01
 * 优先读取 配置文件中server.error.path 的值，没有值就使用 /error 默认路径
 **/
@Controller
@RequestMapping("${server.error.path:/error}")
public final class BootErrorController implements ErrorController {

    private final ErrorProperties errorProperties;
    private final ErrorAttributes errorAttributes;

    public BootErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        this.errorProperties = serverProperties.getError();
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping
    public void handleError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpUtils.writer(preHandle(request),response);
    }


    private BaseResponse preHandle(HttpServletRequest request){


        boolean isIncludeStackTrace = this.isIncludeStackTrace(request);
        // 获取异常信息
        Map<String,Object> errors = this.getErrorAttributes(request,isIncludeStackTrace);

        Throwable throwable = (Throwable) errors.get("throwable");
        int status = (int)errors.get("status");
        return  HttpResponse.baseResponse(status,errors.get("error").toString());

    }


    private Map<String,Object> getErrorAttributes(HttpServletRequest request,boolean isIncludeStackTrace){
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String,Object> errors = this.errorAttributes.getErrorAttributes(webRequest,isIncludeStackTrace);

        // 获取异常
        Throwable throwable = this.errorAttributes.getError(webRequest);

        errors.put("throwable",throwable);
        return errors;
    }


    /**
     * Determine if the stacktrace attribute should be included.
     * @param request the source request
     * @return if the stacktrace attribute should be included
     */
    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = this.errorProperties.getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return this.getTraceParameter(request);
        }
        return false;
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equalsIgnoreCase(parameter);
    }



    /**
     * 请求路径
     * @return
     */
    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }
}
