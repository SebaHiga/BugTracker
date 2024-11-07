import DataBase.Projects.Ticket;
import DataBase.Projects.TicketService;
import DataBase.Tables.TableManager;
import DataBase.ServiceException;
import DataBase.Users.User;
import DataBase.Users.UserService;
import Panels.MainFrame;

public class Main {
    public static void main(String[] args) {
        var tableManager = new TableManager();
        tableManager.createUsersTable();
        tableManager.createTicketTable();

        initializeDemo();

        var mainFrame = new MainFrame();
    }

    public static void initializeDemo(){

        var userService = new UserService();
        var ticketService = new TicketService();

        try{
            userService.add(new User("admin", "admin@admin.com", "password"));
            ticketService.add(new Ticket("BGT-001", "Primer bug de prueba"));
            ticketService.add(new Ticket("BGT-002", "Segundo bug de prueba"));
            ticketService.add(new Ticket("BGT-003", "Tercer bug de prueba"));
        }
        catch (ServiceException exception){
            // DataBase.Users.User admin already created
        }
    }


}