package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.response.HttpResponse;
import club.yuit.oauth.boot.response.SimpleResponse;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.DefaultBeanName;
import club.yuit.oauth.boot.support.code.BootCodeService;
import club.yuit.oauth.boot.support.code.VerificationCode;
import club.yuit.oauth.boot.support.code.VerificationCodeGenerator;
import club.yuit.oauth.boot.support.code.picture.DefaultPictureCodeGenerator;
import club.yuit.oauth.boot.support.code.sms.DefaultSmsCodeGenerator;
import club.yuit.oauth.boot.support.properities.CodeStoreType;
import club.yuit.oauth.boot.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuit
 * @date 2018/10/9  15:09
 **/
@Controller
@Slf4j
public class BaseMainController {


    private BootSecurityProperties properties;
    private BootCodeService codeService;
    private Map<String, VerificationCodeGenerator> codeGenerators;

    public BaseMainController(BootSecurityProperties properties,
                              BootCodeService codeService, Map<String, VerificationCodeGenerator> codeGenerators) {
        this.properties = properties;
        this.codeService = codeService;
        this.codeGenerators = codeGenerators;
    }

    @GetMapping("${boot.oauth.login-page:/auth/login}")
    public String loginPage(Model model, HttpServletRequest request) {

        String type = request.getParameter("type");

        if (type != null
                && (type.equalsIgnoreCase("base")
                || type.equalsIgnoreCase("sms")
                || type.equalsIgnoreCase("social"))) {
            model.addAttribute("type", type);
        } else {
            model.addAttribute("type", "base");
        }

        model.addAttribute("sms", properties.getSmsLogin());
        model.addAttribute("base", properties.getBaseLogin());
        model.addAttribute("codePath", properties.getCodePath());
        return "base-login";
    }

    /**
     * 网页
     * @param response
     * @param request
     * @param type
     * @throws Exception
     */
    @RequestMapping(value = "${boot.oauth.code-path:/auth/code}",produces = MediaType.TEXT_HTML_VALUE)
    public void pictureCodeGenerateHtml(HttpServletResponse response,HttpServletRequest request, @RequestParam("type") String type,Model model) throws Exception {

        String tmp = type.trim();

        if (tmp.equals("picture")) {
            String key = this.properties.getBaseLogin().getPictureCodeParameterName();

            if (this.properties.getCodeStoreType()== CodeStoreType.redis){
                key = UUID.randomUUID().toString();
            }
            VerificationCode<BufferedImage> pCode = ((DefaultPictureCodeGenerator)this.codeGenerators.get(DefaultBeanName.DEFAULT_PICTURE_CODE_GENERATOR_BEAN))
                    .generator(key);

            OutputStream os = response.getOutputStream();
            ImageIO.write(pCode.getContent(), "jpg", os);
            os.flush();
            os.close();

        } else if (tmp.equals("sms")) {

            String key = request.getParameter("mobile");

            VerificationCode<String> sCode = ((DefaultSmsCodeGenerator)this.codeGenerators.get(DefaultBeanName.DEFAULT_SMS_CODE_GENERATOR_BEAN))
                    .generator(key);

            response.setContentType("application/json");
            Map<String,Object> map = new HashMap<>();
            map.put("value",sCode);
            map.put("key",key);

            HttpUtils.writer(HttpResponse.simpleResponse(200,map),response);
        }


    }

    /**
     * 返回JSON,可用于前后端分离架构，只能使用redis存储验证码
     * @param request
     * @param type
     * @throws Exception
     */
    @RequestMapping(value = "${boot.oauth.code-path:/auth/code}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SimpleResponse<Map<String,Object>> pictureCodeGenerateJson(HttpServletRequest request, @RequestParam("type") String type) throws Exception {

        String tmp = type.trim();
        Map<String,Object> responseBoy= new HashMap<>();

        if (tmp.equals("picture")) {

            String key =  UUID.randomUUID().toString();

            VerificationCode<BufferedImage> pCode = ((DefaultPictureCodeGenerator)this.codeGenerators.get(DefaultBeanName.DEFAULT_PICTURE_CODE_GENERATOR_BEAN))
                    .generator(key);

            ByteArrayOutputStream out= new ByteArrayOutputStream();

            ImageIO.write(pCode.getContent(),"png",out);

            byte[] bts =  out.toByteArray();

            String base64_code = Base64.toBase64String(bts);
            // 删除 \n \r
            base64_code = base64_code.replaceAll("\n","").replace("\r","");

            responseBoy.put("value",base64_code);
            responseBoy.put("key",key);
        } else if (tmp.equals("sms")) {

            String key = request.getParameter("mobile");

            VerificationCode<String> sCode = ((DefaultSmsCodeGenerator)this.codeGenerators.get(DefaultBeanName.DEFAULT_SMS_CODE_GENERATOR_BEAN))
                    .generator(key);
            responseBoy.put("value",sCode.getContent());
            responseBoy.put("key",key);
            responseBoy.put("expire",sCode.getExpirationTime());
        }

        return HttpResponse.successSimpleResponse(responseBoy);
    }




}
