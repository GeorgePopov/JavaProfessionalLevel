package lesson2_basesdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

// можно было всё сделать с try/catch with resources
public class MyMain {

    private static final String userName = "root";
    private static final String password = "scitilop88MySQL";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mysql";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static PreparedStatement preparedStatement;
    private static Savepoint savepoint;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(connectionURL, userName, password);
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

//            statement.executeUpdate("insert into maindb.students (name, score) values ('bob5', 40)");
//            statement.executeUpdate("insert into maindb.students (name, score) values ('bob6', 50)");
//            statement.executeUpdate("insert into maindb.students (name, score) values ('bob7', 60)");
//            statement.executeUpdate("insert into maindb.students (name, score) values ('bob8', 70)");

            example1();
//            example2();
//            example3();
//            example4();
//            example5(); // Exception

//            try {
//                exampleReadFile();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        disconnect();
    }

    private static void example1() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM maindb.students");

        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString("name") +
                    " " + resultSet.getString("score"));
        }
    }

    private static void example2() throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println(resultSetMetaData.getColumnName(i) + " " + resultSetMetaData.getColumnType(i) +
                    " " + resultSetMetaData.getTableName(i));
        }
    }

    private static void example3() throws SQLException {
        long t =System.currentTimeMillis();

        connection.setAutoCommit(false);

        for (int i = 0; i < 1000; i++) {
            statement.executeUpdate("INSERT INTO maindb.students (name, score) VALUES ('unknown', " +
                    (i * 10) + ")");
        }

        connection.setAutoCommit(true);

        System.out.println(System.currentTimeMillis() - t);
    }

    private static void example4() throws SQLException {
        connection.setAutoCommit(false);
        preparedStatement = connection.prepareStatement("INSERT INTO maindb.students (name, score) VALUES (?, ?);");

        for (int i = 0; i < 100; i++) {
            preparedStatement.setString(1, "Bob" + i);
            preparedStatement.setInt(2, i * 10);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        connection.setAutoCommit(true);
    }

    private static void example5() throws SQLException {
        statement.executeUpdate("INSERT INTO maindb.students (name, score) VALUES ('Bob1', 10);");
        savepoint = connection.setSavepoint();
        statement.executeUpdate("INSERT INTO maindb.students (name, score) VALUES ('Bob2', 20);");
        connection.rollback(savepoint);
        statement.executeUpdate("INSERT INTO maindb.students (name, score) VALUES ('Bob3', 30);");
        connection.setAutoCommit(true);
    }

    private static void exampleReadFile() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\polit\\Desktop\\updateFile\\update.txt");
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
        String sql = String.format("UPDATE maindb.students SET score = %s where id = %s", newValue, id);
        statement.executeUpdate(sql);
    }
}