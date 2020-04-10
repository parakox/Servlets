package model.dao;

import model.entity.Car;
import model.entity.ParkingPlace;
import model.service.CarService;
import model.service.UsefulFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceDao {
    static {
        try {
            createTableIfNotExists();
            for (int i = 0; i < 64; i++) {
                addNewParkingPlace(null, true);
            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<ParkingPlace> getAllParkingPlaces() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM PARKING_PLACE");
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        while(resultSet.next()){
            parkingPlaces.add(getParkingPlaceFromResultSet(resultSet));
        }
        return parkingPlaces;
    }
    public static ParkingPlace getParkingPlaceById(Integer id) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM PARKING_PLACE WHERE ID = %d",id));
        if(resultSet.next()){
            return getParkingPlaceFromResultSet(resultSet);
        }
        return null;
    }
    public static void setParkingPlaceById(Integer id,ParkingPlace parkingPlace) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE PARKING_PLACE SET CAR_NUMBER = '%s', EMPTY = %b WHERE ID = %d",parkingPlace.isEmpty() ? "null" : parkingPlace.getCar().getCarNumber(),parkingPlace.isEmpty(),id));
    }
    public static void addNewParkingPlace(Car car,Boolean empty) throws ClassNotFoundException, SQLException {
        ParkingPlace parkingPlace = new ParkingPlace(car,empty);
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into PARKING_PLACE(EMPTY, CAR_NUMBER) values (%b, '%s')",parkingPlace.isEmpty(),parkingPlace.isEmpty() ? "null" : parkingPlace.getCar().getCarNumber()));
    }
   public static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PARKING_PLACE(ID bigint auto_increment , EMPTY boolean, CAR_NUMBER varchar(8))");
    }
    private static ParkingPlace getParkingPlaceFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        Boolean empty = resultSet.getBoolean("EMPTY");
        Car car = CarService.getCarByCarNumber(resultSet.getString("CAR_NUMBER"));
        return new ParkingPlace(car,empty);
    }
}
