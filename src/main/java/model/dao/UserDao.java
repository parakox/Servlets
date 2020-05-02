package model.dao;

import model.entity.entity.Car;
import model.entity.сonstant.Constants;
import model.entity.entity.User;
import model.service.CarService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao userDao = new UserDao();

    public static UserDao getUserDao(){
        return userDao;
    }

    private UserDao(){

    }

    private CarService carService = CarService.getCarService();

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM USER");
        while(resultSet.next()){
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }
    public User getUserById(Integer id) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM USER WHERE ID = %d",id));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public User getUserByName(String name) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM USER WHERE NAME = '%s'",name));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public User getUserByNameAndPassword(String name, String password) throws SQLException{
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM USER WHERE NAME = '%s' AND PASSWORD = '%s'",name,password));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public void setUser(User user) throws SQLException{
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE USER SET NAME = '%s', PASSWORD = '%s', WHERE ID = %d",user.getName(),user.getPassword(),user.getId()));
    }
    public void createNewUser(String name,String password) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into USER(NAME,PASSWORD) values ('%s', '%s')", name, password));
    }
    public void createTableIfNotExists() throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment, NAME varchar(30), PASSWORD varchar(30))");
    }
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("NAME");
        String password = resultSet.getString("PASSWORD");
        Integer id = resultSet.getInt("ID");
        List<Car> cars = carService.getCarsByUserId(id);
        return new User(name, id, password, cars);
    }
}
