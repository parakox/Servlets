package controller;

import model.service.DatabaseService;
import model.service.UserService;

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
            Connection connection = DriverManager.getConnection(DatabaseService.PATH);
            String name = req.getParameter("name").trim();
            String password = req.getParameter("password").trim();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment , NAME varchar(30), PASSWORD varchar(30), ADMINISTRATOR bool, CARS array)");
            if(action.equals("Log in")){
                if(DatabaseService.checkIfPresent("USER",name,password,"NAME","PASSWORD")) {
                    getServletContext().setAttribute(DatabaseService.UserID, UserService.getUsers().size());
                    resp.sendRedirect("account.jsp");
                }else
                    resp.sendRedirect("index.jsp");
            }else if(action.equals("Register")){
                if(name.length()<6 || password.length()<6 || name.length()>30 || password.length()>30 || DatabaseService.checkIfPresent("USER",name,"NAME"))
                    resp.sendRedirect("index.jsp");
                else{
                    UserService.createNewUser(name,password,false);
                    getServletContext().setAttribute(DatabaseService.UserID, UserService.getUsers().size());
                    resp.sendRedirect("account.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }
}
