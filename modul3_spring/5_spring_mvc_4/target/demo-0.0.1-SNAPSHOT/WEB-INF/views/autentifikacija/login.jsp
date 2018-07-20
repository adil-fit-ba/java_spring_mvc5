<jsp:include page="/WEB-INF/views/shared/header.jsp"/>



<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="model" type="ba.fit.java.spring.mvc.viewmodels.LoginVM"--%>


<div class="col-md-4 col-md-offset-4">
    <h2>e-Dnevnik</h2>

    <form:form action="/autentifikacija/login" method="post" modelAttribute="model">

        <div class="form-group">
            <label>Username: </label>
            <div>
                <form:input path="username" class="form-control"  data-val="true" data-val-length="Korisni&#x10D;ko ime mora sadr&#x17E;avati mininalno 3 karaktera." data-val-length-max="100" data-val-length-min="3"/>
            </div>
        </div>

        <div class="form-group">
            <label>Password: </label>
            <div>
                <form:password path="password" class="form-control" data-val="true" data-val-length="Password mora sadr&#x17E;avati mininalno 4 karaktera." data-val-length-max="100" data-val-length-min="4" />
            </div>
        </div>


        <input type="submit" class="form-control btn btn-primary" value="Login" />
        <form:checkbox path="zapamtiPassword" />   Zapamti password

       <form:errors path="*" cssClass="alert alert-danger" element="div" />
    </form:form>


</div>


<jsp:include page="/WEB-INF/views/shared/footer.jsp"/>