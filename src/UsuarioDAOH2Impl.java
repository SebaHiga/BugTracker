import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsuarioDAOH2Impl implements UsuarioDAO {

    public void crearUsuario(Usuario unUsuario) throws ObjetoDuplicadoException {
        String user = unUsuario.getUser();
        String email = unUsuario.getEmail();
        String pass = unUsuario.getPass();

        Date d = new Date();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
//            PreparedStatement ps = c.prepareStatement("INSERT INTO usuarios (user, email, pass) VALUES (?, ?, ?)");
//            ps.setString(1, user);
//            ps.setString(2, email);
//            ps.setString(3, pass);
//
            String sql = "INSERT INTO usuarios (user, email, pass) VALUES ('" + user + "', '" + email + "', '" + pass + "')";
            s.executeUpdate(sql);
//            ps.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
                if(e.getErrorCode() == 23505) {
                    throw new ObjetoDuplicadoException();
                }
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void borraUsuario(String username) throws DAOException {
        String sql = "DELETE FROM usuarios WHERE user = '" + username + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
            }
            throw new DAOException();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
//            throw new DAOException("ATENCION NO SE PUEDO CERRAR LA CXN");
        }
    }

    public void actualizaUsuario(Usuario unUsuario) {
        String user = unUsuario.getUser();
        String email = unUsuario.getEmail();
        String pass = unUsuario.getPass();

        String sql = "UPDATE usuarios set email = '" + email + "', pass = '" + pass + "' WHERE user = '" + user + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Usuario> listaTodosLosUsuarios() {
        List<Usuario> resultado = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreUsuario = rs.getString("user");
                String mail = rs.getString("email");
                String contrasenia = rs.getString("pass");
                Usuario u = new Usuario(nombreUsuario, mail, contrasenia);
                resultado.add(u);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return resultado;
    }

    public Usuario muestraUsuario(String username) {
        String sql = "SELECT * FROM usuarios WHERE user = '" + username + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                int id = rs.getInt("id");
                String nombreUsuario = rs.getString("user");
                String mail = rs.getString("email");
                String contrasenia = rs.getString("pass");
                Usuario u = new Usuario(nombreUsuario, mail, contrasenia);
                return u;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }


}
