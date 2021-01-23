package lesson4_multithreading1.wait.notify;

// notify воскресит только последний поток, notifyAll воскресит все потоки
public class Store {
    private int product = 0;

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе " + product);
        notify();
    }


    public synchronized void put() {
        while (product >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе " + product);
        notify();
    }

    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}