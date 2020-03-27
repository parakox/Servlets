package model.service;

import model.dao.CarNumberDao;
import model.entity.CarNumber;
import model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class CarNumberService {
    public static List<CarNumber> getCarNumbers() {
        return CarNumberDao.getCarNumbers();
    }
    public static void setCarNumbers(List<CarNumber> carNumbers) {
        CarNumberDao.setCarNumbers(carNumbers);
    }
    public static CarNumber getCarNumberById(Integer id){
        return CarNumberDao.getCarNumberById(id);
    }
    public static void createNewCarNumber(User user, String number) throws SQLException {
        CarNumberDao.createNewCarNumber(user,number);
    }
}
