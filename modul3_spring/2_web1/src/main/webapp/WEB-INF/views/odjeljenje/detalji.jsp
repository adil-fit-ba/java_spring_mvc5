<jsp:include page="/WEB-INF/views/shared/header.jsp"/>


<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.OdjeljenjeDetaljiVM"--%>

<h2>Detalji</h2>

<div>
    <div class="form-group">
        <label>Školska godina: </label>
        <div>
            <input type="text" class="form-control" readonly value="${model.skolskaGodina}" />
        </div>
    </div>

    <div class="form-group">
        <label>Razred: </label>
        <div>
            <input type="text" class="form-control" readonly value="${model.razred}" />
        </div>
    </div>

    <div class="form-group">
        <label>Oznaka: </label>
        <div>
            <input type="text" class="form-control" readonly value="${model.oznaka}" />
        </div>
    </div>

    <div class="form-group">
        <label>Razrednik: </label>
        <div>
            <input type="text" class="form-control" readonly value="${model.razrednik}" />
        </div>
    </div>

    <div class="form-group">
        <label>BrojUcenika: </label>
        <div>
            <input type="text" class="form-control" readonly value="${model.brojUcenika}" />
        </div>
    </div>


</div>

<div id="nekiID" style="border: 1px black solid"></div>

<script>
    $(document).ready(function(parameters) {
        $.get( "/stavke-ajax/index?odjeljenjeId=${model.odjeljenjeID}",
            function(rezultat, status) {
                $("#nekiID").html(rezultat);

            });
    });


</script>



<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>

