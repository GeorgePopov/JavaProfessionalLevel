package lesson3_inputoutput.thraningManual;

import java.io.*;
import java.util.Arrays;

public class StreamDemoApp {
    public static void main(String[] args) {
        // !*!*! Эти классы могут быть полезны для проверки при записи или считывании данных
//        example1();
//        example2();
//        example3();

        // работа с файлами out
//        example4();
//        example5();

        // работа с файлами in
//        example6();
//        example7();
//        example8();

        // out/in data file with Buffered
//        example9();

        // dataIn/dataOut
//        example10();

        // PipedInputStream/PipedOutputStream
//        example11();

        // class SequenceInputStream
//        example12();
    }

    private static void example12() { // Exception
        try (SequenceInputStream seq = new SequenceInputStream(new FileInputStream("1.txt"), new FileInputStream("2.txt"));
             FileOutputStream out = new FileOutputStream("out.txt")) {
            int x;
            while ((x = seq.read()) != -1) {
                out.write(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example11() {
        try (PipedInputStream in = new PipedInputStream();
             PipedOutputStream out = new PipedOutputStream(in)) {
            for (int i = 0; i < 10; i++) {
                out.write(i);
            }

            int x;
            while ((x = in.read()) != -1) {
                System.out.print(x + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example10() { // примитивные типы
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("demo.txt"))) {
            out.writeInt(128);
            out.writeLong(128L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new FileInputStream("demo.txt"))) {
            System.out.println(in.readInt());
            System.out.println(in.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example9() {
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream("demo.txt"))) {
            for (int i = 0; i < 1000000; i++) {
                out.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream in = new BufferedInputStream(new FileInputStream("demo.txt"))) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.println((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example8() { // чтение будет некорректным
        try (FileInputStream in = new FileInputStream("demo.txt")) {
            while (in.read() > -1);
            System.out.print((char) in.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example7() { // чтение будет некорректным
        try (FileInputStream in = new FileInputStream("demo.txt")) {
            int x;
            while ((x = in.read()) > -1);
            System.out.print((char) x);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example6() {
        byte[] buf = new byte[20];
        try (FileInputStream in = new FileInputStream("demo.txt")) {
            int count;
            while ((count = in.read(buf)) > 0) {
                for (int i = 0; i < count; i++) {
                    System.out.print((char) buf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example5() {
        byte[] outData = new byte[1024 * 1024];
        for (int i = 0; i < outData.length; i++) {
            outData[i] = 65;
        }
        try (FileOutputStream out = new FileOutputStream("demo.txt")) {
            for (int i = 0; i <outData.length; i++) {
                out.write(outData[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example4() {
        byte[] outData = "Java".getBytes(); // описание метода getBytes() в методичке!
        try (FileOutputStream out = new FileOutputStream("demo.txt")) {
            out.write(outData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void example3() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(10);
        out.write(11);
        byte[] arr = out.toByteArray(); // записываем значение в массив
        System.out.println(Arrays.toString(arr));
    }

    private static void example2() {
        byte[] arr = {0, 127, -1};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x;
        while ((x = in.read()) != -1) {
            System.out.print(x + " ");
        }
    }

    private static void example1() {
        byte[] arr = {65, 66, 67};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x;
        while ((x = in.read()) != -1) {
            System.out.print(x + " ");
        }
    }
}
