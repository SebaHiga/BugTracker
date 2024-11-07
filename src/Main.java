import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var tableManager = new TableManager();
        tableManager.createUserTable();

        initializeDemo();

        var mainFrame = new MainFrame();

        mainFrame.displayLoginPane();
    }


    public static void initializeDemo(){

        UserService userService = new UserService(new UserDAOH2Impl());

        try{
            userService.addUser(new User("admin", "admin@admin.com", "password"));
        }
        catch (ServiceException exception){
            // User admin already created
        }

    }


}