package lesson2_basesdata;

import java.sql.*;

public class MySQLiteTest {

    private static Connection connection;
    private static Statement statement;

    public static void connect() throws ClassNotFoundException, SQLException {
        /**
         * Class.forName() - создаём новый экземпляр драйвера (инициализация драйвера);
         * "org.sqlite.JDBC" - дайвер который будем использовать;
         * connection - реализация соединения между БД и приложением (соединение) (устанавливаем связь с нашей БД)
         * В данном примере указываем просто название БД т.к. она находтся в корне проекта, но если БД будет находится
         * в другом месте в ней нужно будет указывать полный путь (url)
         * ("Uniform Resource Locator" Унифицированный указатель ресурса);
         * statement - объект который позволяет отправлять запросы т.е. connection устанавливает связь, а statement
         * отправляет запросы из приложения в БД. В приложение возвращается ResultSet;
         */
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        statement = connection.createStatement();

    }

    public static void disconnect() {

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            connect();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, score FROM students");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }
}