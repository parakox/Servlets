package model.entity;

public class Car {
    private CarNumber carNumber;
    public Car(CarNumber carNumber){
        this.carNumber=carNumber;
    }

    public CarNumber getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(CarNumber carNumber) {
        this.carNumber = carNumber;
    }
}
