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
public class Main {



    public static void main(String[] args) {

       Main m = new Main();
       m.tb().start("testMian");

    }

    private void self(String a){
        System.out.println("a"+a);
    }


    private TestFunction tb(){
        return this::self;
    }
}

@FunctionalInterface
interface TestFunction {
    void start(String a);
}

