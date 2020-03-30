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
import java.sql.SQLException;

public class DeleteCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(UsefulFunctions.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(UsefulFunctions.UserID);
        User user = UserService.getUserById(id);
        String carNumber = req.getParameter("carNumber");
        for(int i=0;i<user.getCars().size();i++){
            if(user.getCars().get(i).getCarNumber().equals(carNumber)) {
                try {
                    CarService.deleteCar(user.getCars().get(i));
                    break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        resp.sendRedirect("account");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
