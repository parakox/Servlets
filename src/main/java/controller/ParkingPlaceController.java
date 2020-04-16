package controller;
import model.entity.ParkingPlace;
import model.dao.Dao;
import model.service.ParkingPlaceService;
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

public class ParkingPlaceController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(ParkingPlaceController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Dao.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        PrintWriter out = resp.getWriter();
        Integer id = (Integer) getServletContext().getAttribute(Dao.USER_ID);
        try {
            List<ParkingPlace> parkingPlaces = ParkingPlaceService.getAllParkingPlaces();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<br>Please enter car number and car name to create one");
            out.println("<form action=parkCar method=post>");
            out.println("<br>Car Number: <input type=text name=carNumber>");
            out.println("<br>Id of parking place: <input type=text name=parkingPlaceId>");
            out.println("<br><input type=submit name=action value=Park car>");
            out.println("</form>");
            out.println("<form action=unparkCar method=post>");
            out.println("<br>Car Number: <input type=text name=carNumber>");
            out.println("<br><input type=submit name=action value=Unpark car>");
            out.println("</form>");
            for (int i = 0; i < parkingPlaces.size(); i++) {
                if (parkingPlaces.get(i).isEmpty()) {
                    out.println("<p>"+ (i + 1) + " - empty</p>");
                } else {
                    out.println("<p>"+(i + 1) + String.format(" - engaged by car with number %s", parkingPlaces.get(i).getCar().getCarNumber())+"</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("DB exception, user "+id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
