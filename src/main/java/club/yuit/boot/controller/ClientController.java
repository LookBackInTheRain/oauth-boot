package club.yuit.boot.controller;

import club.yuit.boot.entity.Client;
import club.yuit.boot.response.BaseResponse;
import club.yuit.boot.response.HttpResponse;
import club.yuit.boot.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author yuit
 * @create time 2018/10/16  10:32
 * @description
 * @modify by
 * @modify time
 **/
@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public BaseResponse clientRegistered(@RequestBody @Valid Client client){

        client.setClientSecret(passwordEncoder.encode(client.getClientSecret()));


       boolean i= clientService.save(client);
       return HttpResponse.baseResponse(200);
    }

}
