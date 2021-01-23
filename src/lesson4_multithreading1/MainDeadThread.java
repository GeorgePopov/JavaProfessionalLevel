package lesson4_multithreading1;

public class MainDeadThread {
    static  Object lock1 = new Object();
    static  Object lock2 = new Object();

    public static void main(String[] args) {
        DeadThreadOne deadThreadOne = new DeadThreadOne();
        DeadThreadTwo deadThreadTwo = new DeadThreadTwo();

        deadThreadOne.start();
        deadThreadTwo.start();
    }

    static class DeadThreadOne extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("DeadThreadOne hold lock1...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadOne wait lock2");
                synchronized (lock2) {
                    System.out.println("DeadThreadOne hold lock1 end hold lock2");
                }
            }
        }
    }

    static class DeadThreadTwo extends Thread {
        @Override
        public void run() {
            synchronized (lock2) {
                System.out.println("DeadThreadTwo hold lock2...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("DeadThreadTwo wait lock1");
                synchronized (lock1) {
                    System.out.println("DeadThreadTwo hold lock1 end hold lock2");
                }
            }
        }
    }
}