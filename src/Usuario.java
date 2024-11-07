import java.util.List;

public class Usuario {

    private int id;
    private String user;
    private String email;
    private String pass;

    List<Cuenta> cuentaList;


    public Usuario() {
    }

    public Usuario(String user, String email, String pass) {
        this.user = user;
        this.pass = pass;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
