package controller;

import model.entity.Message;
import model.entity.User;
import model.exception.InvalidPassedArgumentException;
import model.сonstant.Constants;
import service.UserService;
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

    private UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            String action = req.getParameter("action");
            String name = req.getParameter("name").trim();
            String password = req.getParameter("password").trim();
            if(action.equals("Log in")){
                User user = userService.getUserByNameAndPassword(name,password);
                if(user==null){
                    throw new InvalidPassedArgumentException(String.format(Message.USER_WITH_CREDENTIALS_NOT_EXISTS.getMessage(),name,password));
                }
                getServletContext().setAttribute(Constants.USER_ID, user.getId());
                logger.info("user "+user.getId()+" logged in");
                resp.sendRedirect("account");
            }else if(action.equals("Register")){
                if(name.length()<6 || name.length()>30){
                    throw new InvalidPassedArgumentException(String.format(Message.NAME_NOT_MATCHES_LENGTH.getMessage(),name));
                }
                if(password.length()<6 || password.length()>30){
                    throw new InvalidPassedArgumentException(String.format(Message.PASSWORD_NOT_MATCHES_LENGTH.getMessage(),password));
                }
                if(userService.getUserByName(name)!=null) {
                    throw new InvalidPassedArgumentException(String.format(Message.NAME_ENGAGED.getMessage(),name));
                }
                userService.createNewUser(name,password);
                getServletContext().setAttribute(Constants.USER_ID, userService.getAllUsers().size());
                logger.info("user "+userService.getAllUsers().size()+" registered and logged");
                resp.sendRedirect("account");
            }
        }catch (SQLException | ClassNotFoundException e) {
            logger.error("Exception at logging or signing up "+ e.getMessage());
            resp.sendRedirect("index.jsp");
        }catch(InvalidPassedArgumentException | NullPointerException e){
            logger.info("Exception : "+ e.getMessage());
            req.setAttribute("error",e.getMessage());
            req.getRequestDispatcher("error").forward(req,resp);
        }
    }
}
