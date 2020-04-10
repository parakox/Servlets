package model.entity;

public class Car {
    private String carNumber,name;
    private Boolean parked;
    private Integer userId,parkingPlaceId;
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

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParkingPlaceId() {
        return parkingPlaceId;
    }

    public void setParkingPlaceId(Integer parkingPlaceId) {
        this.parkingPlaceId = parkingPlaceId;
    }

    @Override
    public String toString() {
        return "name : "+this.name+", car number : "+this.carNumber+", parked : "+this.parked + (this.parked ? ", parking place id : "+this.parkingPlaceId : "");
    }
}
