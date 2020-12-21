package lesson1_generalizations.homework;

public class TestBox {
    public static void main(String[] args) {
        // 1
        Box<Fruit> boxWithApple = new Box<>();

        for (int i = 0; i < 6; i++) {
            boxWithApple.addFruit(new Apple());
        }

        System.out.println("Box with apple = " + boxWithApple.view());
        System.out.println("Weight box with apple = " + boxWithApple.getWeight() + " kg");
        // 2
        Box<Fruit> boxWithOrange = new Box<>();

        for (int i = 0; i < 4; i++) {
            boxWithOrange.addFruit(new Orange());
        }

        System.out.println("Box with orange = " + boxWithOrange.view());
        System.out.println("Weight box with orange = " + boxWithOrange.getWeight() + " kg");
        // 3
        System.out.println("Boxes are equal in weight " + boxWithApple.compare(boxWithOrange));
        // 4
        Box<Fruit> newBoxWithOrange = new Box<>();
        boxWithOrange.poutInBox(newBoxWithOrange);

        System.out.println("New box with orange = " + newBoxWithOrange.view());
        System.out.println("Old box with orange = " + boxWithOrange.view());
    }
}
