package service;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {
    private CarService carService = CarService.getInstance();

    @Test
    public void testCarService() throws SQLException, ClassNotFoundException {
        carService.createNewCar("yeeeee","boiiiii",14,0);
        assertEquals("boiiiii",carService.getCarByCarNumber("yeeeee").getName());
    }
}