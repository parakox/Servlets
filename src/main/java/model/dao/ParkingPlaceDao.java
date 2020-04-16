package model.dao;

import model.entity.Car;
import model.entity.ParkingPlace;
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
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<ParkingPlace> getAllParkingPlaces() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM PARKING_PLACE");
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        while(resultSet.next()){
            parkingPlaces.add(getParkingPlaceFromResultSet(resultSet));
        }
        return parkingPlaces;
    }
    public static ParkingPlace getParkingPlaceById(Integer id) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        ResultSet resultSet = connection.createStatement().executeQuery(String.format("SELECT * FROM PARKING_PLACE WHERE ID = %d",id));
        if(resultSet.next()){
            return getParkingPlaceFromResultSet(resultSet);
        }
        return null;
    }
    public static void setParkingPlace(ParkingPlace parkingPlace) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate(String.format("UPDATE PARKING_PLACE SET CAR_NUMBER = '%s', EMPTY = %b WHERE ID = %d",parkingPlace.isEmpty() ? "null" : parkingPlace.getCar().getCarNumber(),parkingPlace.isEmpty(),parkingPlace.getId()));
    }
   public static void createTableIfNotExists() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PARKING_PLACE(ID bigint auto_increment , EMPTY boolean, CAR_NUMBER varchar(8))");
    }
    private static ParkingPlace getParkingPlaceFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        Boolean empty = resultSet.getBoolean("EMPTY");
        Car car = CarService.getCarByCarNumber(resultSet.getString("CAR_NUMBER"));
        Integer id = resultSet.getInt("ID");
        return new ParkingPlace(car,empty,id);
    }
    private static void addNewParkingPlace(Integer id) throws ClassNotFoundException, SQLException {
        ParkingPlace parkingPlace = new ParkingPlace(null, true,id);
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(Dao.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into PARKING_PLACE(EMPTY, CAR_NUMBER) values (%b, '%s')",parkingPlace.isEmpty(),parkingPlace.isEmpty() ? "null" : parkingPlace.getCar().getCarNumber()));
    }
}
