import java.util.List;

public interface UsuarioDAO {

    void crearUsuario(Usuario unUsuario) throws ObjetoDuplicadoException;

    void borraUsuario(String username) throws DAOException;

    void actualizaUsuario(Usuario unUsuario);

    Usuario muestraUsuario(String username);

    List<Usuario> listaTodosLosUsuarios();

}
