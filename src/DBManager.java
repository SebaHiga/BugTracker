
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//{DIR}";
    private static final String DB_NAME = "bugtracker";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            String url = "jdbc:h2:tcp://localhost//{DIR}/{NAME}";
            
            url = url.replace("{DIR}", getBaseDir());
            url = url.replace("{NAME}", DB_NAME);
            
            connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return connection;
    }

    private static String getBaseDir() {
        File currDir = new File("h2/base_de_datos/");
        return currDir.getAbsolutePath();
    }

    public static String getBasePath() {
        String url = "jdbc:h2:tcp://localhost//{DIR}/{NAME}";
        url = url.replace("{DIR}", getBaseDir());
        url = url.replace("{NAME}", DB_NAME);
        return url;
    }

}
