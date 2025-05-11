package com.azienda.catalogoProdotti.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azienda.catalogoProdotti.model.Utente;
import com.azienda.catalogoProdotti.service.AziendaService;


@RestController
@RequestMapping(path = "/rest/utente", produces = "application/json")
@CrossOrigin(origins = "*")
public class LoginControllerRest {

	@Autowired
	AziendaService as;


	@PostMapping("/login")
	public ResponseEntity<Utente> login(
			@RequestHeader(value = "username", required = false) String username,
			@RequestHeader(value = "password", required = false) String password) {
		try {
			// 400 
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

			Utente utente = as.login(username, password);

			// 401 
			if (utente == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			// 200 
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// 500 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/isAdmin")
	public ResponseEntity<Utente> isAdmin(
			@RequestHeader(value = "username", required = false) String username,
			@RequestHeader(value = "password", required = false) String password) {
		try {
			// 400 
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

			Utente utente = as.login(username, password);

			// 401 
			if (utente == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}

			// 403
			if (!"ADMIN".equals(utente.getRuolo().getNome())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}

			// 200 
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// 500 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/registrazione")
	public ResponseEntity<Utente> registrazione(
			@RequestHeader(value = "username", required = false) String username,
			@RequestHeader(value = "password", required = false) String password) {
		try {

			// 400 
			if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

			Utente utente = as.login(username, password);

			// 409
			if (utente != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
			
			as.registration(username, password);
			// 200 
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// 500 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}










}