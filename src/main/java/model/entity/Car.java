package model.entity;

import java.util.Objects;

public class Car {

    private String carNumber;
    private String name;
    private Integer userId;
    private Integer parkingPlaceId;
    public Car(String carNumber,String name,Integer userId,Integer parkingPlaceId){
        this.carNumber=carNumber;
        this.name = name;
        this.userId = userId;
        this.parkingPlaceId = parkingPlaceId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getParkingPlaceId() {
        return parkingPlaceId;
    }

    public void setParkingPlaceId(Integer parkingPlaceId) {
        this.parkingPlaceId = parkingPlaceId;
    }

    @Override
    public String toString() {
        return "name : "+name+
                ", car number : "+carNumber+
                 ", parking place id : " + parkingPlaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Car car = (Car) o;
        return carNumber.equals(car.carNumber);
    }
    @Override
    public int hashCode() {
        return Objects.hash(carNumber);
    }
}
