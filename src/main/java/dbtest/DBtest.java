package dbtest;

import java.sql.*;

/**
 * Created by traitorwtf on 04.04.2017.
 */
public class DBtest {
    private String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private String username = "traitor";
    private String password = "traitor";

    Connection connection;
    String check = "Select * from users";
    String prepMsg = "Select * from users where age > ? and city = ?";
    String theCall = "{call increase_salaries_for_department(?, ?)}";
    String theCall2 = "{call greet_the_department(?)}";
    String theCall3 = "{call get_count_for_department(?, ?)}";
    String theCall4 = "{call get_employees_for_department(?)}";

    CallableStatement callableStatement;
    String theDepartment = "Engineering";
    int theIncreaseAmount = 10000;

    public static void main(String[] args) {
        new DBtest().go();
    }

    private void go() {
        try {
        connection = DriverManager.getConnection(URL, username, password);
        /*Statement statement = connection.createStatement();

        PreparedStatement preparedStatement = connection.prepareStatement(prepMsg);
        preparedStatement.setInt(1,15);
        preparedStatement.setString(2, "Glazov");

        statement.execute("insert into users(name, surname, age, city) values('Don', 'Kapone', 50, 'Rome')");
        ResultSet resultSet = preparedStatement.executeQuery();




        while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String city = resultSet.getString("city");

                System.out.println(id + ". " + name+ " " + surname + " - " + age + ", " + city);
        }*/

        /* //theCall-1 in param
        callableStatement = connection.prepareCall(theCall2);
        callableStatement.setString(1, theDepartment);
        callableStatement.setDouble(2, theIncreaseAmount)
        callableStatement.execute();
        */

        /* //theCall-2 in-out param
        callableStatement = connection.prepareCall(theCall2);
        callableStatement.setString(1, theDepartment);
        callableStatement.registerOutParameter(1, Types.VARCHAR);
        callableStatement.execute();

        String theResult = callableStatement.getString(1);
        System.out.println(theResult);
        */

        /* //theCall-3 out param
            callableStatement = connection.prepareCall(theCall3);
            callableStatement.setString(1, theDepartment);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();

            int theResult = callableStatement.getInt(2);
            System.out.println(theResult);
        */

         /* //theCall-4 result set param
            callableStatement = connection.prepareCall(theCall4);
            callableStatement.setString(1, theDepartment);
            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String lastname = resultSet.getString("last_name");
                String firstname = resultSet.getString("first_name");
                String email = resultSet.getString("email");

                System.out.println(id + ". " + lastname + " " + firstname + " : " + email);
            }

        */
        connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
