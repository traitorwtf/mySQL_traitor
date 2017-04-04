package dbtest;

import java.sql.*;

/**
 * Created by traitorwtf on 04.04.2017.
 */
public class DBMetaData {
    private String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private String username = "traitor";
    private String password = "traitor";

    Connection connection;
    DatabaseMetaData databaseMetaData;
    ResultSet resultSet;

    public static void main(String[] args) {
        new DBMetaData().go();
    }

    private void go() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            databaseMetaData = connection.getMetaData();

            System.out.println(databaseMetaData.getDatabaseProductName());
            System.out.println(databaseMetaData.getDatabaseProductVersion());
            System.out.println(databaseMetaData.getDriverName());
            System.out.println(databaseMetaData.getDriverVersion());
            System.out.println();
            System.out.println("**************");
            System.out.println();

            resultSet = databaseMetaData.getTables(null,null,null,null);
            while (resultSet.next()){
                System.out.println(resultSet.getString("TABLE_NAME"));
            }

            System.out.println();
            System.out.println("**************");
            System.out.println();

            resultSet = databaseMetaData.getColumns(null,null,"employees",null);
            while (resultSet.next()){
                System.out.println(resultSet.getString("COLUMN_NAME"));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
