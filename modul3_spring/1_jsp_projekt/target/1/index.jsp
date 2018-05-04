<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="entitymodels.Ucenik" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.MojDBInitializer" %>
<%@ include file="header.jsp" %>

<%!
    int count = 0;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");
%>

<%
    EntityManager em = emf.createEntityManager();
    MojDBInitializer.Izvrsi(em);

    List<Ucenik> ucenici = em.createQuery("select x from Ucenik x", Ucenik.class).getResultList();

    em.close();
    count++;
%>



<h2>Hello World! <%= count%> </h2>


<table class="table table-bordered">
    <thead>
    <tr>
        <th>RB</th>
        <th>Ime</th>
        <th>Prezime</th>
    </tr>
    </thead>
    <tbody>
<%
    int b=0;
    for (Ucenik u : ucenici)
    {
%>
<tr>
    <td><%= b++ %></td>
    <td><%= u.getIme() %></td>
    <td><%= u.getPrezime() %></td>
</tr>

<%
    }
%>

    </tbody>
</table>


<%@ include file="footer.jsp" %>