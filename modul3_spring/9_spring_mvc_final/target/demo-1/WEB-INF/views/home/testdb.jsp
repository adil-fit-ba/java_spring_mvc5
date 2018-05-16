<jsp:include page="/WEB-INF/views/shared/header.jsp"/>


<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.HomeTestDbVM"--%>

<h2>Test DB - row count</h2>

Ucenik: ${model.ucenikCount} <br/>
Odjeljenje: ${model.odjeljenjeCount} <br/>
Predmet: ${model.predmetCount} <br/>
Nastavnik: ${model.nastavnikCount} <br/>


<a href="/" class="btn btn-primary">Nazad</a>

<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>