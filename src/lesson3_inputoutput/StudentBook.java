package lesson3_inputoutput;

import java.io.Serializable;

/*
 !*!*! transient - используется для поля когда нужно проигнорировать поле при десерализации;
 Используется, кода нет у объекта возможности имплементировать
 серрализацию (например когда объект находится в библиотеке);
 Поле помеченное transient при десерализации будет равно null;
 Суть в том, что передача данных будет работать, но подем мы жертвуем

 !*!*! Выход есть, если нужно сохранить объект, мы его перекладывам в другую сущьность и подписываем её на Serializable
  */
public class StudentBook extends Human implements Serializable {
    int id;
    String name;
    transient Book book;

    public StudentBook(int id, String name) {
        System.out.println("Students up");
        this.id = id;
        this.name = name;
    }

    public void info() {
        System.out.println(id + " " + name);
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
