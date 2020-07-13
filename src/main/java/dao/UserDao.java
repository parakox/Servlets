package dao;

import model.entity.Car;
import model.entity.User;
import service.CarService;
import service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static UserDao userDao;

    static {
        try {
            userDao = new UserDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getInstance(){
        return userDao;
    }

    private UserDao() throws SQLException {
        createTableIfNotExists();
    }

    private CarService carService = CarService.getInstance();

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM USER");
        while(resultSet.next()){
            users.add(getUserFromResultSet(resultSet));
        }
        return users;
    }
    public User getUserById(Integer id) throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery(String.format("SELECT * FROM USER WHERE ID = %d",id));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public User getUserByName(String name) throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery(String.format("SELECT * FROM USER WHERE NAME = '%s'",name));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery(String.format("SELECT * FROM USER WHERE NAME = '%s' AND PASSWORD = '%s'",name,password));
        if(resultSet.next()){
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    public void setUser(User user) throws SQLException {
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate(String.format("UPDATE USER SET NAME = '%s', PASSWORD = '%s' WHERE ID = %d",user.getName(),user.getPassword(),user.getId()));
    }
    public void createNewUser(String name,String password) throws SQLException {
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate(String.format("INSERT into USER(NAME,PASSWORD) values ('%s', '%s')", name, password));
    }
    public void dropTable() throws SQLException {
        DatabaseService.getInstance().getConnection().createStatement().execute("DROP TABLE USER");
    }
    public void createTableIfNotExists() throws SQLException {
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment, NAME varchar(30), PASSWORD varchar(30))");
    }
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("NAME");
        String password = resultSet.getString("PASSWORD");
        Integer id = resultSet.getInt("ID");
        List<Car> cars = carService.getCarsByUserId(id);
        return new User(name, id, password, cars);
    }
}
