package controller;

import model.service.CarService;
import model.service.ParkingPlaceService;
import model.dao.Dao;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class SignInSignUpController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(SignInSignUpController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        try {
            String name = req.getParameter("name").trim();
            String password = req.getParameter("password").trim();
            UserService.createTableIfNotExists();
            ParkingPlaceService.createTableIfNotExists();
            CarService.createTableIfNotExists();
            if(action.equals("Log in")){
                if(Dao.checkIfPresent("USER",name,password,"NAME","PASSWORD")) {
                    int id = 0;
                    for(int i=0;i<UserService.getAllUsers().size();i++){
                        if(UserService.getAllUsers().get(i).getName().equals(name)){
                            id = i+1;
                            break;
                        }
                    }
                    getServletContext().setAttribute(Dao.USER_ID, id);
                    resp.sendRedirect("account");
                    logger.info("user "+id+" logged in");
                }else {
                    resp.sendRedirect("index.jsp");
                    logger.info("couldn't log in with password: "+ password+" name: "+name);
                }
            }else if(action.equals("Register")){
                if(name.length()<6 || password.length()<6 || name.length()>30 || password.length()>30 || Dao.checkIfPresent("USER",name,"NAME")) {
                    resp.sendRedirect("index.jsp");
                    logger.info("couldn't sign up with password: "+ password+" name: "+name);
                }else{
                    UserService.createNewUser(name,password,0,false);
                    getServletContext().setAttribute(Dao.USER_ID, UserService.getAllUsers().size());
                    resp.sendRedirect("account");
                    logger.info("user "+UserService.getAllUsers().size()+" registered and logged");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendRedirect("index.jsp");
            logger.error("DB exception at logging or signing up");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
