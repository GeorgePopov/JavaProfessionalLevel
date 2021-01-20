package lesson3_inputoutput;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class MainIO {
    public static void main(String[] args) throws IOException {

        // считывание файлов
//        readByte();
//        readArrayByte();

//        readStreamAndReader();

//        readRows();

//        conveyorStream();

//        readingMultiplyFiles();

//        BacktrackingToSpecificCharacter();
    }

    private static void BacktrackingToSpecificCharacter() throws IOException {
    /*
    классу RandomAccessFile требуется дополнительный параметр r или w или rw;
    т.е. на что мы даём разрешение на чтение, запись или оба.
    Так можно возвращаться назад
     */
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("123/test2.txt", "r")) {
            randomAccessFile.seek(3);
            System.out.println("Символ 3: " + (char) randomAccessFile.read());
        }
    }

    private static void readingMultiplyFiles() throws IOException {
        // создаём коллекцию, где каждым элементом будет InputStream
        ArrayList<InputStream> arrayListInputStream = new ArrayList<>();
        arrayListInputStream.add(new FileInputStream("123/5/test5.txt")); // добавляем файлы в коллекцию
        arrayListInputStream.add(new FileInputStream("123/5/test6.txt"));
        arrayListInputStream.add(new FileInputStream("123/5/test7.txt"));

        /*
        SequenceInputStream позволяет работать с коллекцией стримов так,
        как будто это один стрим. Работать с ним через одну точку применения
         */
        SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(arrayListInputStream));
        int x;

        while ((x = sequenceInputStream.read()) != -1) { // обращаемся к коллекции потоков как к единому целому
            System.out.print((char) x);
        }
        sequenceInputStream.close();
    }

    private static void conveyorStream() throws IOException {
    /*
    PipedInputStream и PipedOutputStream конвеерные потоки, которые создаются парами,
    в конструктор у одного из них передаётся ссылка на другой

    1 создаём входящий и исходящий потоки
    2 соеденяем потоки с помощью метода connect()
    3 помещаем данные в исходящий поток (от 0 до 99)
    4 во входящем потоки читаем эти данные и выводим на консоль
    5 закрываем потоки

    нужно это для того чтобы гарантировать, что сначала все данные в один поток запишуться
    а потом другим потоком считаются. Т.е. данные операции будут синхронизированны
    !*!*! с использованием многопоточности можно в одном потоке записать а во втором считать
     */
        PipedInputStream in = null;
        PipedOutputStream out = null;

        in = new PipedInputStream();
        out = new PipedOutputStream();

        out.connect(in); // вызываем соединение

        for (int i = 0; i < 100; i++) { // помещаем значения в исходящий поток
            out.write(i);
        }

        int x;
        while ((x = in.read()) != -1) { // считываем данные из этого потока
            System.out.println(x + " ");
        }

        in.close(); // закрываем ресурсы
        out.close();
    }

    private static void readRows() throws IOException {
        // здесь работаем со строчками
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        fileReader = new FileReader("123/test.txt"); // throws FileNotFoundException
        bufferedReader = new BufferedReader(fileReader);

        long t = System.currentTimeMillis();
        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) { // throws IOException
            System.out.println(currentLine); // т.к. здесь читаем сразу строки, используем println()
        }
        // нужно закрывать руками, если неиспользуем try with resources
        bufferedReader.close();
        fileReader.close();
        System.out.println(System.currentTimeMillis() - t);
    }

    private static void readStreamAndReader() {
        // задаём кодировку на уровне чтения строки, непонятно
        try (InputStreamReader inputStreamReader  = new InputStreamReader(new FileInputStream("123/test.txt"), "UTF-8")){
            int x;
            while ((x = inputStreamReader.read()) != -1) {
                System.out.print((char) x);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readArrayByte() {
        // чтение по массиву байт
        long t = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream("123/test.txt")) {
            byte[] arr = new byte[512]; // здесь мы говорим, что читаем кусками по 512 байт
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(System.currentTimeMillis() - t);
    }

    private static void readByte() {
        // чтение по байтам
        long t = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream("123/test.txt")) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x); // если не кастовать char то будут чары в цифровом первоначальном образе
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println(System.currentTimeMillis() - t);
    }
}