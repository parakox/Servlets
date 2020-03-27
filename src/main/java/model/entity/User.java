package model.entity;

import model.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name,password;
    private Integer id;
    private Boolean administrator;
    private List<Car> cars;
    public User(String name, Integer id,String password,Boolean administrator){
        this.name = name;
        this.id = id;
        this.password = password;
        this.administrator = administrator;
        this.cars = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
