package model.service;

import model.dao.ParkingPlaceDao;
import model.entity.ParkingPlace;

import java.util.List;

public class ParkingPlaceService {

    public static List<ParkingPlace> getAllParkingPlaces(){
        return ParkingPlaceDao.getAllParkingPlaces();
    }
    public static ParkingPlace getParkingPlaceById(Integer id){
        return ParkingPlaceDao.getParkingPlaceById(id);
    }
    public static void setParkingPlaceById(Integer id,ParkingPlace parkingPlace){
        ParkingPlaceDao.setParkingPlaceById(id,parkingPlace);
    }
}
