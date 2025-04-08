package com.executorFramework;

public class ExecutorFmwkTest {

    public static void main(String[] args) throws InterruptedException, Exception {
        long startTime = System.currentTimeMillis(); //return current time in millis since 1 Jan 1970
        ExecutorFmwkTest obj = new ExecutorFmwkTest();
        Thread[] threads = new Thread[9];
        //if the below loop runs for 5 iterations, rest 4 threads will be null & cannot join, throw NPE
        //so run the loop for number of threads
        for( int i= 10; i<19 ; i++) {
            int num = i;
            threads[i-10] = new Thread(
                    () -> {
                        long result = 0;
                        try {
                            result = obj.factorialNum(num);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("result : "+ result);
                    }
            );
            threads[i-10].start();

        }

        //manual way of managing threads, waiting for each thread to complete
        for(Thread th : threads) {
            if (th != null) {
                th.join();
            }
        }

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
