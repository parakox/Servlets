package controller;
import model.entity.ParkingPlace;
import model.сonstant.Constants;
import service.AbstractParkingPlaceService;
import service.ParkingPlaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ParkingPlaceController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(ParkingPlaceController.class);

    private AbstractParkingPlaceService parkingPlaceService = ParkingPlaceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        if(id==null) {
            resp.sendRedirect("index.jsp");
        }else {
            try {
                List<ParkingPlace> parkingPlaceList = parkingPlaceService.getAllParkingPlaces();
                req.setAttribute("parkingPlaceList", parkingPlaceList);
                RequestDispatcher rd = req.getRequestDispatcher("parking.jsp");
                rd.forward(req, resp);
            } catch (SQLException | ServletException e) {
                logger.error("Exception, user " + id + " : " + e.getMessage());
            }
        }
    }
}
