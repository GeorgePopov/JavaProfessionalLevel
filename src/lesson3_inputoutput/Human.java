package lesson3_inputoutput;

import java.io.Serializable;

public class Human implements Serializable {
    int age;

//    public Human(int age) {
//        this.age = age;
//    }

    public Human() {
        System.out.println("Human up");
    }
}
