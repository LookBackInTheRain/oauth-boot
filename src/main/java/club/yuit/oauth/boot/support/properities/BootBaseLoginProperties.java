package club.yuit.oauth.boot.support.properities;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2019/11/26 12:17
 **/
@Getter
@Setter
public class BootBaseLoginProperties {
    private String loginProcessUrl="/authentication/base";
    private String pictureCodeParameterName="p_code";
    private String usernameParameterName = "username";
    private String passwordParameterName = "password";
}
