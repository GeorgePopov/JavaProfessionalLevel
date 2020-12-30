package lesson2_basesdata.teacherExample;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "scitilop88MySQL";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/mysql";

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            connect();
//            createTableEx();
//            dropTable();

//            clearTableEx();

            // варианты заполнения таблицы
//            transactionEx();
//            batchEx();
//            preparedStatementEX("goodsTable"); // Exception

            // ищем
//            selectEX();

            // изменяем
//            updateEX();

            // ищем диапазон
//            selectExRange();

            // откат
//            rollbackEx(); // Exception

//            deleteOneEntryEx();

            insertEx();


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        disconnect();
    }

    // fill in the table option1

    private static void transactionEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            statement.executeUpdate("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES (" + i +", 'car', 100)");
        }
        System.out.println(System.currentTimeMillis() - t);
        connection.setAutoCommit(true);
    }
    // fill in the table option2

    private static void batchEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            statement.addBatch("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES (" + i +", 'car', 100)");
        }
        statement.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }
    // fill in the table option3

    private static void preparedStatementEX(String tableName) throws SQLException {
        long t = System.currentTimeMillis();
        connection.setAutoCommit(false);
        String sql = String.format("INSERT INTO %s", tableName);
        preparedStatement = connection.prepareStatement("INSERT INTO ? (prodid, title, cost) VALUES (?, ?, ?);");
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "goodsTable");
            preparedStatement.setString(2, (i + 1) + "1");
            preparedStatement.setString(3, "car");
            preparedStatement.setInt(4, 20 + (i * 10) % 90);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.setAutoCommit(true);
        System.out.println(System.currentTimeMillis() - t);
    }

    private static void selectEX() throws SQLException {
        System.out.println("are looking for");
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        String sql = String.format("SELECT cost FROM dblesson2.goodstable WHERE prodid = '%s'", res);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("cost"));
        }
    }

    private static void updateEX() throws SQLException {
        System.out.println("change");
        Scanner scanner = new Scanner(System.in);
        String cost = scanner.nextLine();
        String id = scanner.nextLine();
        String sql = String.format("UPDATE dblesson2.goodstable SET cost = '%s' WHERE id = '%s';", cost, id);
        statement.executeUpdate(sql);
    }

    private static void selectExRange() throws SQLException {
        System.out.println("Looking in the range");
        Scanner scanner = new Scanner(System.in);
        String low = scanner.nextLine();
        String hight = scanner.nextLine();
        String sql = String.format("SELECT prodid FROM dblesson2.goodstable WHERE cost BETWEEN '%s' AND '%s' ORDER BY prodid;", low, hight);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("prodid"));
        }
    }

    private static void rollbackEx() throws SQLException {
        statement.executeUpdate("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES ('101101', 'car', 50000);");
        Savepoint savepoint1 = connection.setSavepoint();
        statement.executeUpdate("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES ('102102', 'car', 50000);");
        connection.rollback(savepoint1);
        statement.executeUpdate("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES ('103103', 'car', 50000);");
        connection.setAutoCommit(true);
    }

    private static void deleteOneEntryEx() throws SQLException {
        statement.executeUpdate("DELETE FROM dblesson2.goodstable WHERE id = 12000;");
    }

    private static void insertEx() throws SQLException {
        statement.executeUpdate("INSERT INTO dblesson2.goodstable (prodid, title, cost) VALUES ('123', '123', '123');");
    }

    private static void clearTableEx() throws SQLException {
        statement.executeUpdate("DELETE FROM dblesson2.goodstable");
    }

    private static void createTableEx() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS dblesson2.goodstable (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  prodid VARCHAR(45) NULL,\n" +
                "  title VARCHAR(45) NULL,\n" +
                "  cost INT NULL,\n" +
                "  PRIMARY KEY (id));");
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS dblesson2.goodstable");
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        statement = connection.createStatement();
    }

    private static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
