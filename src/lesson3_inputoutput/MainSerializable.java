package lesson3_inputoutput;

import java.io.*;

 /*
 Serializable - это маркерный интерфейс, который просто что либо помечает, и не заставляет переопределять методы
 Если объект подписан на интерфейс Serializable, он всегда воссоздаётся из массива байт
  */
public class MainSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        methodSerializable();
//        methodDeserializable();

        methodTransientSe();
        methodTransientDe();

    }

     private static void methodTransientDe() throws IOException, ClassNotFoundException {
         ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("stud.ser"));
         StudentBook studentBookDe = (StudentBook) objectInputStream.readObject();
         objectInputStream.close();
         studentBookDe.info();
     }

     private static void methodTransientSe() throws IOException {
         StudentBook studentBook = new StudentBook(2, "Sam");
         Book book = new Book("Hobbit");
         studentBook.setBook(book);
         ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stud.ser"));
         objectOutputStream.writeObject(studentBook);
         objectOutputStream.close();
     }

     private static void methodDeserializable() throws IOException, ClassNotFoundException {
        // это процесс десеррализации - из массива байт в объект
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("stud.ser"));
        Students studentsDe = (Students) objectInputStream.readObject();
        objectInputStream.close();
        studentsDe.info();
    }

    private static void methodSerializable() throws IOException {
        // это процесс серрализации - из объекта в массив байт
        Students students = new Students(1, "Bob");
        // "stud.ser" - придумали своё расширение, можно использовать своё
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stud.ser"));
        objectOutputStream.writeObject(students);
        objectOutputStream.close();
        // после запуска программы в корне проекта создастся файл 'stud.ser' с массивом байт
    }
}