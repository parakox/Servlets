package model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void testCar(){
        Car car1 = new Car("hahaha","hello",1,0);
        Car car2 = new Car("hahaha","hellsdo",98,99);
        assertEquals(car1,car2);
    }

}