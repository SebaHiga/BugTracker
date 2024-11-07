package DataBase.Users;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String email;
    private String pass;

    List<Account> cuentaList;

    public User() {
    }

    public User(String user, String email, String pass) {
        this.name = user;
        this.pass = pass;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "DataBase.Users.User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
