package club.yuit.oauth.boot.support.properities;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuit
 * @date 2018/10/19 17:34
 *
 */
@Getter
@Setter
public class BootSmsLoginProperties {

    private Integer length = 4;
    private String mobileParameterName = "mobile";
    private String codeParameterName = "code";
    private String loginProcessUrl="/authentication/mobile";
}
