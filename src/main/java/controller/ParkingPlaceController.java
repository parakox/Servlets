package controller;
import model.entity.entity.ParkingPlace;
import model.entity.сonstant.Constants;
import model.service.ParkingPlaceService;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        try {
            List<ParkingPlace> parkingPlaceList = ParkingPlaceService.getAllParkingPlaces();
            req.setAttribute("parkingPlaceList",parkingPlaceList);
            RequestDispatcher rd = req.getRequestDispatcher("parking.jsp");
            rd.forward(req,resp);
        } catch (SQLException | ServletException e) {
            logger.error("Exception, user "+id+" : "+ e.getMessage());
        }
    }
}
