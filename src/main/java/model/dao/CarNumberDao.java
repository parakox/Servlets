package model.dao;

import model.entity.CarNumber;
import model.entity.User;
import model.service.DatabaseService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarNumberDao {
    private static List<CarNumber> carNumbers;
    static{
        carNumbers = new ArrayList<>();
    }

    public static List<CarNumber> getCarNumbers() {
        return carNumbers;
    }

    public static void setCarNumbers(List<CarNumber> carNumbers) {
        CarNumberDao.carNumbers = carNumbers;
    }
    public static CarNumber getCarNumberById(Integer id){
        if(id<1 || id>carNumbers.size())
            return null;
        return carNumbers.get(id-1);
    }
    public static void createNewCarNumber(User user, String number) throws SQLException {
        CarNumber carNumber = new CarNumber(user,number);
        Connection connection = DriverManager.getConnection(DatabaseService.PATH);
        connection.createStatement().executeQuery(String.format("INSERT into CAR NUMBER(NUMBER,USER ID) values ('%s', %d)",carNumber.getNumber(),carNumber.getUser().getId()));
        carNumbers.add(carNumber);
    }
}
