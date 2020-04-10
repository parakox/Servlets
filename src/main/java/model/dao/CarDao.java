package model.dao;

import model.entity.Car;
import model.entity.ParkingPlace;
import model.entity.User;
import model.service.ParkingPlaceService;
import model.service.UsefulFunctions;
import model.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class CarDao {

    public static List<Car> getAllCars() throws SQLException, ClassNotFoundException {
        List<Car> cars = new ArrayList<>();
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CAR");
        while(resultSet.next()){
            cars.add(getCarFromResultSet(resultSet));
        }
        return cars;
    }
    public static List<Car> getCarsByUserId(Integer id) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM CAR WHERE USER_ID = %d",id));
        List<Car> cars = new ArrayList<>();
        while(resultSet.next()){
            cars.add(getCarFromResultSet(resultSet));
        }
        return cars;
    }
    public static Car getCarByCarNumber(String carNumber) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM CAR WHERE NUMBER = '%s'",carNumber));
        if(resultSet.next()){
            return getCarFromResultSet(resultSet);
        }
        return null;
    }

    public static void createNewCar(String carNumber,String name,Boolean parked,Integer userId,Integer parkingPlaceId) throws SQLException, ClassNotFoundException {
        Car car = new Car(carNumber,name,parked,userId,parkingPlaceId);
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into CAR(NUMBER, NAME, USER_ID, PARKED, PARKING_PLACE_ID) values ('%s', '%s', %d, %b, %d)",carNumber,car.getName(),car.getUserId(),car.isParked(),car.getParkingPlaceId()));
    }
    public static void deleteCar(Car car) throws SQLException, ClassNotFoundException {
        String carNumber = car.getCarNumber();
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("DELETE FROM CAR WHERE NUMBER = '%s'",carNumber));
    }
    public static void parkCar(Car car,Integer idOfParkingPlace) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE CAR SET PARKED = %b, PARKING_PLACE_ID = %d WHERE NUMBER = '%s'",true,idOfParkingPlace,car.getCarNumber()));
        ParkingPlaceService.setParkingPlaceById(idOfParkingPlace,new ParkingPlace(car,false));
    }
    public static void unparkCar(Car car) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE CAR SET PARKED = %b, PARKING_PLACE_ID = %d WHERE NUMBER = '%s'",false,-1,car.getCarNumber()));
        ParkingPlaceService.setParkingPlaceById(car.getParkingPlaceId(),new ParkingPlace(null,true));
    }
    public static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CAR(ID bigint auto_increment , NUMBER varchar(8), NAME varchar(30), USER_ID int, PARKED BOOLEAN, PARKING_PLACE_ID int)");
    }
    private static Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        String carNumber = resultSet.getString("NUMBER");
        String name = resultSet.getString("NAME");
        Boolean parked = resultSet.getBoolean("PARKED");
        Integer userId = resultSet.getInt("USER_ID");
        Integer parkingPlaceId = resultSet.getInt("PARKING_PLACE_ID");
        return new Car(carNumber,name,parked,userId,parkingPlaceId);
    }
}
