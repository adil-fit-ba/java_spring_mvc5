<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.StavkeDodajVM"--%>

<h2>Dodaj novog učenika</h2>


<form:form method="POST" action="/stavke-ajax/snimi" modelAttribute="model" ajax-rezultat="nekiID" ajax-poziv="da">
    <form:input type="hidden" path="odjeljenjeID"  />
    <form:input type="hidden" path="odjeljenjeStavkaId"  />

    <div class="form-group">
        <label>Broj u dnevniku: </label>
        <div>
            <form:input type="text" path="brojUdnevniku" class="form-control" />
            <span class="text-danger field-validation-valid" data-valmsg-for="skolaGodina" data-valmsg-replace="true"></span>
        </div>
    </div>


    <div class="form-group">
        <label>Učenik: </label>
        <div>
            <form:select path="ucenikID"  class="form-control" >
                <form:option value="0"> ---SELECT---</form:option>
                <form:options items="${model.ucenici}"/>
            </form:select>
        </div>
    </div>


    <input id="submitButton" type="submit" value="Snimi"   class="form-control btn btn-danger"/>
</form:form>
<script>
     
</script>