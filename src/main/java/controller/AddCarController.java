package controller;

import model.entity.User;
import model.service.CarService;
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

public class AddCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(AddCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
        String number = req.getParameter("carNumber");
        String name = req.getParameter("carName");
        try {
            User user = UserService.getUserById(id);
            if(!number.equals("null") && name.length()>0 && name.length()<=30 && number.length()>0 && number.length()<=8 && !Dao.checkIfPresent("CAR",number,"NUMBER")){
                CarService.createNewCar(number,name,false,user.getId(),-1);
                logger.info("new car with number "+number+" created, user "+id);
            }else{
                logger.info("couldn't create car with number "+number+", user "+id);
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("DB exception, user "+id);
        }
        resp.sendRedirect("account");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
