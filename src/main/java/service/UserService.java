package service;

import dao.AbstractUserDao;
import dao.UserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements AbstractUserService{

    private static UserService userService = new UserService(UserDao.getInstance());

    private AbstractUserDao userDao;

    public static UserService getInstance() {
        return userService;
    }

    private UserService(AbstractUserDao userDao){
        this.userDao=userDao;
    }

    public List<User> getAllUsers() throws SQLException {
            return userDao.getAllUsers();
    }
    public User getUserById(Integer id) throws SQLException {
        return userDao.getUserById(id);
    }
    public User getUserByName(String name) throws SQLException {
        return userDao.getUserByName(name);
    }
    public User getUserByNameAndPassword(String name, String password) throws SQLException {
        return userDao.getUserByNameAndPassword(name,password);
    }
    public void setUser(User user) throws SQLException {
        userDao.setUser(user);
    }
    public void createNewUser(String name,String password) throws SQLException {
        userDao.createNewUser(name,password);
    }
    public void dropTable() throws SQLException {
        userDao.dropTable();
    }
    public void createTableIfNotExists() throws SQLException {
        userDao.createTableIfNotExists();
    }
}
