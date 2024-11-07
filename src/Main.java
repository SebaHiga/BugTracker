import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        var mainFrame = new MainFrame();
//        mainFrame.mostrar();

        TableManager tm = new TableManager();
        tm.createUserTable();


        UsuarioDAO dao = new UsuarioDAOH2Impl();

        String user = "user1";
        String email = "email1";
        String pass = "pass1";
        Usuario aInsertar = new Usuario();
        aInsertar.setUser(user);
        aInsertar.setEmail(email);
        aInsertar.setPass(pass);
        try {
            dao.crearUsuario(aInsertar);
        } catch (ObjetoDuplicadoException e) {
            System.out.println("EL USUARIO "+user+"YA EXISTE");
        }



        String userOtro = "userOtro";
        String emailOtro = "emailOtro";
        String passOtro = "passOtro";
        Usuario aInsertarOtro = new Usuario();
        aInsertarOtro.setUser(userOtro);
        aInsertarOtro.setEmail(emailOtro);
        aInsertarOtro.setPass(passOtro);
        try {
            dao.crearUsuario(aInsertarOtro);
        } catch (ObjetoDuplicadoException e) {
            System.out.println("EL USUARIO "+user+"YA EXISTE");
        }

        String userx = "user1";
        String emailx = "emailx";
        String passx = "passx";
        Usuario paraEditar = new Usuario(userx, emailx, passx);
//		try {
//			dao.crearUsuario(paraEditar);
//		} catch (ObjectoDuplicadoException e) {
//			System.out.println("EL USUARIO "+userx+"YA EXISTE");
//		}
        dao.actualizaUsuario(paraEditar);

        System.out.println("Ahora voy a mostrar el usuario recien cargado");
        String unUser = "user1";
        Usuario usuarioBase = dao.muestraUsuario(unUser);
        System.out.println(usuarioBase);
        System.out.println("---------");

        System.out.println("Voy a modificar un usuario");
        String user2 = "userx";
        String email2 = "email@mail.com";
        String pass2 = "password";
        Usuario aEditar = new Usuario(user2, email2, pass2);
        dao.actualizaUsuario(aEditar);

        System.out.println("Tengo estos usuarios:");
        List<Usuario> listaTodosLosUsuarios = dao.listaTodosLosUsuarios();
        for (Usuario usuario : listaTodosLosUsuarios) {
            System.out.println(usuario);
        }
        System.out.println("------");


        System.out.println("Voy a borrar un usuario segun su username");
        try {
            dao.borraUsuario("user1");
        } catch (DAOException e) {
            String mensdaje = "HUBO UN PRBLEMA AL BORRAR - " + e.getMessage();
            JOptionPane.showMessageDialog(null, mensdaje);
            System.out.println("HUBO UN PRBLEMA AL BORRAR");
        }

        System.out.println("Tengo estos usuarios:");
        List<Usuario> otraListaUsuarios = dao.listaTodosLosUsuarios();
        for (Usuario u : otraListaUsuarios ) {
            System.out.println(u);
        }
        System.out.println("------");

        tm.dropUserTable();
    }
}