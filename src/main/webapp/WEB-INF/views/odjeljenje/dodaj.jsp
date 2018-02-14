<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM"--%>


<jsp:include page="/WEB-INF/views/shared/header.jsp"/>

<%@ page import="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM" %>
<%@ page import="javassist.compiler.ast.Pair" %>
<%@ page import="ba.fit.java.spring.mvc.helper.SelectListItem" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Dodaj</h2>


<form:form method="POST" action="/odjeljenje/snimi" modelAttribute="model" >

    <div class="form-group">
        <label>Školska godina: </label>
        <div>
            <form:input path="skolaGodina" class="form-control" data-val="true" data-val-length="The field skolaGodina must be a string with a maximum length of 7." data-val-length-max="7" data-val-regex="The field skolaGodina must match the regular expression &#x27;[0-9]{4}[\/-][0-9]{2}&#x27;." data-val-regex-pattern="[0-9]{4}[\/-][0-9]{2}" data-val-remote="&#x27;skolaGodina&#x27; is invalid." data-val-remote-additionalfields="*.skolaGodina,*.oznaka" data-val-remote-url="/odjeljenje/provjeri-oznaku" data-val-required="The skolaGodina field is required." />
            <span class="text-danger field-validation-valid" data-valmsg-for="skolaGodina" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label>Razred: </label>
        <div>
            <form:input path="razred" class="form-control"  data-val="true" data-val-range="The field razred must be between 1 and 9." data-val-range-max="9" data-val-range-min="1" data-val-required="The razred field is required." />
            <span class="text-danger field-validation-valid" data-valmsg-for="razred" data-valmsg-replace="true"></span>
        </div>
    </div>

    <div class="form-group">
        <label>Oznaka: </label>
        <div>
            <form:input path="oznaka" class="form-control" data-val="true" data-val-length="The field oznaka must be a string with a maximum length of 3." data-val-length-max="3" data-val-regex="The field oznaka must match the regular expression &#x27;[1-9][-][a-z]&#x27;." data-val-regex-pattern="[1-9][-][a-z]" data-val-remote="&#x27;oznaka&#x27; is invalid." data-val-remote-additionalfields="*.oznaka,*.skolaGodina" data-val-remote-url="/odjeljenje/provjeri-oznaku" data-val-required="The oznaka field is required."/>
            <span class="text-danger field-validation-valid" data-valmsg-for="oznaka" data-valmsg-replace="true"></span>
        </div>
    </div>


    <div class="form-group">
        <label>Razrednik: </label>
        <div>
            <form:input path="nastavnik" class="form-control" readonly="true"/>
        </div>
    </div>

    <div class="form-group">
        <label>Niže odjeljenje: </label>
        <div>
            <form:select path="odjeljenjeID" class="form-control"  data-val="true" data-val-required="The odjeljenjeID field is required." >
                <form:option value="0"> --SELECT--</form:option>
                <form:options items="${model.odjeljenja}"></form:options>
            </form:select>
            <span class="text-danger field-validation-valid" data-valmsg-for="odjeljenjeID" data-valmsg-replace="true"></span>
        </div>
    </div>

    <input type="submit" value="Snimi" />
</form:form>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>

