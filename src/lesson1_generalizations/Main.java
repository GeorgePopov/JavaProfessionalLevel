package lesson1_generalizations;

import java.util.ArrayList;

// дженерик не может быть static
public class Main {
    public static void main(String[] args) {
//        partBox();
//        partBoxUltimate();
//        partBoxUltimateDouble();
//        partStats1();
//        partStats2();

//        ArrayList<Box> boxes = new ArrayList<SuperBox>(); // так делать нельзя
//        boxes.add(new SuperBox(1)); // так можно
    }

    private static void partStats2() {
        Integer[] iNum = {1, 2, 3, 4, 5};
        Double[] dNum = {1.0, 2.0, 3.0, 4.0, 5.0};

        Stats<Integer> iob = new Stats<>(iNum);
        Stats<Double> dob = new Stats<>(dNum);

        double v1 = iob.avg();
        System.out.println("avg " + v1);
        double v2 = dob.avg();
        System.out.println("avg " + v2);

        if (iob.sameAvg(dob)) {
            System.out.println("Средние значения равны!");
        }
        else {
            System.out.println("Средние значения неравны!");
        }
    }

    private static void partStats1() {
        Integer[] iNumbers = {1, 2, 3, 4, 5};

        Stats<Integer> iob = new Stats<>(iNumbers);
        double v1 = iob.avg();
        System.out.println("avg " + v1);

        Double[] dNumbers = {1.0, 2.0, 3.0, 4.0, 5.0};

        Stats<Double> dob = new Stats<>(dNumbers);
        double v2 = dob.avg();
        System.out.println("avg " + v2);


    }

    private static void partBoxUltimateDouble() {
        BoxUltimateDouble<String, Integer> bus = new BoxUltimateDouble<>("Hello", 1);
        BoxUltimateDouble<Integer, String> bui = new BoxUltimateDouble<>(1, "str");

        bus.info();
        System.out.println();
        bui.info();
    }

    private static void partBoxUltimate() {
        BoxUltimate<String> bus = new BoxUltimate<>("Hello");
        BoxUltimate<Integer> bui = new BoxUltimate<>(1);

        bus.info();
        bui.info();

        int x = 10;
        x = x + bui.getObj();
//        x = x + bus.getObj(); ошибка на этапе компиляции
    }

    private static void partBox() {
        Box box1 = new Box(1);
        Box box2 = new Box("str");
        box1.info();
        box2.info();

        int x = 10;
        x = x + (Integer) box1.getObj();
        System.out.println("x = " + x);

        x = 10;
        x = x + (Integer) box2.getObj();
        System.out.println("x = " + x);
    }
}
