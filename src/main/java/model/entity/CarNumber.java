package model.entity;

public class CarNumber {
    private User user;
    private String number;
    public CarNumber(User user, String number){
        this.user = user;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
