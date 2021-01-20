package lesson3_inputoutput.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;

public class StartNIO {
}


class Test1 {
    public static void main(String[] args) {
        //Пример строки создания объекта Path пути для запуска в Windows
        Path testFilePath = Paths.get("D:\\test\\testfile.txt");
        //Вывод инормации о файле
        System.out.println("Printing file information: ");
        System.out.println("\t file name: " + testFilePath.getFileName());
        System.out.println("\t root of the path: " + testFilePath.getRoot());
        System.out.println("\t parent of the target: " + testFilePath.getParent());
        //Вывод элементов пути
        System.out.println("Printing elements of the path: ");
        for (Path element : testFilePath) {
            System.out.println("\t path element: " + element);
        }
    }
}


class Test2 {
    public static void main(String[] args) throws IOException {
        Path testFilePath = Paths.get(".\\Test");
        System.out.println("The file name is: " + testFilePath.getFileName());
        System.out.println("It's URI is: " + testFilePath.toUri());
        System.out.println("It's absolute path is: "
                + testFilePath.toAbsolutePath());
        System.out.println("It's normalized path is: "
                + testFilePath.normalize());
        //Получение другого объекта строки по нормализованному относительному пути
        Path testPathNormalized = Paths
                .get(testFilePath.normalize().toString());
        System.out.println("It's normalized absolute path is: "
                + testPathNormalized.toAbsolutePath());
        System.out.println("It's normalized real path is: "
                + testFilePath.toRealPath(LinkOption.NOFOLLOW_LINKS));
    }
}

class Test3 {
    public static void main(String[] args) {
        Path path1 = Paths.get("Test");
        Path path2 = Paths.get("/home/heorhi/workspace/OCPJP/Test");
        System.out.println("(path1.compareTo(path2) == 0) is: "
                + (path1.compareTo(path2) == 0));
        System.out.println("path1.equals(path2) is: " + path1.equals(path2));
        System.out.println("path2.equals(path1.toAbsolutePath()) is "
                + path2.equals(path1.toAbsolutePath()));
        System.out.println(path1.toAbsolutePath());
    }
}


class Test4 {
    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("Test1");
        Path path2 = Paths.get("C:\\java_3\\01072019\\Test1");
        System.out.println("Files.isSameFile(path1, path2) is: "
                + Files.isSameFile(path1, path2));
    }
}


class Test5 {
    public static void main(String[] args) {
        //Проверка для файла
        Path path = Paths.get("C:\\java_3\\01072019\\Test1\\qwe.txt");
        //Проверка для дериктории
        //Path path = Paths.get("C:\\java_3\\01072019\\Test1");
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("The file/directory " + path.getFileName()
                    + " exists");
            if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                System.out.println(path.getFileName() + " is a directory");
            } else {
                System.out.println(path.getFileName() + " is a file");
            }
        } else {
            System.out.println("The file/directory " + path.getFileName() + " does not exist");
        }
    }
}


class Test6 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\java_3\\01072019\\Test1\\qwe.txt");
        System.out.printf("Readable: %b, Writable: %b, Executable: %b ",
                Files.isReadable(path), Files.isWritable(path),
                Files.isExecutable(path));
    }
}

class Test7 {
    public static void main(String[] args) {
        Path pathSource = Paths.get("C:\\java_3\\01072019\\Test1\\qwe.txt");
        Path pathDestination = Paths.get("C:\\java_3\\01072019\\Test1\\qwe123.txt");
        try {
            Files.copy(pathSource, pathDestination);
            System.out.println("Source file copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Test8 {
    public static void main(String[] args) {
        Path pathSource = Paths.get("C:\\java_3\\01072019\\Test1\\qwe123.txt");
        try {
            Files.delete(pathSource);
            System.out.println("File deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Test9 {
    public static void main(String[] args) {
        Path pathSource = Paths.get("C:\\java_3\\01072019\\Test1\\qwe123.txt");
        try {
            Files.delete(pathSource);
            System.out.println("File deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Test10 {
    public static void main(String[] args) {
        Path pathSource = Paths.get("C:\\java_3\\01072019\\Test1\\qwe123.txt");
        try {
            Files.createFile(pathSource);
            System.out.println("File create successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class PrintFiles extends SimpleFileVisitor<Path> {

    // Print information about
    // each type of file.
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        System.out.format("Regular file: %s ", file);
        System.out.println("(" + attr.size() + "bytes)");
        return CONTINUE;
    }
    // Print each directory visited.
    @Override
    public FileVisitResult postVisitDirectory(Path dir,IOException exc) {
        System.out.format("Directory: %s%n", dir);
        return CONTINUE;
    }
}

class Test12 {
    public static void main(String[] args) {
        Path pathSource = Paths.get("C:\\java_3\\01072019\\Test1");
        try {
            Files.walkFileTree(pathSource, new PrintFiles());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}