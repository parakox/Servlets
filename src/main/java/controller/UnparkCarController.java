package controller;

import model.entity.entity.Car;
import model.entity.entity.Message;
import model.entity.entity.ParkingPlace;
import model.entity.entity.User;
import model.entity.exception.InvalidPassedArgumentException;
import model.service.CarService;
import model.entity.сonstant.Constants;
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

    private UserService userService = UserService.getUserService();

    private CarService carService = CarService.getCarService();

    private ParkingPlaceService parkingPlaceService = ParkingPlaceService.getParkingPlaceService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        try {
            User user = userService.getUserById(id);
            String carNumber = req.getParameter("carNumber").trim();
            Car car = carService.getCarByCarNumber(carNumber);
            if(car==null || !car.getUserId().equals(user.getId())){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_BELONGS_TO_YOU.getMessage(),carNumber));
            }
            if(car.getParkingPlaceId()==0){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_PARKED.getMessage(),carNumber));
            }
            parkingPlaceService.setParkingPlace(new ParkingPlace(null,car.getParkingPlaceId()));
            car.setParkingPlaceId(0);
            carService.setCar(car);
            logger.info("car with number "+carNumber+" unparked, user "+id);
            resp.sendRedirect("parking");
        } catch (SQLException e) {
            logger.error("Exception, user "+id+" : "+ e.getMessage());
        }catch(InvalidPassedArgumentException e){
            logger.info("Exception, user "+id+" : "+ e.getMessage());
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("error").forward(req,resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.jsp");
    }
}
