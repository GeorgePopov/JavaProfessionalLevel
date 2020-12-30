package lesson2_basesdata.homework;

import java.sql.*;

/**
 * 1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT).
 */
public class Homework {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "scitilop88MySQL";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mysql";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection( CONNECTION_URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            connect();

//            createSchemaAndTable();
//            selectTable();
//            deleteRows("DELETE FROM dblesson2.homework_table WHERE id >= 5");

            showTable();
            showTable2();


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        disconnect();
    }

    private static void deleteRows(String command) throws SQLException {
        statement.executeUpdate(command);
    }

    private static void selectTable() throws SQLException {
        statement.executeUpdate("INSERT INTO dblesson2.homework_table (name, score) VALUES ('Ariana', 12);");
        statement.executeUpdate("INSERT INTO dblesson2.homework_table (name, score) VALUES ('Jenifer', 13);");
        statement.executeUpdate("INSERT INTO dblesson2.homework_table (name, score) VALUES ('Poll', 22);");
    }

    private static void createSchemaAndTable() throws SQLException {
        statement.executeUpdate("CREATE SCHEMA dbLesson2 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin");

        statement.executeUpdate("CREATE TABLE `dblesson2`.`new_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `score` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`));");

        statement.executeUpdate("ALTER TABLE `dblesson2`.`new_table` \n" +
                "RENAME TO  `dblesson2`.`homework_table` ;");
    }

    private static void showTable() throws SQLException {
        resultSet = statement.executeQuery("SELECT id, name, score FROM `dblesson2`.`homework_table`");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt("id") + " " + resultSet.getString(2) + " " +
                    resultSet.getString("score"));
        }
    }

    private static void showTable2() throws SQLException {
        resultSet = statement.executeQuery("SELECT name, score FROM `dblesson2`.`homework_table`" +
                " WHERE score > 15");

        System.out.println("WHERE score > 15");
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getString(2));

        }
    }
}