import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.security.core.parameters.P;
import org.springframework.util.AntPathMatcher;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yuit
 * @create 2018/10/22 17:38
 * @description
 * @modify
 */
public class Main {



    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> res = new LinkedList<>();
        Producer p =  new Producer(res);
        Consumer c = new Consumer(res);

        Thread pth = new Thread(p);
        Thread cth = new Thread(c);

        pth.start();
        cth.start();

        pth.join();
        cth.join();

    }


}

class Producer implements Runnable {

    private Queue<Integer> res;

    public Producer(Queue<Integer> res) {
        this.res = res;
    }

    @Override
    public void run() {

       synchronized (res){
           if (res.size()>=5) {
               res.notify();
               try {
                   res.wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

           res.add(1);
           res.notify();
           try {
               Thread.sleep(1*1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

           System.out.println("Producer: "+ res);

           run();
       }

    }
}


class Consumer implements Runnable {
    private Queue<Integer> res;

    public Consumer(Queue<Integer> res) {
        this.res = res;
    }

    @Override
    public void run() {
        synchronized (res){
            if (res.size()==0) {
                res.notify();
                try {
                    res.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            res.poll();
            res.notify();
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Consumer: "+ res);

            run();
        }
    }
}
