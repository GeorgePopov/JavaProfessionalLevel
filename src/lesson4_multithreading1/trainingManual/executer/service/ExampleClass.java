package lesson4_multithreading1.trainingManual.executer.service;

import java.util.concurrent.*;

public class ExampleClass {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        executeEx();
//        submitRunnableEx();
//        submitCallableEx();



    }

    private static void submitCallableEx() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Асинхронный вызов");
                return "Результат потока";
            }
        });
        System.out.println("future.get() = " + future.get());
        executorService.shutdown();
    }

    private static void submitRunnableEx() throws InterruptedException, java.util.concurrent.ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Асинхронная задача");
            }
        });
        future.get();
        executorService.shutdown();
    }

    private static void executeEx() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Асинхронная задача");
            }
        });
        executorService.shutdown();
    }
}
