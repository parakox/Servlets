package dao;

import model.entity.Car;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CarDaoTest {
    private static CarDao carDao = CarDao.getInstance();

    @AfterAll
    public static void afterAll() throws SQLException {
        carDao.dropTable();
        carDao.createTableIfNotExists();
    }

    @BeforeEach
    public void beforeEach() throws SQLException {
        carDao.dropTable();
        carDao.createTableIfNotExists();
        carDao.createNewCar("hello","there",1,0);
    }

    @Test
    void getInstance() {
    }

    @Test
    void getCarsByUserId() throws SQLException {
        assertEquals(1,carDao.getCarsByUserId(1).size());
    }

    @Test
    void getCarByCarNumber() throws SQLException {
        assertNotEquals(null,carDao.getCarByCarNumber("hello"));
    }

    @Test
    void setCar() throws SQLException {
        Car car = carDao.getCarByCarNumber("hello");
        car.setName("dada");
        carDao.setCar(car);
        assertNotEquals(car.getName(),carDao.getCarByCarNumber("hello"));
    }

    @Test
    void createNewCar() throws SQLException {
        carDao.createNewCar("ssss","xima",1,0);
        assertNotEquals(null,carDao.getCarByCarNumber("ssss"));
    }

    @Test
    void deleteCar() throws SQLException {
        carDao.deleteCar(carDao.getCarByCarNumber("hello"));
        assertNull(carDao.getCarByCarNumber("hello"));
    }
}