<%@ page import="model.entity.ParkingPlace" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Max
  Date: 21.04.2020
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<ParkingPlace> parkingPlaceList = (ArrayList<ParkingPlace>) request.getAttribute("parkingPlaceList");%>
    <br>Please enter car number and car name to create one
    <form action=parkCar method=post>
        <br>Car Number: <input type=text name=carNumber>
        <br>Id of parking place: <input type=text name=parkingPlaceId>
        <br><input type=submit name=action value=Park>
    </form>
    <form action=unparkCar method=post>
        <br>Car Number: <input type=text name=carNumber>
        <br><input type=submit name=action value=Unpark>
    </form>
    <%for (int i = 0; i < parkingPlaceList.size(); i++) {
        if (parkingPlaceList.get(i).getCar()==null) {
            out.print("<p>"+ (i + 1) + " - empty</p>");
        } else {
            out.print("<p>"+(i + 1) + String.format(" - engaged by car with number %s", parkingPlaceList.get(i).getCar().getCarNumber())+"</p>");
        }
    }%>
</body>
</html>
