package controller;

import model.entity.Car;
import model.entity.Message;
import model.entity.ParkingPlace;
import model.entity.User;
import model.exception.InvalidPassedArgumentException;
import service.CarService;
import model.сonstant.Constants;
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

public class UnparkCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(UnparkCarController.class);

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
                User user = userService.getUserById(id);
                Car car = carService.getCarByCarNumber(carNumber);
                if (car == null || !car.getUserId().equals(user.getId())) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_BELONGS_TO_YOU.getMessage(), carNumber));
                }
                if (car.getParkingPlaceId() == 0) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_PARKED.getMessage(), carNumber));
                }
                parkingPlaceService.setParkingPlace(new ParkingPlace(null, car.getParkingPlaceId()));
                car.setParkingPlaceId(0);
                carService.setCar(car);
                logger.info("car with number " + carNumber + " unparked, user " + id);
                resp.sendRedirect("parking");
            } catch (SQLException | ClassNotFoundException e) {
                logger.error("Exception, user " + id + " : " + e.getMessage());
            } catch (InvalidPassedArgumentException | NullPointerException e) {
                logger.info("Exception, user " + id + " : " + e.getMessage());
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("error").forward(req, resp);
            }
        }
    }
}
