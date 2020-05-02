package model.service;

import model.dao.UserDao;
import model.entity.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService = new UserService();

    public static UserService getUserService() {
        return userService;
    }

    private UserService(){

    }

    private UserDao userDao = UserDao.getUserDao();

    public List<User> getAllUsers() throws SQLException {
            return userDao.getAllUsers();
    }
    public User getUserById(Integer id) throws SQLException {
        return userDao.getUserById(id);
    }
    public User getUserByName(String name) throws SQLException{
        return userDao.getUserByName(name);
    }
    public User getUserByNameAndPassword(String name, String password) throws SQLException{
        return userDao.getUserByNameAndPassword(name,password);
    }
    public void setUser(User user) throws SQLException {
        userDao.setUser(user);
    }
    public void createNewUser(String name,String password) throws SQLException {
        userDao.createNewUser(name,password);
    }
    public void createTableIfNotExists() throws SQLException {
        userDao.createTableIfNotExists();
    }
}
