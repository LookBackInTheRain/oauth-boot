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


    public BaseMainController(BootSecurityProperties properties) {
        this.properties = properties;
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






}
