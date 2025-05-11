<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrazione</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex justify-content-center align-items-center vh-100">

    <div class="container bg-white p-4 rounded shadow" style="max-width: 400px;">
        <h1 class="mb-4 text-center">Registrazione</h1>

        <c:if test="${not empty erroreRegistrazione}">
            <div class="alert alert-danger">${erroreRegistrazione}</div>
        </c:if>

        <form action="/catalogoProdotti/registrazioneControllo" method="post">
            <div class="mb-3">
                <label for="un" class="form-label">Username</label>
                <input type="text" class="form-control" id="un" name="un" required>
            </div>

            <div class="mb-3">
                <label for="pwd" class="form-label">Password</label>
                <input type="password" class="form-control" id="pwd" name="pwd" required>
            </div>

            <button type="submit" class="btn btn-success w-100">Registrati</button>
        </form>

        <div class="mt-4 text-center">
            <p>Oppure effettua il <a href="/catalogoProdotti/login">Login</a></p>
            <p><a href="/catalogoProdotti/home">Torna alla Home</a></p>
        </div>
    </div>

    <!-- Bootstrap JS (opzionale) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
