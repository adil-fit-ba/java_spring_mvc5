<%@ page import="ba.fit.java.spring.mvc.viewmodels.StavkeIndexVM" %>
<%--@elvariable id="xy" type="ba.fit.java.spring.mvc.viewmodels.StavkeIndexVM"--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Index</h2>

<table class="table table-bordered">
    <thead>
    <tr>
        <th>Broj u dnevniku</th>
        <th>Ucenik</th>
        <th>Broj zakljucihOcjena</th>
        <th>Akcija</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${xy.rows}" var="x">
            <tr>
                <td>${x.brojUDnevniku}</td>
                <td>${x.ucenik}</td>
                <td>${x.brojZakljucihOcjena}</td>
                <td>
                    <a  class="btn btn-danger" ajax-poziv="da" ajax-rezultat="nekiID" href="/stavke-ajax/obrisi?id=${x.odjeljenjeStavkeId}">Obrisi</a>
                    <a  class="btn btn-warning" ajax-poziv="da" ajax-rezultat="nekiID" href="/stavke-ajax/uredi?id=${x.odjeljenjeStavkeId}">Uredi</a>

                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

Ukupno ${xy.rows.size()}

<a href="/stavke-ajax/dodaj?odjeljenjeId=${xy.odjeljenjeId}" class="btn btn-danger" ajax-poziv="da" ajax-rezultat="nekiID">Dodaj</a>

