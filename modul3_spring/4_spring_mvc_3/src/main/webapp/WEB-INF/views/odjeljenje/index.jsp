<%@ page import="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM" %><%--
  Created by IntelliJ IDEA.
  User: adil
  Date: 16/5/2018
  Time: 1:36 PM
  To change this template use File | Settings | File Templates.
--%>

<%
   OdjeljenjeIndexVM model = (OdjeljenjeIndexVM) request.getAttribute("model");
%>

<jsp:include page="/WEB-INF/views/shared/header.jsp"/>

<a href="/odjeljenje/dodaj" class="btn btn-success">Dodaj</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Skolska godina</th>
        <th>Razred(od 1 do 4)</th>
        <th>Oznaka</th>
        <th>Razrednik</th>
        <th>Prebaceni u visi razred(odjeljenje)</th>
        <th>Prosjek ocjena</th>
        <th>Najbolji ucenik</th>
        <th>Akcija</th>
    </tr>
    </thead>
    <tbody>

    <%
    for (OdjeljenjeIndexVM.Row x : model.rows)
    {
    %>
    <tr>
        <td><%= x.skolskaGodina %></td>
        <td><%=x.razred%></td>
        <td><%=x.oznaka%></td>
        <td><%=x.razrednik%></td>
        <td><%=(x.isPrebacenuViseOdjeljenje?"DA":"NE")%></td>
        <td><%=String.format("%.2f", x.prosjekOcjena) %></td>
        <td><%=x.najboljiUcenik%></td>
<td><a href="/odjeljenje/obrisi?id=<%= x.odjeljenjeId%>" class="btn btn-danger">Obrisi</a></td>
<td><a href="/odjeljenje/uredi?id=<%= x.odjeljenjeId%>" class="btn btn-success">Uredi</a></td>

    </tr>

    <% }%>

    </tbody>
</table>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>