package cn.tiiis.async;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private Task task;

    @Autowired
    private AsycnTask asycnTask;

//    @org.junit.Test
//    public void test() throws Exception {
//        task.doTaskOne();
//        task.doTaskTwo();
//        task.doTaskThree();
//    }

    @org.junit.Test
    public void asynctest() throws Exception {

        long start = System.currentTimeMillis();

        Future<String> task1 = asycnTask.doTaskOne();
        Future<String> task2 = asycnTask.doTaskTwo();
        Future<String> task3 = asycnTask.doTaskThree();

        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
