package org.jee.netty.guide.unite2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BioTimeServerHandlerExecutePool {
    private ExecutorService executor;
    public BioTimeServerHandlerExecutePool(int maxPoolSize,int queneSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            maxPoolSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queneSize));

    }
    public void execute(Runnable task){
        executor.execute(task);
    }
}
