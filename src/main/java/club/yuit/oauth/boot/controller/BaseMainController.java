package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.entity.User;
import club.yuit.oauth.boot.service.IUserService;
import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

        return "base-login";
    }

}
