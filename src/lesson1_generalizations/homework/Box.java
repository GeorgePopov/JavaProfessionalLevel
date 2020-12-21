package lesson1_generalizations.homework;

import java.util.*;

public class Box<T extends Fruit> {

    List<T> boxWithFruits = new ArrayList<>();

    public void addFruit(T fruit) {
        boxWithFruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0;

        for (T fruit : boxWithFruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public String view() {
        return (String.valueOf(boxWithFruits));
    }

    public boolean compare(Box<?> anotherBox) {
        return Box.this.getWeight() == anotherBox.getWeight();
    }

    public void poutInBox(Box<T> newBox) {
        for (T fruit : boxWithFruits) {
            newBox.addFruit(fruit);
        }
        boxWithFruits.clear();
    }

}