package club.yuit.oauth.boot.config;

import club.yuit.oauth.boot.support.BootSecurityProperties;
import club.yuit.oauth.boot.support.code.BootCodeService;
import club.yuit.oauth.boot.support.code.RedisCodeService;
import club.yuit.oauth.boot.support.code.SessionCodeService;
import club.yuit.oauth.boot.support.properities.BootBaseLoginProperties;
import club.yuit.oauth.boot.support.properities.CodeStoreType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuit
 * @date  2018/10/9  15:08
 **/
@Configuration
@EnableSwagger2
public class CoreConfig extends WebMvcConfigurationSupport {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");

        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/statics/favicon.ico");
    }

    /**
     * Could not resolve view with name 'forward:/oauth/confirm_access' in servlet with name 'dispatcherServlet'
     *
     */
    /*@Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(new InternalResourceViewResolver());
    }*/

    @Bean
    public Docket docket() {

        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();

        builder.name("Authorization").description("token").modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(builder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(parameters)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("club.yuit.oauth.boot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Boot API")
                .description("创建于 2018-10-12")
                .contact(new Contact("yuit", "", "1239964852g@gmail.com"))
                .version("1.0")
                .build();
    }


    @Bean
    public BootCodeService codeService(StringRedisTemplate template, BootSecurityProperties properties){
        if (properties.getCodeStoreType() == CodeStoreType.redis) {
            return  new RedisCodeService(template,properties.getCodeExpireTime());
        }else {
            return   new SessionCodeService();
        }
    }




}
