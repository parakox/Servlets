package controller;

import model.dao.ParkingPlaceDao;
import model.entity.Car;
import model.entity.ParkingPlace;
import model.entity.User;
import model.service.CarService;
import model.service.ParkingPlaceService;
import model.dao.Dao;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ParkCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(ParkCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber");
            Integer idOfParkingPlace = Integer.parseInt(req.getParameter("parkingPlaceId"));
            Car car = CarService.getCarByCarNumber(carNumber);
            ParkingPlace parkingPlace = ParkingPlaceService.getParkingPlaceById(idOfParkingPlace);
            if(car!=null && car.getUserId().equals(user.getId()) && !car.isParked() && parkingPlace.isEmpty() && idOfParkingPlace<=ParkingPlaceDao.amount && idOfParkingPlace>0){
                car.setParkingPlaceId(idOfParkingPlace);
                car.setParked(true);
                CarService.setCar(car);
                ParkingPlaceService.setParkingPlace(new ParkingPlace(car,false,car.getParkingPlaceId()));
                logger.info("car with number "+carNumber+" parked at parking place "+idOfParkingPlace+", user "+id);
            }else{
                logger.info("couldn't park car with number "+carNumber+" at parking place "+idOfParkingPlace+", user "+id);
            }
        } catch (Exception e) {
            logger.error("DB exception, user "+id);
        }
        resp.sendRedirect("parking");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
