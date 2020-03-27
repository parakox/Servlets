package model.dao;

import model.entity.Car;
import model.entity.CarNumber;
import model.entity.User;
import model.service.DatabaseService;
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
    public static void createNewCar(CarNumber carNumber) throws SQLException {
        Car car = new Car(carNumber);
        Connection connection = DriverManager.getConnection(DatabaseService.PATH);
        connection.createStatement().executeUpdate(String.format("INSERT into CAR(NUMBER ID) values ('%s')",carNumber.getNumber()));
        cars.add(car);
        Integer ownerId = carNumber.getUser().getId();
        User owner = UserService.getUserById(ownerId);
        StringBuilder listOfCars =new StringBuilder("[");
        for(int i=0;i<owner.getCars().size();i++){
            if(i!=owner.getCars().size()-1)
                listOfCars.append(owner.getCars().get(i)).append(", ");
            else
                listOfCars.append(owner.getCars().get(i));
        }
        listOfCars.append("]");
        connection.createStatement().executeQuery(String.format("UPDATE USER SET CARS = '%s' WHERE ID = %d",listOfCars,ownerId));
        owner.getCars().add(car);
    }
}
