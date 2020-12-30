package lesson1_generalizations.homework;

/**
 * 3. Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
 * поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 * Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и
 * вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 * Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той,
 * которую подадут в compare в качестве параметра, true – если она равны по весу,
 * false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним
 * про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно,
 * в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * Не забываем про метод добавления фрукта в коробку.
 */
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
