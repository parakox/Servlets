package model.dao;

import model.entity.Car;
import model.entity.User;
import model.service.UsefulFunctions;
import model.service.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static List<Car> cars ;
    static{
        cars = new ArrayList<>();
    }

    public static List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> users) {
        CarDao.cars = users;
    }
    public static Car getCarById(Integer id){
        if(id<1 || id>cars.size())
            return null;
        else
            return cars.get(id-1);
    }
    public static void createNewCar(String carNumber,String name,User user) throws SQLException, ClassNotFoundException {
        Car car = new Car(carNumber,name,user);
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into CAR(NUMBER, NAME, USERID) values ('%s', '%s', %d)",carNumber,car.getName(),user.getId()));
        cars.add(car);
        StringBuilder listOfCars = UsefulFunctions.createListOfCars(car.getUser());
        connection.createStatement().executeUpdate(String.format("UPDATE USER SET CARS = '%s' WHERE ID = %d",listOfCars,car.getUser().getId()));
        car.getUser().getCars().add(car);
    }
    public static void deleteCar(Car car) throws SQLException {
        String carNumber = car.getCarNumber();
        Connection connection = DriverManager.getConnection(UsefulFunctions.PATH);
        connection.createStatement().executeUpdate(String.format("DELETE FROM CAR WHERE NUMBER = '%s'",carNumber));
        cars.remove(car);
        StringBuilder listOfCars = UsefulFunctions.createListOfCars(car.getUser());
        connection.createStatement().executeUpdate(String.format("UPDATE USER SET CARS = '%s' WHERE ID = %d",listOfCars,car.getUser().getId()));
        car.getUser().getCars().remove(car);
    }

}
