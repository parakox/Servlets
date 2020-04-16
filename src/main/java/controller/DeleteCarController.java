package controller;

import model.entity.Car;
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
import java.util.List;

public class DeleteCarController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(DeleteCarController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber");
            List<Car> cars = user.getCars();
            for(int i = 0; i<cars.size(); i++){
                if(cars.get(i).getCarNumber().equals(carNumber) && !cars.get(i).isParked()) {
                    CarService.deleteCar(cars.get(i));
                    logger.info("car with number "+carNumber+" deleted, user "+id);
                    break;
                }
                if(i==cars.size()-1){
                    logger.info(" couldn't delete car with number "+carNumber+", user "+id);
                }
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
