package lesson4_multithreading1;

public class DemonThread {
    public static void main(String[] args) {
//        method1();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i++);
                }
            }
        });

        /*
        Демон поток работает и существует только тогда, когда есть хотя бы один основной поток,
        а основной поток это любой поток, который не является демон потоком. Т.е. как только наш
        основной поток закончил свою работу и демон поток тоже закончил свою.
         */
        t2.setDaemon(true);
        t2.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End calculator");

    }

    private static void method1() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                }
            }
        });

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End calculator");
    }
}