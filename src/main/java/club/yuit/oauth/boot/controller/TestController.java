package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.exception.NotAuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author yuit
 * @date 2018/11/1 16:44
 *
 */
@RestController
public class TestController {

    @GetMapping("/other")
    public String test() throws NoHandlerFoundException {
        throw new NoHandlerFoundException("GET","/other",null);
        //return "other";
    }

    @GetMapping("/other1")
    public String test1() throws NoHandlerFoundException {
        throw new NotAuthException("未认证");
        //return "other";
    }

}
