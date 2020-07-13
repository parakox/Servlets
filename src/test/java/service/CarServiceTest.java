package service;

import model.entity.Car;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private static CarService carService = CarService.getInstance();

    @AfterAll
    public static void afterAll() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        carService.dropTable();
        carService.createTableIfNotExists();
        carService.createNewCar("hello","there",1,0);
    }

    @Test
    void getInstance() {
    }

    @Test
    void getCarsByUserId() throws SQLException {
        assertEquals(1, carService.getCarsByUserId(1).size());
    }

    @Test
    void getCarByCarNumber() throws SQLException {
        assertNotEquals(null, carService.getCarByCarNumber("hello"));
    }

    @Test
    void setCar() throws SQLException {
        Car car = carService.getCarByCarNumber("hello");
        car.setName("dada");
        carService.setCar(car);
        assertNotEquals(car.getName(), carService.getCarByCarNumber("hello"));
    }

    @Test
    void createNewCar() throws SQLException {
        carService.createNewCar("ssss","xima",1,0);
        assertNotEquals(null, carService.getCarByCarNumber("ssss"));
    }

    @Test
    void deleteCar() throws SQLException {
        carService.deleteCar(carService.getCarByCarNumber("hello"));
        assertNull(carService.getCarByCarNumber("hello"));
    }
}