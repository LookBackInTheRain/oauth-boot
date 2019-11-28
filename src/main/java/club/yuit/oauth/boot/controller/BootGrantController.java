package club.yuit.oauth.boot.controller;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author yuit
 * @date 2018/11/1 11:44
 *
 */
@Controller
@SessionAttributes("authorizationRequest")
public class BootGrantController {

    private BootSecurityProperties properties;

    public BootGrantController(BootSecurityProperties properties) {
        this.properties = properties;
    }

    @RequestMapping("/custom/confirm_access")
    public String getAccessConfirmation(Map<String, Object> param, HttpServletRequest request, Model model) throws Exception {

        AuthorizationRequest authorizationRequest = (AuthorizationRequest) param.get("authorizationRequest");
        if (authorizationRequest==null){
            return "redirect:"+properties.getLoginPage();
        }

        model.addAttribute("clientId", authorizationRequest.getClientId());
        model.addAttribute("scopes",authorizationRequest.getScope());

        return "base-grant";
    }

}
