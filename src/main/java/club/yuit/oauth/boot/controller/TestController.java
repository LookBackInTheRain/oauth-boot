package club.yuit.oauth.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuit
 * @date 2018/11/1 16:44
 *
 */
@RestController
public class TestController {

    @GetMapping("/other")
    public String test(){
        return "other";
    }

}
