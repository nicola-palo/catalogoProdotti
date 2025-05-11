<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Interna</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="container-fluid vh-100">
        <div class="row h-100">
            <div class="col-12 col-md-2 bg-dark text-white p-4">
                <jsp:include page="menu.jsp" />
            </div>

            <div class="col-12 col-md-10 d-flex flex-column justify-content-center align-items-center text-center bg-white p-5">
                <h1 class="mb-3">Benvenuto nell'area privata!</h1>
                <p class="fs-5 text-success">Sei connesso al tuo account.</p>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
