package com.tratorwtf.db;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by traitorwtf on 03.04.2017.
 */
public class mainDB {
    private static String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private static String username = "traitor";
    private static String password = "traitor";

    public static void main(String[] args) {
        Connection connection;
        String DELETE = "delete from users where id = ?";

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

//            statement.execute("insert into users(name, surname, age, city) values('Alex', 'Maksimova', 16, 'Glazov')");
//            statement.executeUpdate("update users set name = 'Alexandra' where name = 'Alex'");
            ResultSet resultSet = statement.executeQuery("Select * from users");

//            statement.addBatch("insert into users(name, surname, age, city) values('Name1', 'Surname1', 66, 'City1')");
//            statement.addBatch("insert into users(name, surname, age, city) values('Name2', 'Surname2', 66, 'City2')");
//            statement.executeBatch();
//            statement.clearBatch();

//            statement.getConnection();
//            System.out.println(statement.isClosed());
//            statement.close();
//            System.out.println(statement.isClosed());

            ArrayList<User> users = new ArrayList<User>();

            while (resultSet.next()){
                User user = new User();
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city");

                user.setId(id);
                user.setName(name);
                user.setSurname(surname);
                user.setAge(age);
                user.setCity(city);

                users.add(user);
            }

            for (User us: users) {
                System.out.println(us.toString());
            }

            // Использование prepareStatemnet'ов.
//            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
//            preparedStatement.setInt(1, 10);
//            preparedStatement.executeUpdate();

            connection.close();

        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }

    }
}
