package dbtest;

import java.io.*;
import java.sql.*;

/**
 * Created by traitorwtf on 04.04.2017.
 */
public class DBBlob {
    private String URL = "jdbc:mysql://localhost:3306/traitor_db";
    private String username = "traitor";
    private String password = "traitor";

    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;
    Statement statement2;

    String myStatement = "update employees set Picture = ? where first_name = 'Lisa'";
    String myStatement2 = "select Picture from employees where first_name = 'Lisa'";

    public static void main(String[] args) {
        new DBBlob().go();
    }

    private void go() {
        try {
            connection = DriverManager.getConnection(URL, username, password);
            /*//Trying to Save file to SQL
            statement = connection.prepareStatement(myStatement);
            File file = new File("C:\\!art\\c885194charab2.jpg");
            FileInputStream inputStream = new FileInputStream(file);

            statement.setBinaryStream(1, inputStream);

            statement.executeUpdate();*/

            //Trying to Read file from SQL
            statement2 = connection.createStatement();
            resultSet = statement2.executeQuery(myStatement2);

            File file2 = new File("C:\\!art\\fromSQL.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);

            if (resultSet.next()){
                InputStream inputStream = resultSet.getBinaryStream("Picture");
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                while (bufferedInputStream.available()>0){
                    fileOutputStream.write(bufferedInputStream.read());
                }

                /*byte[] bytes = new byte[1024];
                while (inputStream.read(bytes)>0){
                    fileOutputStream.write(bytes);
                }*/
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