package service;

import model.entity.Car;
import model.entity.ParkingPlace;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceServiceTest {

    private static ParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();
    private static CarService carService = CarService.getInstance();
    @AfterAll
    public static void afterAll() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
        parkingPlaceService.dropTable();
        parkingPlaceService.createTableIfNotExists();
        parkingPlaceService.init();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
        carService.createNewCar("sdsds","sss",1,2);
        parkingPlaceService.dropTable();
        parkingPlaceService.createTableIfNotExists();
        parkingPlaceService.init();
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllParkingPlaces() throws SQLException {
        assertEquals(parkingPlaceService.getAmount(), parkingPlaceService.getAllParkingPlaces().size());
    }

    @Test
    void getParkingPlaceById() throws SQLException {
        assertNull(parkingPlaceService.getParkingPlaceById(parkingPlaceService.getAmount() + 1));
    }

    @Test
    void setParkingPlace() throws SQLException {
        ParkingPlace parkingPlace = parkingPlaceService.getParkingPlaceById(2);
        Car car = carService.getCarByCarNumber("sdsds");
        parkingPlace.setCar(car);
        parkingPlaceService.setParkingPlace(parkingPlace);
        assertNotEquals(null, parkingPlaceService.getParkingPlaceById(2).getCar());
    }

    @Test
    void getAmount() {
    }
}