package model.entity;

public class Car {
    private String carNumber,name;
    private User user;
    public Car(String carNumber,String name,User user){
        this.carNumber=carNumber;
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "name : "+this.name+"; carNumber : "+this.carNumber;
    }
}
