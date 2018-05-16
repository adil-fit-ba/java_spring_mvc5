<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeIndexVM" %>
<jsp:include page="/WEB-INF/views/shared/header.jsp"/>


<h2>Index</h2>
<%
 OdjeljenjeIndexVM model = (OdjeljenjeIndexVM) request.getAttribute("model");
%>
<h2>odjeljenje/index</h2>
<h3>Dobro dosao nastanvik</h3>

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

	<% for (OdjeljenjeIndexVM.Row x : model.rows)
	{%>
	<tr>
		<td><%= x.skolskaGodina %></td>
		<td><%=x.razred%></td>
		<td><%=x.oznaka%></td>
		<td><%=x.razrednik%></td>
		<td><%=(x.isPrebacenuViseOdjeljenje?"DA":"NE")%></td>
		<td><%=String.format("%.2f", x.prosjekOcjena) %></td>
		<td><%=x.najboljiUcenik%></td>
		<td>
			<a href="/odjeljenje/detalji?id=<%= x.odjeljenjeId %>" class="btn btn-primary">Detalji</a>
			<a href="/odjeljenje/obrisi?id=<%= x.odjeljenjeId %>" class="btn btn-danger">Obrisi</a>

		</td>
	</tr>
	<%} %>

	</tbody>
</table>

<a href="/odjeljenje/dodaj" class="btn btn-success">Dodaj</a>


<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>