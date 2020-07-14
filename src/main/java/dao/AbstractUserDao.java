package dao;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface AbstractUserDao {
    List<User> getAllUsers() throws SQLException;
    User getUserById(Integer id) throws SQLException;
    User getUserByName(String name) throws SQLException;
    User getUserByNameAndPassword(String name, String password) throws SQLException;
    void setUser(User user) throws SQLException;
    void createNewUser(String name,String password) throws SQLException;
    void dropTable() throws SQLException;
    void createTableIfNotExists() throws SQLException;
    User getUserFromResultSet(ResultSet resultSet) throws SQLException;
}
