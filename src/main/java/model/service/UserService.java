package model.service;

import model.dao.CarDao;
import model.dao.UserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static List<User> getAllUsers() throws SQLException, ClassNotFoundException {
            return UserDao.getAllUsers();
    }
    public static User getUserById(Integer id) throws SQLException, ClassNotFoundException {
        return UserDao.getUserById(id);
    }
    public static void setUser(User user) throws SQLException, ClassNotFoundException {
        UserDao.setUser(user);
    }
    public static void createNewUser(String name,String password,Integer reputation,Boolean administrator) throws SQLException, ClassNotFoundException {
        UserDao.createNewUser(name,password,reputation,administrator);
    }
    public static void createTableIfNotExists() throws SQLException, ClassNotFoundException {
        UserDao.createTableIfNotExists();
    }
}
