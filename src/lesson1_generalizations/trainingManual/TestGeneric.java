package lesson1_generalizations.trainingManual;

public class TestGeneric<T> {
    private T obj;

    public TestGeneric(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void showType() {
        System.out.println("Type T: " + obj.getClass().getName());
    }
}

// в данном примере при получении значения не требуется приведение типов, как в предыдущем
// примере без обобщения, т.е. здесь приведение типов выполняется неявно автоматически
class GenericsDemoApp {
    public static void main(String[] args) {
        TestGeneric<String> genStr = new TestGeneric<>("Hallo");
        genStr.showType();
        System.out.println("genStr.getObj(): " + genStr.getObj());
        TestGeneric<Integer> genInt = new TestGeneric<>(140);
        genInt.showType();
        System.out.println("genInt.getObj(): " + genInt.getObj());

        int valueFromGenInt = genInt.getObj();
        String valueFromGenStr = genStr.getObj();
    }
}