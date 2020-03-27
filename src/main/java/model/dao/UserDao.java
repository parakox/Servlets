package model.dao;

import model.entity.User;
import model.service.DatabaseService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static List<User> users;
    static{
        users = new ArrayList<>();
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        UserDao.users = users;
    }
    public static User getUserById(Integer id){
        if(id<1 || id>users.size())
            return null;
        else
            return users.get(id-1);
    }
    public static void createNewUser(String name,String password,Boolean isAdministrator) throws SQLException {
        User user = new User(name,users.size()+1,password,isAdministrator);
        Connection connection = DriverManager.getConnection(DatabaseService.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into USER(NAME,PASSWORD,ADMINISTRATOR) values ('%s', '%s', %b)", user.getName(), user.getPassword(), user.isAdministrator()));
        users.add(user);
    }
}
