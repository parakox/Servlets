package model.dao;

import model.entity.entity.Car;
import model.entity.сonstant.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static CarDao carDao = new CarDao();

    public static CarDao getCarDao() {
        return carDao;
    }

    private CarDao(){

    }

    public List<Car> getCarsByUserId(Integer id) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM CAR WHERE USER_ID = %d",id));
        List<Car> cars = new ArrayList<>();
        while(resultSet.next()){
            cars.add(getCarFromResultSet(resultSet));
        }
        return cars;
    }
    public Car getCarByCarNumber(String carNumber) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM CAR WHERE NUMBER = '%s'",carNumber));
        if(resultSet.next()){
            return getCarFromResultSet(resultSet);
        }
        return null;
    }
    public void setCar(Car car) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE CAR SET NAME = '%s', USER_ID = %d, PARKING_PLACE_ID = %d WHERE NUMBER = '%s'",car.getName(),car.getUserId(),car.getParkingPlaceId(),car.getCarNumber()));
    }
    public void createNewCar(String carNumber,String name,Integer userId,Integer parkingPlaceId) throws SQLException {
        Car car = new Car(carNumber,name,userId,parkingPlaceId);
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into CAR(NUMBER, NAME, USER_ID, PARKING_PLACE_ID) values ('%s', '%s', %d, %d)",car.getCarNumber(),car.getName(),car.getUserId(),car.getParkingPlaceId()));
    }
    public void deleteCar(Car car) throws SQLException {
        String carNumber = car.getCarNumber();
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("DELETE FROM CAR WHERE NUMBER = '%s'",carNumber));
    }
    public void createTableIfNotExists() throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CAR(NUMBER varchar(8), NAME varchar(30), USER_ID int, PARKING_PLACE_ID int)");
    }
    private Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        String carNumber = resultSet.getString("NUMBER");
        String name = resultSet.getString("NAME");
        Integer userId = resultSet.getInt("USER_ID");
        Integer parkingPlaceId = resultSet.getInt("PARKING_PLACE_ID");
        return new Car(carNumber,name,userId,parkingPlaceId);
    }
}
