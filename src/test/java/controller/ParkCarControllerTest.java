package controller;

import model.сonstant.Constants;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.only;

class ParkCarControllerTest {

    @Test
    void doPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        ServletConfig sg = mock(ServletConfig.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(req.getParameter("carNumber")).thenReturn("123123123231");
        when(req.getParameter("parkingPlaceId")).thenReturn("-1");
        when(sg.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(Constants.USER_ID)).thenReturn(1);
        when(req.getRequestDispatcher("error")).thenReturn(requestDispatcher);
        ParkCarController parkCarController = new ParkCarController();
        parkCarController.init(sg);
        parkCarController.doPost(req, resp);
        verify(requestDispatcher,only()).forward(req,resp);
    }
}