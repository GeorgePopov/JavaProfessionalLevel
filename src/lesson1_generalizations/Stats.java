package lesson1_generalizations;

// в этом классе мы собираемся хранить числа разных типов данных
public class Stats<T extends Number> { // ограничение по типам
    private T[] numbers;

    public Stats(T[] numbers) {
        this.numbers = numbers;
    }

    public double avg() { // метод который будет считать среднее значение этих чисел в масииве
        double sum = 0.0;

        for (int i = 0; i <numbers.length; i++) {
            sum += numbers[i].doubleValue();
        }

        return sum / numbers.length;
    }

    // !*!*! '?' меттасимвольный аргумент - позволяет вместо '?' поставить любой
    // тип данных. Сюда мы можем поместить любой тип данных который имеется в классе
    // Stats, т.к. мы сюда помещяем класс Stats, и в данном случае типы наследники Number
    public boolean sameAvg(Stats<? /*super Integer*/> another) {
        return Math.abs(this.avg() - another.avg()) < 0.0001;
    }
    // если написать Stats<? super Integer> то ограничения будут еще и с низу, а так просто сверху
}