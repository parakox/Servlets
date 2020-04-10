package controller;

import model.dao.UserDao;
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
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class AccountOfUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(UsefulFunctions.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(UsefulFunctions.UserID);
        try {
            User user = UserService.getUserById(id);
            List<Car> cars = user.getCars();
            String name = user.getName();
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Your name: " + name + "</p>");
            out.println("<p>Your id :" + id + "</p>");
            out.println("<p>Your cars :" + "</p>");
            out.println("<p>[");
            for (Car i : cars) {
                out.println(i.toString() + "; ");
            }
            out.println("]</p>");
            out.println("<br>Please enter car number and car name to create one");
            out.println("<form action=addCar method=post>");
            out.println("<br>Car Number: <input type=text name=carNumber>");
            out.println("<br>Car Name: <input type=text name=carName>");
            out.println("<br><input type=submit name=action value=Create new car>");
            out.println("<br>");
            out.println("<br>Please enter car number to delete one");
            out.println("</form>");
            out.println("<form action=deleteCar method=post>");
            out.println("<br>Car Number: <input type=text name=carNumber>");
            out.println("<br><input type=submit name=action value=Delete car>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
