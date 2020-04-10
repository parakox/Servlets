package model.service;

import model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsefulFunctions {
    public static final String PATH = "jdbc:h2:mem:DB_CLOSE_DELAY=-1";
    public static final String UserID = "idOfUser";
    public static Boolean checkIfPresent(String nameOfTable,String... args) throws SQLException, ClassNotFoundException {
        String[] comparingParameters = new String[args.length/2];
        String[] tableColumns = new String[args.length/2];
        for(int i=0;i<args.length/2;i++){
            comparingParameters[i]=args[i];
            tableColumns[i]=args[i+args.length/2];
        }
        StringBuilder columns = new StringBuilder();
        for(int i=0;i<tableColumns.length;i++){
            if(i!=tableColumns.length-1)
                columns.append(tableColumns[i]).append(", ");
            else
                columns.append(tableColumns[i]).append(" ");
        }
        String query = "SELECT "+columns+"FROM "+nameOfTable;
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(query);
        while (resultSet.next()) {
            for (int i = 0; i < tableColumns.length; i++) {
                if (!resultSet.getString(tableColumns[i]).equals(comparingParameters[i]))
                    break;
                if (i == tableColumns.length - 1)
                    return true;
            }
        }
        return false;
    }
}
