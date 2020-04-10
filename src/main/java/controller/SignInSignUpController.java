package controller;

import model.entity.ParkingPlace;
import model.service.CarService;
import model.service.ParkingPlaceService;
import model.service.UsefulFunctions;
import model.service.UserService;
import org.h2.engine.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class SignInSignUpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            String name = req.getParameter("name").trim();
            String password = req.getParameter("password").trim();
            UserService.createTableIfNotExists();
            ParkingPlaceService.createTableIfNotExists();
            CarService.createTableIfNotExists();
            if(action.equals("Log in")){
                if(UsefulFunctions.checkIfPresent("USER",name,password,"NAME","PASSWORD")) {
                    int id = 0;
                    for(int i=0;i<UserService.getAllUsers().size();i++){
                        if(UserService.getAllUsers().get(i).getName().equals(name)){
                            id = i+1;
                            break;
                        }
                    }
                    getServletContext().setAttribute(UsefulFunctions.UserID, id);
                    resp.sendRedirect("account");
                }else
                    resp.sendRedirect("index.jsp");
            }else if(action.equals("Register")){
                if(name.length()<6 || password.length()<6 || name.length()>30 || password.length()>30 || UsefulFunctions.checkIfPresent("USER",name,"NAME"))
                    resp.sendRedirect("index.jsp");
                else{
                    UserService.createNewUser(name,password,false);
                    getServletContext().setAttribute(UsefulFunctions.UserID, UserService.getAllUsers().size());
                    resp.sendRedirect("account");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
