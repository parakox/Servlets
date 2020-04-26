package controller;

import model.entity.entity.Car;
import model.entity.entity.Message;
import model.entity.entity.User;
import model.entity.exception.InvalidPassedArgumentException;
import model.service.CarService;
import model.entity.сonstant.Constants;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(DeleteCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber").trim();
            Car car = CarService.getCarByCarNumber(carNumber);
            if(car==null || !car.getUserId().equals(user.getId())){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NOT_BELONGS_TO_YOU.getMessage(),carNumber));
            }
            if(car.getParkingPlaceId()!=0){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_IS_PARKED.getMessage(),carNumber));
            }
            CarService.deleteCar(car);
            logger.info("car with number " + carNumber + " deleted, user " + id);
            resp.sendRedirect("account");
        } catch (SQLException e) {
            logger.error("Exception, user "+id+" : "+ e.getMessage());
        }catch(InvalidPassedArgumentException e){
            logger.info("Exception, user "+id+" : "+ e.getMessage());
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("error").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
