<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Esito</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <div class="d-flex w-100 vh-100">
        <div class="col-12 col-md-2 bg-dark text-white p-4">
			<jsp:include page="menu.jsp" />
		</div>

        <div class="flex-grow-1 d-flex justify-content-center align-items-center p-5">
            <div class="bg-white shadow rounded p-5 w-100" style="max-width: 600px;">
                <h2 class="mb-4 text-center text-primary">🔔 Esito Operazione</h2>

                <c:if test="${not empty successo}">
                    <div class="alert alert-success text-center" role="alert">
                        <strong>Successo!</strong> ${successo}
                    </div>
                </c:if>

                <c:if test="${not empty errore}">
                    <div class="alert alert-danger text-center" role="alert">
                        <strong>Errore!</strong> ${errore}
                    </div>
                </c:if>

                <div class="mt-4 text-center">
                    <a href="/catalogoProdotti/homeInterna" class="btn btn-outline-secondary">🏠 Torna alla Home</a>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

