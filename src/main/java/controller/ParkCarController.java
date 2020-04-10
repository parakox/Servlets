package controller;

import model.entity.Car;
import model.entity.ParkingPlace;
import model.entity.User;
import model.service.CarService;
import model.service.ParkingPlaceService;
import model.service.UsefulFunctions;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ParkCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(UsefulFunctions.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(UsefulFunctions.UserID);
        try {
            User user = UserService.getUserById(id);
            String carNumber = req.getParameter("carNumber");
            Integer idOfParkingPlace = Integer.parseInt(req.getParameter("parkingPlaceId"));
            Car car = CarService.getCarByCarNumber(carNumber);
            ParkingPlace parkingPlace = ParkingPlaceService.getParkingPlaceById(idOfParkingPlace);
            if(car!=null && car.getUserId().equals(user.getId()) && !car.isParked() && parkingPlace.isEmpty()){
                CarService.parkCar(car,idOfParkingPlace);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("parking");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
