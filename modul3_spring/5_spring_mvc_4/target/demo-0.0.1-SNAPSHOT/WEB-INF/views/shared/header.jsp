<%@ page import="ba.fit.java.spring.mvc.helper.Autentifikacija" %>
<%@ page import="ba.fit.java.spring.mvc.entitymodels.KorisnickiNalog" %>
<% KorisnickiNalog korisnickiNalog = Autentifikacija.getLogiraniKorisnik(request); %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="UTF-8">
    <title>Ispit.Web</title>

    <%--css--%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/static/css/site.css" />

    <%--js--%>

    <script src="http://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.16.0/jquery.validate.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validation.unobtrusive/3.2.6/jquery.validate.unobtrusive.js"></script>



    <script  src="/static/js/jsFIT.js" ></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/" class="navbar-brand">Glavni modul</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <li><a href="/test-db">Test db</a></li>
                <li><a href="/odjeljenje/index">Odjeljenja</a></li>
                <li><a href="/ucenik-home/index">Ucenik home</a></li>

                <li><a href="/autentifikacija/logout">
                    <% if (korisnickiNalog != null)
                    {
                        out.print(korisnickiNalog.getKorisnickoIme() + " Logout");
                    }
                    else
                    {
                        out.print("Login");
                    }
                    %>
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container body-content">
