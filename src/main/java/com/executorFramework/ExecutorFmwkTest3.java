package com.executorFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorFmwkTest3 {
    /*
     ExecutorService ->
                    .submit(Runnable r)
                    .submit(Callable c)
                    .submit(Runnable, return value in Future<T>)
                    .shutdown()
                    .shutdownNow()
                    .awaitTermination(exec, time)
                    .isShutdown()
                    .isTerminated()
                    .invokeAll(collection of callable tasks) //Executes the given tasks, returning a list of Futures holding their status and results when all complete or the timeout expires, whichever happens first.
                    .invokeAny() //return any task result which is completed first
    */

    public static void main(String[] args) throws InterruptedException, Exception {
        ExecutorService exec= Executors.newFixedThreadPool(4);
        //runnable cannot return a value so use 2 param submit with 1st runnable & 2nd callable
        //Submits a Runnable task for execution and returns a Future representing that task.
        // The Future's get method will return null upon successful completion.
        Future<String> submit1 = exec.submit(
                ()->{
                    System.out.println("Hellow from executor with 2 params using future");
                },
                "Success");


        //2. example of excutor 2 param
        Future<Integer> submit2 = exec.submit(() -> 1+2);
        Integer submitInt = submit2.get(); //will make main thread wait for future to return
        System.out.println("the submit2 result is : " + submitInt);

        //3. example of invokeAll(callable task list)
        List<Callable<String>> taskCallableList = new ArrayList<>();
        for (int i =1; i<=10; i++) {
            int j = i;
            //Variable used in lambda expression should be final or effectively final
            Callable<String> callable = () -> {
                Thread.sleep(1000); //can help check whether tasks are cancelled if time-out
                // reached for invokeAll(callableList of tasks, timeValue, time Chrono Unit)
                System.out.println(j +" Hellow User from Callable<String> !!");;
                return j + " Hellow User from Future<String> !!";
            };//raw use of Callable<T> is not permitted
            taskCallableList.add(callable);
        }

        List<Future<String>> futureInvokeAll =  exec.invokeAll(taskCallableList , 1, TimeUnit.SECONDS);
        //block the main thread, waiting for all tasks to complete
        //can also have a timeout duration set as a safety fuse!! incomplete tasks will be cancelled
        for (Future<String> f : futureInvokeAll) {
            try {
                System.out.println(f.get()); //this is a waiting operation, be careful !!
                //can future.get() can throw java.util.concurrent.CancellationException & also go into race condition
            } catch (CancellationException | InterruptedException ce) {
                System.out.println(ce.getMessage());
            }
            /*
            catch (InterruptedException ie) {
                System.out.println(ie.getMessage());
            } catch (ExecutionException ee) {
                System.out.println(ee.getMessage());
            } commenting these similar exceptions
            Thrown when a thread is waiting, sleeping, or otherwise occupied, and the thread is interrupted,
            either before or during the activity. Occasionally a method may wish to test whether the current
            thread has been interrupted, and if so, to immediately throw this exception. The following code
            can be used to achieve this effect:
             */
        }

        exec.shutdown();
        System.out.println("ExecutorService is shutdown ? -> " + exec.isShutdown());

        Thread.sleep(1000); //exec service can take time to shut down
        System.out.println("ExecutorService is terminated ? -> "+ exec.isTerminated()); //will come false, give true if waited
    }

}
