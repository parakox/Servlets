package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static model.сonstant.Constants.DB_PATH;
import static model.сonstant.Constants.JDBC_DRIVER;

public class DatabaseService {
    private static DatabaseService databaseService;

    static {
        try {
            databaseService = new DatabaseService();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public static DatabaseService getInstance(){
        return databaseService;
    }
    private DatabaseService() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_PATH);
    }
    public Connection getConnection() {
        return connection;
    }
}
