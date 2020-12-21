package lesson1_generalizations.trainingManual;

public class TwoGen<T, V> {
    private T obj1;
    private V obj2;

    public TwoGen(T obj1, V obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public T getObj1() {
        return obj1;
    }

    public V getObj2() {
        return obj2;
    }

    public void showType() {
        System.out.println("Type T: " + obj1.getClass().getName());
        System.out.println("Type V: " + obj2.getClass().getName());
    }
}

class SimpleGenApp {
    public static void main(String[] args) {
        TwoGen<Integer, String> twoGenObj = new TwoGen<>(555, "Hallo");
        twoGenObj.showType();

        int intValue = twoGenObj.getObj1();
        String strValue = twoGenObj.getObj2();
        System.out.println(intValue + "\n" + strValue);
    }
}