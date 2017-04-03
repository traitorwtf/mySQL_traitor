package com.tratorwtf.db;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by traitorwtf on 03.04.2017.
 */
public class mainDB {
    private static String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private static String username = "traitor";
    private static String password = "traitor";

    public static void main(String[] args) {
        Connection connection;
        try {
            //Driver driver = new FabricMySQLDriver();
            //DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, username, password);
            Statement statement = connection.createStatement();
//            if (!connection.isClosed()){
//                System.out.println("connected to bd");
//            }
//            connection.close();
//            if (connection.isClosed()){
//                System.out.println("connection is closed");
//            }

            //statement.execute("insert into users(name, surname, age, city) values('Alex', 'Maksimova', 16, 'Glazov')");
            //statement.executeUpdate("update users set name = 'Alexandra' where name = 'Alex'");
            //statement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
    }
}
