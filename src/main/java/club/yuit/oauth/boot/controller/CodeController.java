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
import jdk.nashorn.internal.objects.annotations.Getter;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author yuit
 * @date 2019/11/27 9:27
 **/
@Controller
@RequestMapping("${boot.oauth.code-path:/auth/code}")
public class CodeController {


    private BootSecurityProperties properties;
    private Map<String, VerificationCodeGenerator> codeGenerators;

    public CodeController(BootSecurityProperties properties,  Map<String, VerificationCodeGenerator> codeGenerators) {
        this.properties = properties;
        this.codeGenerators = codeGenerators;
    }

    /**
     *
     * @param request  request
     * @param type sms/picture
     * @throws Exception ex
     */
    @RequestMapping()
    @ResponseBody
    public SimpleResponse<Map<String,Object>> codeGenerateJson(HttpServletRequest request, @RequestParam("type") String type) throws Exception {

        String tmp = type.trim();
        Map<String,Object> responseBoy= new HashMap<>();

        if (tmp.equals("picture")) {

            String key = this.properties.getBaseLogin().getPictureCodeParameterName();

            if (this.properties.getCodeStoreType()== CodeStoreType.redis){
                key = UUID.randomUUID().toString();
            }

            VerificationCode<BufferedImage> pCode = ((DefaultPictureCodeGenerator)this.codeGenerators.get(DefaultBeanName.DEFAULT_PICTURE_CODE_GENERATOR_BEAN))
                    .generator(key);

            ByteArrayOutputStream out= new ByteArrayOutputStream();

            ImageIO.write(pCode.getContent(),"png",out);

            byte[] bts =  out.toByteArray();

            String base64_code = Base64.toBase64String(bts);
            // 删除 \n \r
            base64_code = base64_code.replaceAll("\n","").replace("\r","");

            responseBoy.put("value","data:image/png;base64,"+base64_code);
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
