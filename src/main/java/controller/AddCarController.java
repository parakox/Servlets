package controller;

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

public class AddCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(AddCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        String number = req.getParameter("carNumber").trim();
        String name = req.getParameter("carName").trim();
        try{
            User user = UserService.getUserById(id);
            if(CarService.getCarByCarNumber(number)!=null || number.equals("null")){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NUMBER_ENGAGED.getMessage(),number));
            }
            if(name.length()<6 || name.length()>30){
                throw new InvalidPassedArgumentException(String.format(Message.NAME_NOT_MATCHES_LENGTH.getMessage(),name));
            }
            if(number.length()==0 || number.length()>8){
                throw new InvalidPassedArgumentException(String.format(Message.CAR_NUMBER_NOT_MATCHES_LENGTH.getMessage(),number));
            }
            CarService.createNewCar(number,name,user.getId(),null);
            logger.info("new car with number "+number+" created, user "+id);
            resp.sendRedirect("account");
        }catch (SQLException e) {
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
