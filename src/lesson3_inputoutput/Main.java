package lesson3_inputoutput;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

// !*!*! Работа с файловой системой, основные методы
public class Main {
    public static void main(String[] args) {
//        iteratingObject();

//        iteratingFolder();

//        iteratingFilter();

//        try {
//            createFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        fileOperations();

//        renameFile();
    }

    private static void renameFile() {
        // здесь будет exception т.к. файл с таким именем существует, изначально нужно сделать exists()
        File file = new File("123/test1.txt");
//        file.renameTo("15.txt");
//        file.renameTo(new File("15.txt")); // можно так, что это непонятно
    }

    private static void fileOperations() {
        File file = new File("123/test.txt");
        file.isHidden(); // проверить скрыт ли файл?
        file.canRead(); // проверить можно ли записывать в файл
        file.canWrite(); // проверить можно ли читать файла

        // проверить существует ли файл
        File f = new File("123/test10.txt");
        if (f.exists()) System.out.println("file exists");
    }

    private static void createFile() throws IOException {
        File f = new File("123/test2.txt");
        f.createNewFile();
    }

    private static void iteratingFilter() {
        File file = new File("123");
        String[] str = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.startsWith("1");
            }
        });
        for (String name : str) {
            System.out.println(name);
        }
    }

    // с помощью file.list() через foreach перебираем содержимое папки. А так же создание директории
    private static void iteratingFolder() {
        File file = new File("123");
//        file.mkdir(); // создаёт конечную точку
        file.mkdirs(); // создаёт весь путь

        String[] str = file.list(); // возвращает содержимое в воде массива строк
        for (String name : str) {
            System.out.println(name);
        }
    }

    // с помощью file.list() через foreach перебираем содержимое файла. А так же создание директории
    private static void iteratingObject() {
        File file = new File("123/4");
//        file.mkdir(); // создаёт конечную точку
        file.mkdirs(); // создаёт весь путь

        String[] str = file.list(); // возвращает содержимое в воде массива строк
        for (String name : str) {
            System.out.println(name);
        }
    }
}