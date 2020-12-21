package lesson1_generalizations;

// Обобщённый класс, в котором могут храниться разные типы данных, но при этом этот класс
// безопасен, т.к. мы знаем о том, какой тип данных там находится, еще на этапе компиляции
public class BoxUltimate<T> { // принято ставить T от Type, но можно и другое
    private T obj;

    public BoxUltimate(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("obj " + obj);
        System.out.println("type " + obj.getClass());
    }
}
