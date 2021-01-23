package lesson4_multithreading1.homework;

public class Main {

    private boolean suspendedA = false;
    private boolean suspendedB = false;
    private boolean suspendedC = false;

    public void suspendedMA() {
        suspendedA = true;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeA() {
        suspendedA = false;
        notify();
    }

    public void suspendedMB() {
        suspendedB= true;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeB() {
        suspendedB = false;
        notify();
    }

    public void suspendedMC() {
        suspendedC = true;
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeC() {
        suspendedC = false;
        notify();
    }

    private synchronized void methodA() {
        if (suspendedB) {
            resumeB();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("A");
            suspendedMA();
        }
    }

    private synchronized void methodB() {
        if (suspendedC) {
            resumeC();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("B");
            suspendedMB();
        }
    }

    private synchronized void methodC() {
        if (suspendedA) {
            resumeA();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("C");
            suspendedMC();
        }
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
           new Main().methodA();
        });

        Thread threadB = new Thread(() -> {
            new Main().methodB();
        });

        Thread threadC = new Thread(() -> {
            new Main().methodC();
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}