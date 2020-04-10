package controller;

import model.entity.Car;
import model.entity.User;
import model.service.CarService;
import model.service.UsefulFunctions;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeleteCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(UsefulFunctions.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(UsefulFunctions.UserID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber");
            List<Car> cars = user.getCars();
            for(int i = 0; i<cars.size(); i++){
                if(cars.get(i).getCarNumber().equals(carNumber)) {
                    try {
                        CarService.deleteCar(cars.get(i));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            resp.sendRedirect("account");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
