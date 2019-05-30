package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.code.picture.BootPictureCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yuit
 * @date  2018/10/9  15:09
 *
 **/
@Controller
public class BaseMainController {


    @Autowired
    private BootSecurityProperties properties;

    @GetMapping("/auth/login")
    public String loginPage(Model model){

        model.addAttribute("loginProcessUrl",properties.getLoginProcessUrl());
        model.addAttribute("pictureCodeParameterName",properties.getPictureCodeParameterName());

        return "base-login";
    }

    @GetMapping("/picture_code")
    public void pictureCodeGenerate(HttpServletResponse response) throws IOException {
        BootPictureCodeGenerator.generate(response);
    }

}
