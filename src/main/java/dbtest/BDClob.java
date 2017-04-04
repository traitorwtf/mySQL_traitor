package dbtest;

import java.io.*;
import java.sql.*;

/**
 * Created by traitorwtf on 04.04.2017.
 */
class DBClob {
    private String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private String username = "traitor";
    private String password = "traitor";

    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Statement statement2;

    String myStatement = "update employees set Text = ? where first_name = 'David'";
    String myStatement2 = "select Text from employees where id = 4";

    public static void main(String[] args) {
        new DBClob().go();
    }

    private void go() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            /*//Trying to Save file to SQL
            statement = connection.prepareStatement(myStatement);
            File file = new File("C:\\!art\\The Best Animation v.2.txt");
            FileInputStream inputStream = new FileInputStream(file);
            Reader fileReader = new FileReader(file);

            statement.setCharacterStream(1, fileReader);

            statement.executeUpdate();*/

            //Trying to Read file from SQL
            statement2 = connection.createStatement();
            resultSet = statement2.executeQuery(myStatement2);

            File file2 = new File("C:\\!art\\fromSQL.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);

            if (resultSet.next()){
                Reader reader = resultSet.getCharacterStream("Text");
                int character;
                while ((character = reader.read()) > 0){
                    fileOutputStream.write(character);
                }
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}