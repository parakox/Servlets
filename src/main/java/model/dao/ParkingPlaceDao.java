package model.dao;

import model.entity.ParkingPlace;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlaceDao {
    private static List<ParkingPlace> parking;
    static{
        parking = new ArrayList<>(){
            {
                for(int i=0;i<64;i++)
                    add(new ParkingPlace(null,null,true));
            }
        };
    }
    public static List<ParkingPlace> getAllParkingPlaces(){
        return parking;
    }
    public static ParkingPlace getParkingPlaceById(Integer id){
        if(id<=0 || id>parking.size())
            return null;
        return parking.get(id-1);
    }
    public static void setParkingPlaceById(Integer id,ParkingPlace parkingPlace){
        parking.set(id-1,parkingPlace);
    }
}
