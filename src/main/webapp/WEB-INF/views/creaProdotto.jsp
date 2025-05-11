<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Creazione Prodotto</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light vh-100 d-flex">

	<div class="d-flex w-100">
		<div class="col-12 col-md-2 bg-dark text-white p-4">
			<jsp:include page="menu.jsp" />
		</div>

		<div
			class="flex-grow-1 d-flex align-items-center justify-content-center p-5">
			<div class="bg-white shadow rounded p-5"
				style="width: 100%; max-width: 500px;">
				<h2 class="mb-4 text-center text-primary">📦 Creazione Prodotto</h2>

				<form action="/catalogoProdotti/creaProdottoControllo" method="post">
					<div class="mb-3">
						<label for="np" class="form-label">Nome del Prodotto</label> <input
							type="text" id="np" name="np" class="form-control" placeholder="Inserisci nome" required />
					</div>

					<div class="mb-3">
						<label for="price" class="form-label">Prezzo (€)</label> <input
							type="number" id="price" name="price" class="form-control"
							step="0.01" placeholder="Inserisci prezzo" required />
					</div>

					<button type="submit" class="btn btn-success w-100">📝
						Crea Prodotto</button>
				</form>

				<div class="mt-4 text-center">
					<a href="/catalogoProdotti/homeInterna"
						class="btn btn-outline-secondary">🏠 Torna alla Home</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
