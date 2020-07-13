package controller;

import model.entity.Car;
import model.entity.Message;
import model.entity.ParkingPlace;
import model.entity.User;
import model.exception.InvalidPassedArgumentException;
import model.сonstant.Constants;
import service.CarService;
import service.ParkingPlaceService;
import service.UserService;
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

    private UserService userService = UserService.getInstance();

    private CarService carService = CarService.getInstance();

    private ParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        if(id==null) {
            resp.sendRedirect("index.jsp");
        }else {
            try {
                String carNumber = req.getParameter("carNumber").trim();
                Integer parkingPlaceId = Integer.parseInt(req.getParameter("parkingPlaceId").trim());
                if (parkingPlaceId <= 0 || parkingPlaceId > parkingPlaceService.getAmount()) {
                    throw new InvalidPassedArgumentException(String.format(Message.WRONG_PARKING_PLACE_ID.getMessage(), parkingPlaceId));
                }
                User user = userService.getUserById(id);
                Car car = carService.getCarByCarNumber(carNumber);
                ParkingPlace parkingPlace = parkingPlaceService.getParkingPlaceById(parkingPlaceId);
                if (car == null || !car.getUserId().equals(user.getId())) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_BELONGS_TO_YOU.getMessage(), carNumber));
                }
                if (car.getParkingPlaceId() != 0) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_IS_PARKED.getMessage(), carNumber));
                }
                if (parkingPlace.getCar() != null) {
                    throw new InvalidPassedArgumentException(String.format(Message.PARKING_PLACE_ENGAGED.getMessage(), parkingPlaceId));
                }
                car.setParkingPlaceId(parkingPlaceId);
                carService.setCar(car);
                parkingPlaceService.setParkingPlace(new ParkingPlace(car, car.getParkingPlaceId()));
                logger.info("car with number " + carNumber + " parked at parking place " + parkingPlaceId + ", user " + id);
                resp.sendRedirect("parking");
            } catch (SQLException e) {
                logger.error("Exception, user " + id + " : " + e.getMessage());
            } catch (InvalidPassedArgumentException | NullPointerException e) {
                logger.info("Exception, user " + id + " : " + e.getMessage());
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("error").forward(req, resp);
            }
        }
    }
}
