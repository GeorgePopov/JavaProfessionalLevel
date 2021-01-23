package lesson3_inputoutput.thraningManual;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemoApp {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("demo.txt", "r")) {
            raf.seek(2);
            System.out.println((char) raf.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
