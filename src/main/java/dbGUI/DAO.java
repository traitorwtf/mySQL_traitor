package dbGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by traitorwtf on 05.04.2017.
 */
public class DAO {

    Properties props;
    Connection connection;


    DAO(){
        try {
            props = new Properties();
            props.load(new FileInputStream("traitorDB.properties"));

            String URL = props.getProperty("URL");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(URL, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    ArrayList<User> getAllUsers() throws Exception{
        ArrayList<User> users = new ArrayList<User>();
        Statement statement = connection.createStatement();
        String query = "select * from users";

        ResultSet resultSet = statement.executeQuery(query);
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
        statement.close();
        resultSet.close();
        return users;
    }

    ArrayList<User> searchUsers(String searchSurname) throws Exception{
        ArrayList<User> users = new ArrayList<User>();
        String query = "select * from users where surname like ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        searchSurname += "%";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, searchSurname);

        resultSet = preparedStatement.executeQuery();
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
        preparedStatement.close();
        resultSet.close();
        return users;
    }
}
