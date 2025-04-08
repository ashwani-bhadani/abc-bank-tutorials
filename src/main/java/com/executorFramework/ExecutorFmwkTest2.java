package com.executorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorFmwkTest2 {

    public static void main(String[] args) throws InterruptedException, Exception {
        long startTime = System.currentTimeMillis(); //return current time in millis since 1 Jan 1970
        ExecutorFmwkTest2 obj = new ExecutorFmwkTest2();
        //JVM can manage threads
        ExecutorService execService = Executors.newFixedThreadPool(9);

        for( int i= 10; i<19 ; i++) {
            int num = i;
            //takes runnable, callable as arg, means a lambda
            //also lambda can work with eventually final values,
            // not the one like i which changes in treads, throws error
//            execService.submit( //can use executor.execute() method, return future to check,
//            future will wait for computation to complete
            execService.execute( //main thread will also wait for this to end
                    //execute() comes from Executor interface & need to manually shutdown & release resources
                    ()->{
                        long res = 0;
                        try {
                            res = obj.factorialNum(num);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("result : "+res);
                    }
            ); ; //executor should be shutdown manually
//            execService.shutdown(); //starts orderly shutdown & stop taking new tasks,
            // main  thread will complete tough, so use executor.awaitTermination
        }

        //manual way of managing threads, waiting for each thread to complete

        System.out.println("total time: "+ (System.currentTimeMillis()-startTime));
    }

//    long factorialNum(int number) {
//        for(i = number; i >0 ; i--) {
//            number*=i ;
//        }
//        return number ;
//    }

    long factorialNum(int num) throws InterruptedException {
        Thread.sleep(100);
        if(num <=1 ) return 1;
        return num * factorialNum(num - 1);
    }

}
