import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        Connection connection = DBconnnection.databaseConnection();
        Scanner sc = new Scanner(System.in);
        User user = new User(connection,sc);
        Accounts accounts  = new Accounts(connection,sc);
        AccountManager accountManager = new AccountManager();

        while(true){
            System.out.println("WELCOME TO BANKING MANAGEMENT SYSTEM");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Enter your choice : ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    user.register();
                    break;

                case 2:
                    String email = user.login();
                    if(email!=null){
                        System.out.println("User Logged In Successfully!");
                    }
                    else{
                        System.out.println();
                        System.out.println("User not registered. Please register first or check your password again :)");
                    }
                    break; //Add break or check again about the gateway to let the user control his transactions or his details;

                case 3:
                    System.out.println("THANKS FOR USING BANKING SYSTEM :)");
                    System.out.println("EXITING SYSTEM!");
                    return;
            }

        }
    }
}