package controller;

import model.entity.Message;
import model.entity.User;
import model.exception.InvalidPassedArgumentException;
import service.CarService;
import model.сonstant.Constants;
import service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(AddCarController.class);

    private UserService userService = UserService.getInstance();

    private CarService carService = CarService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        if(id==null) {
            resp.sendRedirect("index.jsp");
        }else {
            try {
                String number = req.getParameter("carNumber").trim();
                String name = req.getParameter("carName").trim();
                if (name.length() < 6 || name.length() > 30) {
                    throw new InvalidPassedArgumentException(String.format(Message.NAME_NOT_MATCHES_LENGTH.getMessage(), name));
                }

                if (number.length() == 0 || number.length() > 8) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_NUMBER_NOT_MATCHES_LENGTH.getMessage(), number));
                }

                if (carService.getCarByCarNumber(number) != null) {
                    throw new InvalidPassedArgumentException(String.format(Message.CAR_NUMBER_ENGAGED.getMessage(), number));
                }
                User user = userService.getUserById(id);
                carService.createNewCar(number, name, user.getId(), null);
                logger.info("new car with number " + number + " created, user " + id);
                resp.sendRedirect("account");
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
