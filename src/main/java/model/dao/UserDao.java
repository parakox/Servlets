package model.dao;

import model.entity.Car;
import model.entity.User;
import model.service.CarService;
import model.service.UsefulFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserDao {
    public static List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USER");
        while(resultSet.next()){
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }
    public static User getUserById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM USER WHERE ID = %d",id));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public static void createNewUser(String name,String password,Boolean administrator) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into USER(NAME,PASSWORD,ADMINISTRATOR) values ('%s', '%s', %b)", name, password, administrator));
    }
    public static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment , NAME varchar(30), PASSWORD varchar(30), ADMINISTRATOR bool)");
    }
    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        String name = resultSet.getString("NAME");
        String password = resultSet.getString("PASSWORD");
        Integer id = resultSet.getInt("ID");
        Boolean administrator = resultSet.getBoolean("ADMINISTRATOR");
        List<Car> cars = CarService.getCarsByUserId(id);
        return new User(name, id, password, administrator, cars);
    }
}
