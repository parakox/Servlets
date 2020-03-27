package controller;
import com.google.gson.Gson;
import model.entity.ParkingPlace;
import model.service.ParkingPlaceService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class ParkingPlaceController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getServletContext().getAttribute("idOfUser")==null)
            resp.sendRedirect("index.jsp");
        PrintWriter printWriter = resp.getWriter();
        String id = req.getParameter("id");
        if(id == null) {
            List<ParkingPlace> parkingPlaces = getAllParkingPlaces();
            for (int i = 0; i < parkingPlaces.size(); i++) {
                if (parkingPlaces.get(i).isEmpty()) {
                    printWriter.println(i + 1 + " - empty");
                } else {
                    printWriter.println(i + 1 + String.format(" - engaged by car with number %s", parkingPlaces.get(i).getCar().getCarNumber().getNumber()));
                }
            }
        }else{
            Gson gson = new Gson();
            String json = gson.toJson(getParkingPlaceById(Integer.parseInt(id)));
            printWriter.append(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public List<ParkingPlace> getAllParkingPlaces(){
        return ParkingPlaceService.getAllParkingPlaces();
    }
    public ParkingPlace getParkingPlaceById(Integer id){
        return ParkingPlaceService.getParkingPlaceById(id);
    }
    public void setParkingPlaceById(Integer id,ParkingPlace parkingPlace){
        ParkingPlaceService.setParkingPlaceById(id,parkingPlace);
    }
}
