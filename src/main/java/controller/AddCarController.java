package controller;

import model.entity.User;
import model.service.CarService;
import model.service.UsefulFunctions;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(UsefulFunctions.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(UsefulFunctions.UserID);
        String number = req.getParameter("carNumber");
        String name = req.getParameter("carName");
        try {
            User user = UserService.getUserById(id);
            if(!number.equals("null") && name.length()>0 && name.length()<=30 && number.length()>0 && number.length()<=8 && !UsefulFunctions.checkIfPresent("CAR",number,"NUMBER")){
                CarService.createNewCar(number,name,false,user.getId(),-1);
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
