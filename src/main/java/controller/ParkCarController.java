package controller;

import model.dao.ParkingPlaceDao;
import model.entity.entity.Car;
import model.entity.entity.Message;
import model.entity.entity.ParkingPlace;
import model.entity.entity.User;
import model.entity.exception.InvalidPassedArgumentException;
import model.entity.сonstant.Constants;
import model.service.CarService;
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

public class ParkCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(ParkCarController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber").trim();
            Integer parkingPlaceId = Integer.parseInt(req.getParameter("parkingPlaceId").trim());
            Car car = CarService.getCarByCarNumber(carNumber);
            ParkingPlace parkingPlace = ParkingPlaceService.getParkingPlaceById(parkingPlaceId);
            if(parkingPlaceId<=0 || parkingPlaceId>ParkingPlaceDao.amount){
                throw new InvalidPassedArgumentException(String.format(Message.WRONG_PARKING_PLACE_ID.getMessage(),parkingPlaceId));
            }
            if(car==null || !car.getUserId().equals(user.getId())){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_BELONGS_TO_YOU.getMessage(),carNumber));
            }
            if(car.getParkingPlaceId()!=0){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_IS_PARKED.getMessage(),carNumber));
            }
            if(parkingPlace.getCar()!=null){
                throw new InvalidPassedArgumentException(String.format(Message.PARKING_PLACE_ENGAGED.getMessage(),parkingPlaceId));
            }
            car.setParkingPlaceId(parkingPlaceId);
            CarService.setCar(car);
            ParkingPlaceService.setParkingPlace(new ParkingPlace(car,car.getParkingPlaceId()));
            logger.info("car with number "+carNumber+" parked at parking place "+parkingPlaceId+", user "+id);
            resp.sendRedirect("parking");
        } catch (SQLException e) {
            logger.error("Exception, user "+id+" : "+ e.getMessage());
        }catch (InvalidPassedArgumentException e){
            logger.info("Exception, user "+id+" : "+ e.getMessage());
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("error").forward(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
        resp.sendRedirect("index.jsp");
    }
}
