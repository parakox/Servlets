package controller;

import model.entity.Car;
import model.entity.User;
import model.service.DatabaseService;
import model.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

public class AccountOfUserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute(DatabaseService.UserID)==null)
            resp.sendRedirect("index.jsp");
        Integer id = (Integer) getServletContext().getAttribute(DatabaseService.UserID);
        User user = UserService.getUserById(id);
        List<Car> cars = user.getCars();
        String name = user.getName();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Your name: " + name+"</p>");
        out.println("<p>Your id :" + id+"</p>");
        out.println("<p>Your cars :"+"</p>");
        out.println("<p>");
        Stream.of(cars).forEach(n->out.println(n.toString()));
        out.println("</p>");
        out.println("<br>Please enter car number and car name to create one");
        out.println("<form action=addCar method=post>");
        out.println("<br>Car Number: <input type=text name=carNumber>");
        out.println("<br>Car Name: <input type=text name=carName>");
        out.println("<br><input type=submit name=action value=Create new car>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
