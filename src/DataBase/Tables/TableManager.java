package DataBase.Tables;

import DataBase.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    public void createUserTable() {

        Connection c = DBManager.connect();

        String sql = "CREATE TABLE users ( id INTEGER IDENTITY, name VARCHAR(256) UNIQUE, email VARCHAR(256), pass VARCHAR(10))";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void dropUserTable() {

        Connection c = DBManager.connect();

        String sql = "DROP TABLE users";

        try {
            Statement s = c.createStatement();
            s.execute(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
