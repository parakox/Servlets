package model.entity;

import java.util.Map;
import java.util.Objects;

public class Car {
    public static final Map<String,Integer> PRICE= Map.of("Mercedes",1000,"Bmv",500,"Zaporozhets",9999);

    private String carNumber;
    private String name;
    private Boolean parked;
    private Integer userId;
    private Integer parkingPlaceId;
    public Car(String carNumber,String name,Boolean parked,Integer userId,Integer parkingPlaceId){
        this.carNumber=carNumber;
        this.name = name;
        this.parked = parked;
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

    public Boolean isParked() {
        return parked;
    }

    public void setParked(Boolean parked) {
        this.parked = parked;
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
        return "name : "+this.name+
                ", car number : "+this.carNumber+
                ", parked : "+this.parked +
                (this.parked ? ", parking place id : " +
                        this.parkingPlaceId : "");
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
