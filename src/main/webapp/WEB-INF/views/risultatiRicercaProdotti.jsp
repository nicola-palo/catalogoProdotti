<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.azienda.catalogoProdotti.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Risultati Ricerca Prodotti</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-body-secondary vh-100 d-flex">

	<%
    Utente utente = (Utente) session.getAttribute("utenteLoggato");
    boolean isAdmin = utente != null && utente.getRuolo().getId() == 1;
    request.setAttribute("isAdmin", isAdmin);
%>

	<div class="d-flex w-100">
		<div class="col-12 col-md-2 bg-dark text-white p-4">
			<jsp:include page="menu.jsp" />
		</div>


		<div class="flex-grow-1 p-5 bg-white overflow-auto">
			<h2 class="mb-4 text-primary-emphasis">📦 Risultati Ricerca
				Prodotti</h2>

			<c:if test="${prodotti.size() > 0}">
				<div class="table-responsive">
					<table class="table table-striped table-hover align-middle">
						<thead class="table-primary">
							<tr>
								<th>Nome Prodotto</th>
								<th>Prezzo</th>
								<th>Azione</th>
								<c:if test="${isAdmin}">
									<th colspan="3">Admin</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="p" items="${prodotti}">
								<tr>
									<td>${p.nome}</td>
									<td>€ ${p.prezzo}</td>
									
									<c:if test="${isAdmin}">
										<td>
											<form action="/catalogoProdotti/modificaCompletaProdotto"
												method="get" class="d-inline">
												<input type="hidden" name="id" value="${p.id}" />
												<button type="submit" class="btn btn-sm btn-warning">✏️
													Completa</button>
											</form>
										</td>
										<td>
											<form action="/catalogoProdotti/modificaParzialeProdotto"
												method="get" class="d-inline">
												<input type="hidden" name="id" value="${p.id}" />
												<button type="submit" class="btn btn-sm btn-info text-white">🛠️
													Parziale</button>
											</form>
										</td>
										<td>
											<form action="/catalogoProdotti/eliminaProdotto"
												method="post" class="d-inline">
												<input type="hidden" name="id" value="${p.id}" />
												<button type="submit" class="btn btn-sm btn-danger">🗑️
													Cancella</button>
											</form>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>

			<c:if test="${prodotti.size() == 0}">
				<div class="alert alert-warning">⚠️ Nessun prodotto trovato.</div>
			</c:if>

			<div class="mt-4">
				<a href="/catalogoProdotti/homeInterna"
					class="btn btn-outline-secondary">🏠 Torna alla Home</a>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
