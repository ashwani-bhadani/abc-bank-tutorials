package com.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecServ1 {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService normalThreadPool = Executors.newCachedThreadPool();
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //cached thread pool keeps thread ready & terminates on a 60 sec interval of in-activity of thread
        //dynamically adjusts the thread pool size, we do not have a limit, be careful

        //Submits a one-shot task that becomes enabled after the given delay.
        // signature: ScheduledFuture<?> schedule(Runnable command,long delay, TimeUnit unit);
        ScheduledExecutorService scheduledtask = Executors.newScheduledThreadPool(1);
        scheduledtask.schedule(
                () -> System.out.println("Runnable task beeped after 2 secs."),
                2l,
                TimeUnit.SECONDS
        );
        scheduledtask.shutdown();

        ScheduledExecutorService beeper = Executors.newScheduledThreadPool(1);
        //task will run at fixed rate irrespective of current task completed or not, on a fixed rate new task starts
        //use scheduleAtFixedDelay which starts next task at a fixed delay rate after it is completed, returns scheduledFuture
        beeper.scheduleAtFixedRate(
                ()-> System.out.println("Beep!!")     ,
                2,
                3,  //executing every 5 seconds
                TimeUnit.SECONDS
        ); //periodic task goes into queue, do not run shutdown immediately & also handle its shutdown
//        beeper.shutdown();

        ScheduledExecutorService shutdownBeeper = Executors.newScheduledThreadPool(1);
        shutdownBeeper.schedule(
                () -> {
                    System.out.println("stopping the beeper");
                    beeper.shutdown();
                },
                20,
                TimeUnit.SECONDS
        );
        Thread.sleep(5000);
        shutdownBeeper.shutdown();
    }

}
