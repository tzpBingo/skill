package cn.tiiis.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Component
public class AsycnTask {

    /**
     * @Async所修饰的函数不要定义为static类型，这样异步调用不会生效
     */

    public static Random random =new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做异步任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成异步任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做异步任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成异步任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务二完成");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        System.out.println("开始做异步任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println("完成异步任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("异步任务三完成");
    }
}
