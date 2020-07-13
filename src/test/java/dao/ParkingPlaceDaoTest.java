package dao;

import model.entity.Car;
import model.entity.ParkingPlace;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.CarService;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {

    private static ParkingPlaceDao parkingPlaceDao = ParkingPlaceDao.getInstance();
    private static CarService carService = CarService.getInstance();
    @AfterAll
    public static void afterAll() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
        parkingPlaceDao.dropTable();
        parkingPlaceDao.createTableIfNotExists();
        parkingPlaceDao.init();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
        carService.createNewCar("sdsds","sss",1,2);
        parkingPlaceDao.dropTable();
        parkingPlaceDao.createTableIfNotExists();
        parkingPlaceDao.init();
    }

    @Test
    void getInstance() {
    }

    @Test
    void getAllParkingPlaces() throws SQLException {
        assertEquals(parkingPlaceDao.getAmount(),parkingPlaceDao.getAllParkingPlaces().size());
    }

    @Test
    void getParkingPlaceById() throws SQLException {
        assertNull(parkingPlaceDao.getParkingPlaceById(parkingPlaceDao.getAmount() + 1));
    }

    @Test
    void setParkingPlace() throws SQLException {
        ParkingPlace parkingPlace = parkingPlaceDao.getParkingPlaceById(2);
        Car car = carService.getCarByCarNumber("sdsds");
        parkingPlace.setCar(car);
        parkingPlaceDao.setParkingPlace(parkingPlace);
        assertNotEquals(null,parkingPlaceDao.getParkingPlaceById(2).getCar());
    }

    @Test
    void getAmount() {
    }
}