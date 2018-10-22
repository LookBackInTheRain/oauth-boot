import org.springframework.util.AntPathMatcher;

/**
 * @author yuit
 * @create 2018/10/22 17:38
 * @description
 * @modify
 */
public class Main {

    public static void main(String[] args){

        AntPathMatcher matcher = new AntPathMatcher();

        System.out.println(matcher.match("/oauth/aa**","/oauth/aa?d=asd&asd=0"));

    }

}
