package controller;

import model.entity.entity.User;
import model.entity.сonstant.Constants;
import model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AccountOfUserController extends HttpServlet {
    final static Logger logger = LogManager.getLogger(AccountOfUserController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(getServletContext().getAttribute(Constants.USER_ID)==null) {
            resp.sendRedirect("index.jsp");
        }
        Integer id = (Integer) getServletContext().getAttribute(Constants.USER_ID);
        try {
            User user = UserService.getUserById(id);
            req.setAttribute("user",user);
            RequestDispatcher rd = req.getRequestDispatcher("account.jsp");
            rd.forward(req,resp);
        }catch (SQLException | ServletException e) {
            logger.error("Exception, user "+id+" : "+ e.getMessage());
        }
    }
}
