package dao;

import model.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.DatabaseService;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarDaoTest {
    private CarDao carDao = CarDao.getInstance();

    @Test
    void testCarDao() throws SQLException, ClassNotFoundException {
        carDao.createNewCar("hello","there",1,0);
        carDao.createNewCar("sup","here",1,0);
        assertEquals(2,carDao.getCarsByUserId(1).size());
    }
}