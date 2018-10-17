package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.entity.User;
import club.yuit.oauth.boot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yuit
 * @create time 2018/10/9  15:09
 * @description
 * @modify by
 * @modify time
 **/
@RestController
public class MainController {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("index")
    public String index(){

        List<User> users= this.userService.getUsers();

        this.logger.info(users.toString());

        return "index:"+users.toString();
    }

    @GetMapping("/other")
    public String other(){
        return "other";
    }



}
