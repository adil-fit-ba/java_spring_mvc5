<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="/WEB-INF/views/shared/header.jsp"/>

<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM"--%>

<h2>Dodaj</h2>


<form method="POST" action="/odjeljenje/snimi" >

    <div class="form-group">
        <label>Školska godina: </label>
        <div>
            <input name="skolaGodina" class="form-control"  value="${model.skolaGodina}" />

        </div>
    </div>

    <div class="form-group">
        <label>Razred: </label>
        <div>
            <input name="razred" class="form-control"  value="${model.razred}" />

        </div>
    </div>

    <div class="form-group">
        <label>Oznaka: </label>
        <div>
            <input name="oznaka" class="form-control" value="${model.oznaka}" />

        </div>
    </div>


    <div class="form-group">
        <label>Razrednik: </label>
        <div>
            <input name="nastavnik" class="form-control" readonly="true" value="${model.nastavnik}"/>
        </div>
    </div>
    <input name="odjeljenjeId" class="form-control" type="hidden"  value="${model.odjeljenjeId}" />


    <input type="submit" value="Snimi" />
</form>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>

