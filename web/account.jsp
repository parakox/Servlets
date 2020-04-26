<%@ page import="model.entity.entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.entity.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 21.04.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <%
        User user = (User) request.getAttribute("user");
        String name= user.getName();
        Integer id = user.getId();
        List<Car> cars = user.getCars();%>
    <p>Your name: <%=name%></p>
    <p>Your id : <%=id%></p>
    <p>Your cars :</p>
    <p> <% for (Car car : cars) {
        out.println(car.toString());
    }
        ;%></p>
    <br>Please enter car number and car name to create one
    <form action=addCar method=post>
        <br>Car Number: <input type=text name=carNumber>
        <br>Car Name: <input type=text name=carName>
        <br><input type=submit name=action value=Create>
        <br>
        <br>Please enter car number to delete one
    </form>
    <form action=deleteCar method=post>
        <br>Car Number: <input type=text name=carNumber>
        <br><input type=submit name=action value=Delete>
    </form>
    <form method = get action=parking>
        <input type=submit value=Parking>
    </form>
</body>
</html>
