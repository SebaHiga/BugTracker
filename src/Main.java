import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        var mainFrame = new MainFrame();
//        mainFrame.mostrar();

        TableManager tm = new TableManager();
        tm.createUserTable();


        UserDAO dao = new UserDAOH2Impl();

        String user = "user1";
        String email = "email1";
        String pass = "pass1";
        User aInsertar = new User();
        aInsertar.setName(user);
        aInsertar.setEmail(email);
        aInsertar.setPass(pass);
        try {
            dao.userCreate(aInsertar);
        } catch (ExceptionObjectDuplicated e) {
            System.out.println("EL USUARIO "+user+"YA EXISTE");
        }



        String userOtro = "userOtro";
        String emailOtro = "emailOtro";
        String passOtro = "passOtro";
        User aInsertarOtro = new User();
        aInsertarOtro.setName(userOtro);
        aInsertarOtro.setEmail(emailOtro);
        aInsertarOtro.setPass(passOtro);
        try {
            dao.userCreate(aInsertarOtro);
        } catch (ExceptionObjectDuplicated e) {
            System.out.println("EL USUARIO "+user+"YA EXISTE");
        }

        String userx = "user1";
        String emailx = "emailx";
        String passx = "passx";
        User paraEditar = new User(userx, emailx, passx);
//		try {
//			dao.crearUsuario(paraEditar);
//		} catch (ObjectoDuplicadoException e) {
//			System.out.println("EL USUARIO "+userx+"YA EXISTE");
//		}
        dao.userUpdate(paraEditar);

        System.out.println("Ahora voy a mostrar el usuario recien cargado");
        String unUser = "user1";
        User userBase = dao.getUserByName(unUser);
        System.out.println(userBase);
        System.out.println("---------");

        System.out.println("Voy a modificar un usuario");
        String user2 = "userx";
        String email2 = "email@mail.com";
        String pass2 = "password";
        User aEditar = new User(user2, email2, pass2);
        dao.userUpdate(aEditar);

        System.out.println("Tengo estos usuarios:");
        List<User> listaTodosLosUsers = dao.getUserList();
        for (User usuario : listaTodosLosUsers) {
            System.out.println(usuario);
        }
        System.out.println("------");


        System.out.println("Voy a borrar un usuario segun su username");
        try {
            dao.userDelete("user1");
        } catch (DAOException e) {
            String mensdaje = "HUBO UN PRBLEMA AL BORRAR - " + e.getMessage();
            JOptionPane.showMessageDialog(null, mensdaje);
            System.out.println("HUBO UN PRBLEMA AL BORRAR");
        }

        System.out.println("Tengo estos usuarios:");
        List<User> otraListaUsers = dao.getUserList();
        for (User u : otraListaUsers) {
            System.out.println(u);
        }
        System.out.println("------");

        tm.dropUserTable();
    }
}