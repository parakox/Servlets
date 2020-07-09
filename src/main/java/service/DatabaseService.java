package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static model.сonstant.Constants.DB_PATH;

public class DatabaseService {
    private static Connection con=null;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (con != null) return con;
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(DB_PATH);
    }
}
