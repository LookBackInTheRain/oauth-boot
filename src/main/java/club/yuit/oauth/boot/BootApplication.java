package club.yuit.oauth.boot;

import club.yuit.oauth.boot.support.oauth2.BootClientDetailsService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

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
