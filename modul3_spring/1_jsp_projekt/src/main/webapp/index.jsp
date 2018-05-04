<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="dao.MojDBInitializer" %>
<%@ page import="java.util.List" %>
<%@ page import="entitymodels.Ucenik" %>
<%@ include file="header.jsp" %>

<%!
    int count = 0;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");


%>

<%

    EntityManager em = emf.createEntityManager();
    MojDBInitializer.Izvrsi(em);

    List<Ucenik> podaci = em.createQuery("select x from Ucenik x", Ucenik.class).getResultList();

    em.close();
    count++;

%>

<%-- jsp comment --%>

<!-- html comment -->

<h2>Hello World!  <%= count %> </h2>

<a href="dodaj.jsp">Dodaj novi</a>

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
        int r=0;
    for (Ucenik x : podaci) { %>

    <tr>
        <td><%= ++r %></td>
        <td><%= x.getIme() %></td>
        <td><%= x.getPrezime() %></td>
    </tr>


    <%
    }
    %>


    </tbody>
</table>

<%@ include file="footer.jsp" %>