package controller;

import model.entity.Car;
import model.entity.ParkingPlace;
import model.entity.User;
import model.service.CarService;
import model.dao.Dao;
import model.service.ParkingPlaceService;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UnparkCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(UnparkCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber");
            Car car = CarService.getCarByCarNumber(carNumber);
            if(car!=null && car.getUserId().equals(user.getId()) && car.isParked()){
                ParkingPlaceService.setParkingPlace(new ParkingPlace(null,true,car.getParkingPlaceId()));
                car.setParked(false);
                car.setParkingPlaceId(-1);
                CarService.setCar(car);
                logger.info("car with number "+carNumber+" unparked, user "+id);
            }else{
                logger.info("couldn't unpark car with number "+carNumber+" , user "+id);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("DB exception, user "+id);
        }
        resp.sendRedirect("parking");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
