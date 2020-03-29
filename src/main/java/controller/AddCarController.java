package controller;

import model.entity.User;
import model.service.CarService;
import model.service.DatabaseService;
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
        if(getServletContext().getAttribute(DatabaseService.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(DatabaseService.UserID);
        String number = req.getParameter("carNumber");
        String name = req.getParameter("carName");
        User user = UserService.getUserById(id);
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(DatabaseService.PATH);
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CAR(ID bigint auto_increment , NUMBER varchar(30), NAME varchar(30), USERID int)");
            if(!DatabaseService.checkIfPresent("CAR",number,"NUMBER")){
                CarService.createNewCar(number,name,user);
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
