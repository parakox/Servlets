package dao;

import model.entity.Car;
import model.entity.ParkingPlace;
import service.CarService;
import service.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceDao {
    private Integer amount = 100;

    public static ParkingPlaceDao parkingPlace;

    static {
        try {
            parkingPlace = new ParkingPlaceDao();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ParkingPlaceDao getInstance() {
        return parkingPlace;
    }

    private CarService carService = CarService.getInstance();

    private ParkingPlaceDao() throws SQLException, ClassNotFoundException {
        createTableIfNotExists();
        try {
            for (int i = 0; i < amount; i++) {
                addNewParkingPlace(i+1);
            }
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<ParkingPlace> getAllParkingPlaces() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery("SELECT * FROM PARKING_PLACE");
        List<ParkingPlace> parkingPlaces = new ArrayList<>();
        while(resultSet.next()){
            parkingPlaces.add(getParkingPlaceFromResultSet(resultSet));
        }
        return parkingPlaces;
    }
    public ParkingPlace getParkingPlaceById(Integer id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DatabaseService.getInstance().getConnection().createStatement().executeQuery(String.format("SELECT * FROM PARKING_PLACE WHERE ID = %d",id));
        if(resultSet.next()){
            return getParkingPlaceFromResultSet(resultSet);
        }
        return null;
    }
    public void setParkingPlace(ParkingPlace parkingPlace) throws SQLException, ClassNotFoundException {
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate(String.format("UPDATE PARKING_PLACE SET CAR_NUMBER = '%s' WHERE ID = %d",parkingPlace.getCar()==null ? "null" : parkingPlace.getCar().getCarNumber(),parkingPlace.getId()));
    }

    public Integer getAmount() {
        return amount;
    }

    private ParkingPlace getParkingPlaceFromResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        Car car = carService.getCarByCarNumber(resultSet.getString("CAR_NUMBER"));
        Integer id = resultSet.getInt("ID");
        return new ParkingPlace(car,id);
    }
    private void createTableIfNotExists() throws SQLException, ClassNotFoundException {
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PARKING_PLACE(ID bigint auto_increment, CAR_NUMBER varchar(8))");
    }
    private void addNewParkingPlace(Integer id) throws SQLException, ClassNotFoundException {
        ParkingPlace parkingPlace = new ParkingPlace(null,id);
        DatabaseService.getInstance().getConnection().createStatement().executeUpdate(String.format("INSERT into PARKING_PLACE(CAR_NUMBER) values ('%s')",parkingPlace.getCar()==null ? "null" : parkingPlace.getCar().getCarNumber()));
    }
}
