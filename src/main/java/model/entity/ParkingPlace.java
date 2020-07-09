package model.entity;

import java.util.Objects;

public class ParkingPlace {
    private Car car;
    private Integer id;
    public ParkingPlace(Car car,Integer id){
        this.car = car;
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
