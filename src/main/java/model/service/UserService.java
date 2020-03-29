package model.service;

import model.dao.UserDao;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static List<User> getUsers() {
            return UserDao.getUsers();
    }
    public static void setUsers(List<User> users) {
        UserDao.setUsers(users);
    }
    public static User getUserById(Integer id){
        return UserDao.getUserById(id);
    }
    public static void createNewUser(String name,String password,Boolean isAdministrator) throws SQLException, ClassNotFoundException {
        UserDao.createNewUser(name,password,isAdministrator);
    }
}
