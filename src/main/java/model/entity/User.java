package model.entity;

import model.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name,password;
    private Integer id;
    private Boolean administrator;
    private List<Car> cars;
    public User(String name, Integer id,String password,Boolean administrator,List<Car> cars){
        this.name = name;
        this.id = id;
        this.password = password;
        this.administrator = administrator;
        this.cars = cars;
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
    public StringBuilder createListOfCars(){
        StringBuilder listOfCars = new StringBuilder();
        for(int i=0;i<this.getCars().size();i++){
            if(i!=this.getCars().size()-1)
                listOfCars.append(this.getCars().get(i)).append(",");
            else
                listOfCars.append(this.getCars().get(i));
        }
        return listOfCars;
    }
}
