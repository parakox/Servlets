package model.service;

import model.dao.UserDao;
import model.entity.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static List<User> getAllUsers() throws SQLException {
            return UserDao.getAllUsers();
    }
    public static User getUserById(Integer id) throws SQLException {
        return UserDao.getUserById(id);
    }
    public static User getUserByName(String name) throws SQLException{
        return UserDao.getUserByName(name);
    }
    public static User getUserByNameAndPassword(String name, String password) throws SQLException{
        return UserDao.getUserByNameAndPassword(name,password);
    }
    public static void setUser(User user) throws SQLException {
        UserDao.setUser(user);
    }
    public static void createNewUser(String name,String password) throws SQLException {
        UserDao.createNewUser(name,password);
    }
    public static void createTableIfNotExists() throws SQLException {
        UserDao.createTableIfNotExists();
    }
}
