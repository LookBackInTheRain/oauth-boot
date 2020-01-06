import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.security.core.parameters.P;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

/**
 * @author yuit
 * @create 2018/10/22 17:38
 * @description
 * @modify
 */
public class Main  extends A{

    int a;


    public static void main(String[] args) {
        Main m = new Main();

        m.test1();
    }

    private void self(String a) {
        System.out.println("a" + a);
    }


    private TestFunction tb() {
        return this::self;
    }

    private void test (TestFunction c) {
        System.out.println(c);
    }

    private void test1(){
        this.test(this.tb());
    }

}

class A {

}

@FunctionalInterface
interface TestFunction {
    void start(String a);
}
