package controller;

import model.entity.Car;
import model.entity.User;
import model.dao.Dao;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class AccountOfUserController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(AccountOfUserController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
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
            out.println("<p>Your cars :</p>");
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
            out.println("<form method = get action=parking><input type=submit value=Parking></form>");
            out.println("</body>");
            out.println("</html>");
        }catch (SQLException | ClassNotFoundException e) {
            logger.error("DB exception, user "+id);
        }
    }
}
