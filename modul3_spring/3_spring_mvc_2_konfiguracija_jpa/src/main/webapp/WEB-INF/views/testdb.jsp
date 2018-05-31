<%@ page import="ba.fit.java.spring.mvc.viewmodels.HomeTestDbVM" %><%--
  Created by IntelliJ IDEA.
  User: adil
  Date: 29/5/2018
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>

<%
    HomeTestDbVM model = (HomeTestDbVM) request.getAttribute("model");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

ucenikCount: <%= model.ucenikCount %>
odjeljenjeCount: <%= model.odjeljenjeCount %>
predmetCount: <%= model.predmetCount %>
nastavnikCount: <%= model.nastavnikCount %>

</body>
</html>
