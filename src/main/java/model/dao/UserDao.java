package model.dao;

import model.entity.Car;
import model.entity.User;
import model.service.CarService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USER");
        while(resultSet.next()){
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }
    public static User getUserById(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM USER WHERE ID = %d",id));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public static void setUser(User user) throws ClassNotFoundException, SQLException{
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE USER SET REPUTATION = %d, NAME = '%s', PASSWORD = '%s', ADMINISTRATOR = %b WHERE ID = %d",user.getReputation(),user.getName(),user.getPassword(),user.isAdministrator(),user.getId()));
    }
    public static void createNewUser(String name,String password,Integer reputation,Boolean administrator) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into USER(REPUTATION,NAME,PASSWORD,ADMINISTRATOR) values (%d, '%s', '%s', %b)", reputation, name, password, administrator));
    }
    public static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment,REPUTATION int, NAME varchar(30), PASSWORD varchar(30), ADMINISTRATOR bool)");
    }
    private static User getUserFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        String name = resultSet.getString("NAME");
        String password = resultSet.getString("PASSWORD");
        Integer id = resultSet.getInt("ID");
        Integer reputation = resultSet.getInt("REPUTATION");
        Boolean administrator = resultSet.getBoolean("ADMINISTRATOR");
        List<Car> cars = CarService.getCarsByUserId(id);
        return new User(name, id,reputation, password, administrator, cars);
    }
}
