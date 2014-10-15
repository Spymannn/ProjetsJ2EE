package myconnections;

import java.sql.*;
import java.util.*;

public class DBConnection {

    protected String serverName;
    protected String username;
    protected String password;
    protected String dbName;
    protected String dbPort;

    public DBConnection() {
        PropertyResourceBundle properties = (PropertyResourceBundle) PropertyResourceBundle.getBundle("resources.application");
//nom du fichier properties à utiliser
        serverName = properties.getString("hotel.DB.server");
        dbName = properties.getString("hotel.DB.database");
        username = properties.getString("hotel.DB.login");
        password = properties.getString("hotel.DB.password");
        dbPort = properties.getString("hotel.DB.port");

    }

    public DBConnection(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@//" + serverName + ":" + dbPort + "/" + dbName;
            Connection dbConnect = DriverManager.getConnection(url, username, password);
            return dbConnect;

        } catch (Exception e) {
            System.out.println("erreur de connexion " + e);
            e.printStackTrace();
            return null;
        }
    }
}
