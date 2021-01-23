package lesson4_multithreading1;

// !*!*! паралельно-последовательная работа методов. Здесь синхранизация по объекту
public class Game {
    public static void main(String[] args) {
        final Object  стул1 = new Object();
        final Object  стул2 = new Object();

        Thread человек1 = new Thread(() -> {
            System.out.println("Человек1 подощёл к стулу1");
            synchronized (стул1) {
                System.out.println("Человек1 сел на стул1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек1 встал со стула1");
            }
        });
        человек1.start();

        Thread человек2 = new Thread(() -> {
            System.out.println("Человек2 подощёл к стулу1");
            synchronized (стул1) {
                System.out.println("Человек2 сел на стул1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек2 встал со стула1");
            }
        });
        человек2.start();

        Thread человек3 = new Thread(() -> {
            System.out.println("Человек3 подощёл к стулу2");
            synchronized (стул2) {
                System.out.println("Человек3 сел на стул2");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Человек3 встал со стула2");
            }
        });
        человек3.start();
    }
}