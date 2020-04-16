package model.entity;

import model.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String password;
    private Integer id;
    private Integer reputation;
    private Boolean administrator;
    private List<Car> cars;
    public User(String name, Integer id,Integer reputation,String password,Boolean administrator,List<Car> cars){
        this.name = name;
        this.id = id;
        this.reputation = reputation;
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

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public String getPassword(){
        return password;
    }

    public Boolean isAdministrator() {
        return administrator;
    }

    public List<Car> getCars() {
        return cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
