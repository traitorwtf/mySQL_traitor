package dbtest;

import java.sql.*;

/**
 * Created by traitorwtf on 04.04.2017.
 */
public class DBResultSetMeta {
    private String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private String username = "traitor";
    private String password = "traitor";

    Connection connection;
    ResultSetMetaData resultSetMetaData;
    ResultSet resultSet;

    public static void main(String[] args) {
        new DBResultSetMeta().go();
    }

    private void go() {
        try {
            connection = DriverManager.getConnection(URL, username, password);

            resultSet = connection.createStatement().executeQuery("select id, last_name, first_name, salary from employees");
            resultSetMetaData = resultSet.getMetaData();

            int columns = resultSetMetaData.getColumnCount();
            for (int i = 1; i < columns; i++) {
                System.out.println("Column name: " + resultSetMetaData.getColumnName(i));
                System.out.println("Column type: " + resultSetMetaData.getColumnTypeName(i));
                System.out.println("Is nullable: " + resultSetMetaData.isNullable(i));
                System.out.println("Auto-increment: " + resultSetMetaData.isAutoIncrement(i));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}