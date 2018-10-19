package club.yuit.oauth.boot.support.properities;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

/**
 * @auther yuit
 * @create 2018/10/19 17:34
 * @description
 * @modify
 */
@Getter
@Setter
public class BootSmsCodeProperties {

    private Integer expirationTime = 240;
    private Integer length = 4;
    private String mobileParameter = "mobile";


}
