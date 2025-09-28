import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private final Connection connection;
    private final Scanner sc;

    public User(Connection connection, Scanner sc){
        this.connection = connection;
        this.sc = sc;
    }
    String email;
    String password;
    public void register(){
            System.out.println("Registration Page");
            System.out.print("Full Name : ");
            String fullName = sc.nextLine();
            System.out.print("Email : ");
            String email = sc.nextLine();
            System.out.print("Password : ");
            String password = sc.nextLine();

            if(userExists(email)){
                System.out.println("User already exists ");
                return;
            }
            String register_query = "INSERT INTO user (full_name,email,password) VALUES(?,?,?)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(register_query)){
                preparedStatement.setString(1,fullName);
                preparedStatement.setString(2,email);
                preparedStatement.setString(3,password);

                int rowsInserted = preparedStatement.executeUpdate();
                if(rowsInserted > 0){
                    System.out.println("Registration Done Successfully");
                }
                else{
                    System.out.println("Registration failed !!!");
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public boolean userExists(String email){
        String userExist_query = "SELECT 1 FROM user where email = ? LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(userExist_query)){
            preparedStatement.setString(1,email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                return resultSet.next();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String login(){
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Password : ");
        String password = sc.nextLine();

        String login_query = "SELECT * FROM user where email = ? AND password = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(login_query)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return email;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
