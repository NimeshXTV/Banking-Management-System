import com.mysql.cj.conf.ConnectionPropertiesTransform;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner sc;
    public User(Connection connection, Scanner sc){
        this.connection = connection;
        this.sc = sc;
    }
    String email;
    String password;
    public void register(){
        try{
            String query = "INSERT INTO user (full_name,email,password) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println("Registration Page");
            System.out.print("Full Name : ");
            String fullName = sc.nextLine();
            System.out.print("Email : ");
            String email = sc.nextLine();
            System.out.print("Password : ");
            String password = sc.nextLine();

            preparedStatement.setString(1,fullName);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean userExists(String email){
        String query = "SELECT 1 FROM user where email = ? LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
