<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="dao.MojDBInitializer" %>
<%@ page import="java.util.List" %>
<%@ page import="entitymodels.Ucenik" %>
<%@ page import="entitymodels.KorisnickiNalog" %>
<%@ include file="header.jsp" %>

<%!
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MojaOznaka1");


%>

<%

    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    String ime = request.getParameter("ime");
    String prezime = request.getParameter("prezime");

    Ucenik ucenik = new Ucenik();
    ucenik.setIme(ime);
    ucenik.setPrezime(prezime);
    ucenik.setKorisnickiNalog(new KorisnickiNalog());


    em.persist(ucenik);
    em.getTransaction().commit();

%>

Ucenik je dodat u db.
<a href="index.jsp">Prikazi</a>

<%@ include file="footer.jsp" %>