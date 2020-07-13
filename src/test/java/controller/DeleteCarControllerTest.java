package controller;

import model.сonstant.Constants;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.only;

class DeleteCarControllerTest {
    private DeleteCarController deleteCarController = new DeleteCarController();

    @Test
    void doPost() throws ServletException, IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        ServletConfig sg = mock(ServletConfig.class);
        when(sg.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(Constants.USER_ID)).thenReturn(null);
        deleteCarController.init(sg);
        deleteCarController.doPost(req, resp);
        verify(resp,only()).sendRedirect("index.jsp");
    }
}