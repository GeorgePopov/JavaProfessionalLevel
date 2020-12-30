package lesson2_basesdata;

import java.sql.*;

public class MySQLTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "scitilop88MySQL";
        String connectionURL = "jdbc:mysql://localhost:3306/mysql";

        Class.forName("com.mysql.cj.jdbc.Driver");

        // Statement - то, где создаём SQL запросы
        // !*!*! можно два действия оборачивать в t/c так
        try (Connection connection = DriverManager.getConnection(connectionURL, userName, password); // connection stream
            Statement statement = connection.createStatement()) {
//            System.out.println("Successful connection");
            // executeUpdate() - это запросы, которые будут обновлять БД
//            statement.executeUpdate("CREATE TABLE developers (firstname VARCHAR(10) not NULL, secondname VARCHAR(10))");
//            statement.executeUpdate("INSERT INTO developers (firstname, secondname) VALUES ('Jack', 'Jansen')");
//            statement.executeUpdate("INSERT INTO developers (firstname, secondname) VALUES ('Jason', 'Moon')");
//            statement.executeUpdate("INSERT INTO developers (firstname, secondname) VALUES ('Nick', 'Carol')");
//            System.out.println("Data has been successful added to the table!");

            // executeQuery() - это запросы, с помощью которых мы получаем данные из БД
            ResultSet resultSet = statement.executeQuery("SELECT * FROM developers");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }

            System.out.println("Data displayed successful!");
        }
    }
}