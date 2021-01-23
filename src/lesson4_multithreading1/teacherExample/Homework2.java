package lesson4_multithreading1.teacherExample;

// Интересеный пример
public class Homework2 {
    static Object mon = new Object();
    static volatile char currentLetter = 'A';

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'A') {
                                mon.wait();
                            }
                            System.out.println("A");
                            currentLetter = 'B';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'B') {
                                mon.wait();
                            }
                            System.out.println("B");
                            currentLetter = 'C';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (mon) {
                            while (currentLetter != 'C') {
                                mon.wait();
                            }
                            System.out.println("C");
                            currentLetter = 'A';
                            mon.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}