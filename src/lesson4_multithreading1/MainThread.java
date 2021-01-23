package lesson4_multithreading1;

public class MainThread {
    public static void main(String[] args) {

        MyThread t1 = new MyThread();
        t1.start();

        Thread t2 = new Thread(new MyRunnableClass());
        t2.start();

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        });

        t3.start();

//        try {
//            t1.join();
//            t2.join();
//            t3.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // замена метода join()
        while (true) {
            if (!t1.isAlive()) {
                break;
            }
        }

        while (true) {
            if (!t2.isAlive()) {
                break;
            }
        }

        while (true) {
            if (!t3.isAlive()) {
                break;
            }
        }

        System.out.println("END");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    private static class MyRunnableClass implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }
}