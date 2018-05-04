<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="dao.MojDBInitializer" %>
<%@ page import="java.util.List" %>
<%@ page import="entitymodels.Ucenik" %>
<%@ include file="header.jsp" %>

<form action="snimi.jsp">
    Ime <input type="text" name="ime">
    Prezime <input type="text" name="prezime">
     <input type="submit" value="Snimi">
</form>

<%@ include file="footer.jsp" %>