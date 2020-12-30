package lesson2_basesdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection; // реализация(связь) между БД и application
    private static Statement stmt; // с помощью Statement мы отправляем запросы между application и БД

    private static PreparedStatement pstmt;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC"); // создаём экземпляр драйвера, эта строчка отвечает за инициализацию драйвера
        connection = DriverManager.getConnection("jdbc:sqlite:main.db"); // БД с таким именем я не создавал...
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try { // в данной ситуации t/c лучше сделать в точке входа
            connect();
            // executeQuery() - обязательно вернёт ResultSet. ResultSet - это всегда таблица
            // --1--
//            ResultSet rs = stmt.executeQuery("SELECT id, name, score, FROM students");

            // метод next() - позволяет переходить на следующую строчку,
            // он спрашивает есть ли еще строка, и возвращает true/false, и выходим из цикла.
            // getInt() у JDBC драйвера индексация начинается с 1, обращаемся по индексу
            // getString() - обращаемся по названию
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
//            }

            // --2-- rs.getMetaData() - так можно узнать мета информацию (инфу о таблице)
//            ResultSetMetaData rsmd = rs.getMetaData();

//            for (int i = 0; i <= rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i) + rsmd.getTableName(i));
//            }

            // --3--
            // executeUpdate() - возвращает кол-во строк, которые подверглись изменению
//            int res1 = stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('bob8', 70)");
//            System.out.println(res1); // res = 1 (одна строчка подверглась изменению)
//            int res2 = stmt.executeUpdate("DELETE from students");
//            System.out.println(res2); // res = 4 (четыре строчки подверглись изменению)

//            int res3 = stmt.executeUpdate("DROP TABLE IF EXISTS students"); // удаляет таблицу если она существует
//            int res4 = stmt.executeUpdate("CREATE TABLE students (\n" +
//                    "id + INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "name TEXT," +
//                    "score TEXT)");

            // --4--
            // setAutoCommit(f/t) - так мы добавляем не в каждом запросе по одному записи, а все записи одним запросом
            // !*!*! есть и обратная сторона, если на 999 записи будет Exception, то недобавиться не одна запись 
            // connection.commit() - можно еще вместо этого сделать так в конце
//            long t = System.currentTimeMillis();
//
//            connection.setAutoCommit(false);
//
//            for (int i = 0; i < 1000; i++) {
//                // !*!*! вот так можно оборачивать код
//                // executeUpdate() это команда для JDBC драйвера, не для БД ->
//                stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('unknown', " + (i * 10) + ")");
//            }
//
//            connection.setAutoCommit(true);
//
//            System.out.println(System.currentTimeMillis() - t);
//
//            // можно и так, мы добавляем в пачту и добавляем всю пачку
//            // !*!*! если боимся потерять данные цикл можно разбить на 5 по 200 операций
//            for (int i = 0; i < 1000; i++) {
//                // !*!*! вот так можно оборачивать код
//                // executeUpdate() это команда для JDBC драйвера, не для БД ->
//                stmt.addBatch("INSERT INTO students (name, score) VALUES ('unknown', " + (i * 10) + ")");
//            }
//            stmt.executeBatch();

            // --5--
            // prepareStatement() - формирование предварительного запроса, кроме INSERT другие команды
            // выполнятся небудут. Вместо '?' у нас подставляются параметры. Это защита от 'SQL инъекций'
//            connection.setAutoCommit(false);
//            pstmt = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
//
//            for (int i = 0; i < 100; i++) {
//                pstmt.setString(1, "Box" + i); // 1 параметр
//                pstmt.setInt(2, i * 10);       // 2 параметр
//                pstmt.addBatch();
//            }
//
//            pstmt.executeBatch();
//            connection.setAutoCommit(true);

            // --6--
            // если выполняется команда Savepoint - то у нас автоматически выполняется команда setAutoCommit(false);
            // если после третьего добавления добавить connection.setAutoCommit(true), то будет две записи а не одна
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 10);");
//            Savepoint sv = connection.setSavepoint(); // делаем точку сохранения
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 20);");
//            connection.rollback(sv); // откатываемся назад
//            stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 30);");
//            connection.setAutoCommit(true);

            // --7--
            // Очень круто! здесь читаем текстовый файл из ПК, обрабатываем
            // его данные и заносим их в базу, изменяя её наполенние
            try {
                readFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    private static void readFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\Users\\Artem\\Desktop\\update.txt");
        Scanner scanner = new Scanner(fileInputStream);

        while (scanner.hasNext()) { // !*!*! вот так нужно делать при чтении
            String[] mass = scanner.nextLine().split(" ");
            try {
                updateDB(mass[0], mass[1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateDB(String id, String newValue) throws SQLException {
        String sql = String.format("UPDATE students SET score = %s where id = %s", newValue, id); // можно так писать SQL запросы ?
        stmt.executeUpdate(sql);
    }
}
