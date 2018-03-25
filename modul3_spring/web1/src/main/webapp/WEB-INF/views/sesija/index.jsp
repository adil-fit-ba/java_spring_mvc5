<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ba.fit.java.spring.mvc.helper.Autentifikacija" %>
<%@ page import="ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog" %>
<jsp:include page="/WEB-INF/views/shared/header.jsp"/>

<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.SesijaIndexVM"--%>

<%
    KorisnickiNalog korisnik = Autentifikacija.getLogiraniKorisnik(request);
%>

<h2>Aktivne sesije za korisnika <%= korisnik.getKorisnickoIme()%> </h2>



<table class="table table-striped">
    <thead>
    <tr>
        <th>Vrijeme</th>
        <th>Ip adresa</th>
        <th>Akcija</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${model.rows}" var="x">
            <tr>
                <td> ${x.vrijemeLogiranja}
                    <c:if test="${x.token.compareTo(model.trenutniToken)==0}">
                        <strong>(trenutna sesija)</strong>
                    </c:if>
                </td>
                <td>${x.ipAdresa}</td>
                <td>
                    <a href="/sesija/obrisi?value=${x.token}" class="btn btn-danger">Obrisi</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>