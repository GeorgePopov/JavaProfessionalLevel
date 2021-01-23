package lesson4_multithreading1.teacherExample;

// Оптимизация первого примера, круто!
public class Homework1 {
    static volatile char str = 'A';

    public static void main(String[] args) {
        final Object lock = new Object();

        class MyTask implements Runnable {
            private char b;     // current variable
            private char nextB; // nex variable

            public MyTask(char b, char nextB) {
                this.b = b;
                this.nextB = nextB;
            }

            @Override
            public void run() {
                synchronized (lock) {
                    for (int i = 0; i < 5; i++) {
                        try {
                            while (str != b)  {
                                lock.wait();
                            }
                            System.out.println(b);
                            str = nextB;
                            lock.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        new Thread(new MyTask('A', 'B')).start();
        new Thread(new MyTask('B', 'C')).start();
        new Thread(new MyTask('C', 'A')).start();
    }
}