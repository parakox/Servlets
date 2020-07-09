package model.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String password;
    private Integer id;
    private List<Car> cars;
    public User(String name, Integer id,String password,List<Car> cars){
        this.name = name;
        this.id = id;
        this.password = password;
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

    public String getPassword(){
        return password;
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
