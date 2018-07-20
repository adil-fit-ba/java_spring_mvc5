<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDodajVM"--%>


<jsp:include page="/WEB-INF/views/shared/header.jsp"/>



<h2>Dodaj</h2>


<form method="POST" action="/odjeljenje/snimi" >

    <div class="form-group">
        <label>Školska godina: </label>
        <div>
            <input name="skolaGodina" class="form-control"  />

        </div>
    </div>

    <div class="form-group">
        <label>Razred: </label>
        <div>
            <input name="razred" class="form-control"  />

        </div>
    </div>

    <div class="form-group">
        <label>Oznaka: </label>
        <div>
            <input name="oznaka" class="form-control" />

        </div>
    </div>


    <div class="form-group">
        <label>Razrednik: </label>
        <div>
            <input name="nastavnik" class="form-control" readonly="true"/>
        </div>
    </div>


    <input type="submit" value="Snimi" />
</form>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>

