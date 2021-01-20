package lesson3_inputoutput.threaningManual;

import java.io.*;
import java.util.Arrays;

public class SerializeDemoApp {
    private static class Cat implements Serializable {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cat " + name;
        }
    }

    public static void main(String[] args) {
        byte[] byteCat = null;
        try (ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
             ObjectOutputStream objOut = new ObjectOutputStream(byteArrOut)) {
            Cat catOut = new Cat("Sam");
            objOut.writeObject(catOut);
            byteCat = byteArrOut.toByteArray();
            System.out.println("Cat before serialize: " + catOut);
            System.out.println("Вот так выглядит кот в байтовом представлении: " + Arrays.toString(byteCat));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ByteArrayInputStream byteArrIn = new ByteArrayInputStream(byteCat);
             ObjectInputStream objIn = new ObjectInputStream(byteArrIn)) {
            Cat catIn = (Cat) objIn.readObject();
            System.out.println("А вот такого кота мы востановили из набора байтов: " + catIn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
