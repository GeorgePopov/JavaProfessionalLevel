package lesson3_inputoutput.threaningManual;

import java.io.*;
/*
Классы InputStreamReader и OutputStreamWriter могут производить преобразование символов,
используя различные кодировки, которые задаются при конструировании потока.
 */
public class WriterAndReaderDemoApp {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("demo.txt"))) {
            for (int i = 0; i < 20; i++) {
                writer.write("Java\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader =new BufferedReader(new FileReader("demo.txt"))) {
            String str;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
