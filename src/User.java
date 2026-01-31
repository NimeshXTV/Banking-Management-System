import java.sql.*;
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
            email = sc.nextLine();
            System.out.print("Password : ");
            password = sc.nextLine();

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
        String userExist_query = "SELECT 1 FROM user WHERE email = ? LIMIT 1";
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
        System.out.print("Enter your registered Email : ");
        String email = sc.nextLine();
        System.out.print("Enter your password : ");
        String password = sc.nextLine();

        String check_login_query = "SELECT 1 FROM user WHERE email = ? AND password = ? LIMIT 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(check_login_query)){
                preparedStatement.setString(1,email);
                preparedStatement.setString(2,password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) return email;
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }
}
