package controller;

import model.entity.Car;
import model.service.DatabaseService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AccountOfUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(DatabaseService.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(DatabaseService.UserID);
        PrintWriter printWriter = resp.getWriter();
        List<Car> cars = UserService.getUserById(id).getCars();
        if(cars.size()==0)
            printWriter.append("You have no cars currently");
        else{
            cars.forEach(n-> printWriter.append(n.getCarNumber().getNumber()).append(" "));
        }
    }
}
