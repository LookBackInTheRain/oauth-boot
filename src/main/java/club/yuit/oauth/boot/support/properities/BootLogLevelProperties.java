package club.yuit.oauth.boot.support.properities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.Serializable;

/**
 * @auther yuit
 * @create 2018/10/19 16:30
 * @description
 * @modify
 */
@Getter
@Setter
public class BootLogLevelProperties implements Serializable {

    private String level = "INFO";

}
