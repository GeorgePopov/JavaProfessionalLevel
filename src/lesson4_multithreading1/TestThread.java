package lesson4_multithreading1;

public class TestThread {
    public synchronized void m1() { // здесь синхронизация по экземпляру класса, т.к. метод принадлежит экз класса
        System.out.println("M1");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("M2");
    }
}

class Main {
    public static void main(String[] args) {
        TestThread e1 = new TestThread();
        TestThread e2 = new TestThread();

        new Thread(e1::m1).start();
        new Thread(e2::m1).start();
    }
}