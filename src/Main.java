import DataBase.Tables.TableManager;
import DataBase.Users.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.MainFrame;

public class Main {
    public static void main(String[] args) {
        var tableManager = new TableManager();
        tableManager.createUserTable();

        initializeDemo();

        var mainFrame = new MainFrame();
    }

    public static void initializeDemo(){

        UserService userService = new UserService();

        try{
            userService.addUser(new User("admin", "admin@admin.com", "password"));
        }
        catch (ServiceException exception){
            // DataBase.Users.User admin already created
        }

    }


}