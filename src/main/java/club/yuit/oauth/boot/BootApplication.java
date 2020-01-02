package club.yuit.oauth.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yuit
 */
@SpringBootApplication
@MapperScan("club.yuit.oauth.boot.mapper")
public class BootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(BootApplication.class, args);
    }

}
