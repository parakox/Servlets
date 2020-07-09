package controller;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.only;

class SignInSignUpControllerTest {

    @Test
    void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletConfig sg = mock(ServletConfig.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(req.getParameter("action")).thenReturn("Register");
        when(req.getParameter("name")).thenReturn("fgfg");
        when(req.getParameter("password")).thenReturn("dsfsdsdfdfs");
        when(req.getRequestDispatcher("error")).thenReturn(requestDispatcher);
        SignInSignUpController signInSignUpController = new SignInSignUpController();
        signInSignUpController.init(sg);
        signInSignUpController.doPost(req, resp);
        verify(requestDispatcher,only()).forward(req,resp);
    }
}