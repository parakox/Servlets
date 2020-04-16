package model.entity;

import model.entity.Car;

import java.util.Objects;

public class ParkingPlace {
    private Car car;
    private Boolean empty;
    private Integer id;
    public ParkingPlace(Car car,Boolean empty,Integer id){
        this.car = car;
        this.empty = empty;
        this.id = id;
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
    public Integer getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingPlace that = (ParkingPlace) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
