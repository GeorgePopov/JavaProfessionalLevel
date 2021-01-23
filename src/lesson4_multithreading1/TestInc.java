package lesson4_multithreading1;

// Если 1 поток успевает захватить объект и сделать инкремент, то в общем получиться последовательно-паралельная работа
public class TestInc {
    private static Integer n = new Integer(300);

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (n) {
                System.out.println("X");
                n++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (n) {
                System.out.println("Y");
                n++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (n) {
                System.out.println("Z");
                n++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}