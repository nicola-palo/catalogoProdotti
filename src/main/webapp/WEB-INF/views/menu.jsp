<%@page import="com.azienda.catalogoProdotti.model.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="d-flex flex-column">

    <%
        Utente utente = (Utente) session.getAttribute("utenteLoggato");
        if (utente != null && utente.getRuolo().getId() == 1) {
    %>
        <a href="/catalogoProdotti/creaProdotto" class="text-white mb-3 fs-5 text-decoration-none">Crea Prodotto</a>
    <%
        }
    %>

    <a href="/catalogoProdotti/ricercaProdotti" class="text-white mb-3 fs-5 text-decoration-none">Ricerca Prodotti</a>

    <form action="/catalogoProdotti/logout" method="post">
        <button type="submit" class="btn btn-link text-white fs-5 p-0 mb-3 text-start">Logout</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

