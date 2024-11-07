import java.util.List;

public class UsuarioService {

    public List<Usuario> listarUsuarios() throws ServiceException {
        UsuarioDAO d = new UsuarioDAOH2Impl();
        List<Usuario> usuarios = d.listaTodosLosUsuarios();
        return usuarios;
    }

    public void agregarUsuario(Usuario u) throws ServiceException {
        UsuarioDAO d = new UsuarioDAOH2Impl();
        try {
            d.crearUsuario(u);
        } catch (ObjetoDuplicadoException e) {
            throw new ServiceException(e);
        }
    }

    public void editarUsuario(Usuario u) throws ServiceException {
        UsuarioDAO d = new UsuarioDAOH2Impl();
        d.actualizaUsuario(u);
    }
}
