package model.entity;

import model.entity.Car;

public class ParkingPlace {
    private Car car;
    private Double duration;
    private Boolean empty;
    public ParkingPlace(Car car, Double duration,Boolean empty){
        this.car=car;
        this.duration=duration;
        this.empty = empty;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
}
