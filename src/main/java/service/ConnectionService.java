package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static model.сonstant.Constants.DB_PATH;
import static model.сonstant.Constants.JDBC_DRIVER;

public class ConnectionService {
    private static ConnectionService databaseService;

    static {
        try {
            databaseService = new ConnectionService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public static ConnectionService getInstance(){
        return databaseService;
    }
    private ConnectionService() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_PATH);
    }
    public Connection getConnection() {
        return connection;
    }
}
