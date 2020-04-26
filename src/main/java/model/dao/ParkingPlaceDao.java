package model.dao;

import model.entity.entity.Car;
import model.entity.сonstant.Constants;
import model.entity.entity.ParkingPlace;
import model.service.CarService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceDao {
    public static Integer amount = 100;

    static {
        try {
            createTableIfNotExists();
            for (int i = 0; i < amount; i++) {
                addNewParkingPlace(i+1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<ParkingPlace> getAllParkingPlaces() throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM PARKING_PLACE");
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        while(resultSet.next()){
            parkingPlaces.add(getParkingPlaceFromResultSet(resultSet));
        }
        return parkingPlaces;
    }
    public static ParkingPlace getParkingPlaceById(Integer id) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM PARKING_PLACE WHERE ID = %d",id));
        if(resultSet.next()){
            return getParkingPlaceFromResultSet(resultSet);
        }
        return null;
    }
    public static void setParkingPlace(ParkingPlace parkingPlace) throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE PARKING_PLACE SET CAR_NUMBER = '%s' WHERE ID = %d",parkingPlace.getCar()==null ? "null" : parkingPlace.getCar().getCarNumber(),parkingPlace.getId()));
    }
   public static void createTableIfNotExists() throws SQLException {
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PARKING_PLACE(ID bigint auto_increment, CAR_NUMBER varchar(8))");
    }
    private static ParkingPlace getParkingPlaceFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = CarService.getCarByCarNumber(resultSet.getString("CAR_NUMBER"));
        Integer id = resultSet.getInt("ID");
        return new ParkingPlace(car,id);
    }
    private static void addNewParkingPlace(Integer id) throws SQLException {
        ParkingPlace parkingPlace = new ParkingPlace(null,id);
        Connection connection = DriverManager.getConnection(Constants.DB_PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into PARKING_PLACE(CAR_NUMBER) values ('%s')",parkingPlace.getCar()==null ? "null" : parkingPlace.getCar().getCarNumber()));
    }
}
