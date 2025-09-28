import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {

    private static final String url = "";
    private static final String username = "";
    private static final String password = "";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Scanner sc = new Scanner(System.in);
            User user = new User(connection,sc);
            Accounts accounts  = new Accounts();
            AccountManager accountManager = new AccountManager();

            while(true){
                System.out.println("WELCOME TO BANKING MANAGEMENT SYSTEM");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println();
                System.out.println("Enter your choice : ");
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        user.register();
                        break;

                    case 2:
                        String email = user.login();

                    case 3:
                        System.out.println("THANKS FOR USING BANKING SYSTEM :)");
                        System.out.println("EXITING SYSTEM!");
                        return;
                }

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}