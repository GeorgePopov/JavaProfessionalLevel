package lesson3_inputoutput;

import java.io.Serializable;

public class Students extends Human implements Serializable {
    int id;
    String name;

//    public Students(int id, String name) {
//        super(10);
//        this.id = id;
//        this.name = name;
//    }


    public Students(int id, String name) {
        System.out.println("Students up");
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id + " " + name);
    }
}