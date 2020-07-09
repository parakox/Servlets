package service;

import dao.UserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService = new UserService();

    private UserDao userDao = UserDao.getInstance();

    public static UserService getInstance() {
        return userService;
    }

    private UserService(){

    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
            return userDao.getAllUsers();
    }
    public User getUserById(Integer id) throws SQLException, ClassNotFoundException {
        return userDao.getUserById(id);
    }
    public User getUserByName(String name) throws SQLException, ClassNotFoundException {
        return userDao.getUserByName(name);
    }
    public User getUserByNameAndPassword(String name, String password) throws SQLException, ClassNotFoundException {
        return userDao.getUserByNameAndPassword(name,password);
    }
    public void setUser(User user) throws SQLException, ClassNotFoundException {
        userDao.setUser(user);
    }
    public void createNewUser(String name,String password) throws SQLException, ClassNotFoundException {
        userDao.createNewUser(name,password);
    }
}
