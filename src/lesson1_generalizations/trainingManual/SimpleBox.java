package lesson1_generalizations.trainingManual;

public class SimpleBox {
    private Object object;

    public SimpleBox(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

class BoxDemoApp {
    public static void main(String[] args) {
        SimpleBox simpleBox1 = new SimpleBox(20);
        SimpleBox simpleBox2 = new SimpleBox(30);

        // делаем проверку перед приведением к типу
        if (simpleBox1.getObject() instanceof Integer && simpleBox2.getObject() instanceof Integer) {
            int sum = (Integer) simpleBox1.getObject() + (Integer) simpleBox2.getObject();
            System.out.println("sum = " + sum);
        }
        else {
            System.out.println("The contents of the boxes differ by type");
        }
    }
}