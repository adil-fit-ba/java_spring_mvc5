<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/shared/header.jsp"/>


<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM"--%>

<h2>Dodaj</h2>


<form:form method="POST" action="/odjeljenje/snimi" modelAttribute="model" >

    <div class="form-group">
        <label>Školska godina: </label>
        <div>
            <form:input class="form-control"   path="skolaGodina"/>

        </div>
    </div>

    <div class="form-group">
        <label>Razred: </label>
        <div>
            <form:input class="form-control"  path="razred"/>

        </div>
    </div>

    <div class="form-group">
        <label>Oznaka: </label>
        <div>
            <form:input  class="form-control"  path="oznaka"/>

        </div>
    </div>


    <div class="form-group">
        <label>Razrednik: </label>
        <div>
            <form:select class="form-control" path="nastavnikId">
                <option value="0">(odaberite)</option>
                <form:options items="${model.nastavnici}"></form:options>
            </form:select>
        </div>
    </div>

    <c:if test="${model.odjeljenjeId == 0}">
        <div class="form-group">
            <label>Preuzmi učenike iz odjeljenja: </label>
            <div>
                <form:select class="form-control" path="odjeljenjeId">
                    <option value="0">(odaberite)</option>
                    <form:options items="${model.odjeljenja}"></form:options>
                </form:select>
            </div>
        </div>

    </c:if>


    <input name="odjeljenjeId" class="form-control" type="hidden"  value="${model.odjeljenjeId}" />


    <input type="submit" value="Snimi" />
</form:form>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>

