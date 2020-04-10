package model.entity;

import model.entity.Car;

public class ParkingPlace {
    private Car car;
    private Boolean empty;
    public ParkingPlace(Car car,Boolean empty){
        this.car=car;
        this.empty = empty;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
}
