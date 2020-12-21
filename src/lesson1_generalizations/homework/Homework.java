package lesson1_generalizations.homework;

import java.util.*;

public class Homework<T> {

    public void task2(T[] array) {
        printArray(array);
        List<T> arrayLis = new ArrayList<>();
        Collections.addAll(arrayLis, array);
        System.out.println("Collection: " + arrayLis);
    }

    public void task1(T[] array ) {
        Scanner scanner = new Scanner(System.in);

        printArray(array);

        System.out.println("Введите первый элемент от 1 до " + array.length);
        int numFirstEl = scanner.nextInt() - 1;
        System.out.println("Введите второй элемент от 1 до " + array.length + ", за исключением " + (numFirstEl + 1));
        int numSecondEl = scanner.nextInt() - 1;

        T temporaryVar = array[numFirstEl];
        array[numFirstEl] = array[numSecondEl];
        array[numSecondEl] = temporaryVar;

        if (numFirstEl == numSecondEl) {
            System.out.println("Вы дважды ввели один и тот же аргуммент");
            return;
        }

        printArray(array);
    }

    private void printArray(T[] array) {
        System.out.println("Array:");
        for (T el : array) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] array = {"A", "B", "C", "D", "E", "F", "G"};
        new Homework<String>().task1(array);

        Float[] arrayF = {1.0f, 4.0f, 7.0f};
        new Homework<Float>().task2(arrayF);
    }
}
