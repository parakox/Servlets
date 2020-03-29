package controller;

import model.service.DatabaseService;
import model.service.UserService;
import model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.stream.Stream;

public class SignInSignUpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(DatabaseService.PATH);
            String name = req.getParameter("name").trim();
            String password = req.getParameter("password").trim();
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS USER(ID bigint auto_increment , NAME varchar(30), PASSWORD varchar(30), ADMINISTRATOR bool, CARS array)");
            if(action.equals("Log in")){
                if(DatabaseService.checkIfPresent("USER",name,password,"NAME","PASSWORD")) {
                    int id = 0;
                    for(int i=0;i<UserService.getUsers().size();i++){
                        if(UserService.getUsers().get(i).getName().equals(name)){
                            id = i+1;
                            break;
                        }
                    }
                    getServletContext().setAttribute(DatabaseService.UserID, id);
                    resp.sendRedirect("account");
                }else
                    resp.sendRedirect("index.jsp");
            }else if(action.equals("Register")){
                if(name.length()<6 || password.length()<6 || name.length()>30 || password.length()>30 || DatabaseService.checkIfPresent("USER",name,"NAME"))
                    resp.sendRedirect("index.jsp");
                else{
                    UserService.createNewUser(name,password,false);
                    getServletContext().setAttribute(DatabaseService.UserID, UserService.getUsers().size());
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
