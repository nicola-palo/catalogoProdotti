package com.azienda.catalogoProdotti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.azienda.catalogoProdotti.model.Prodotto;
import com.azienda.catalogoProdotti.service.AziendaService;

@RestController
@RequestMapping(path = "/rest/prodotti", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoControllerRest {

	private static final String AUTH_SERVICE_URL = "http://localhost:8080/catalogoProdotti/rest/utente/login";
	private static final String ADMIN_SERVICE_URL = "http://localhost:8080/catalogoProdotti/rest/utente/isAdmin";

	@Autowired
	AziendaService as;

	@Autowired
	RestTemplate restTemplate;

	//RICHIAMO AL LOGIN CONTROLLER PER VERIFICA LOG
	void login(String username, String password) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("username", username);
			headers.set("password", password);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange(
					AUTH_SERVICE_URL,
					HttpMethod.POST,
					entity,
					String.class
					);

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new ResponseStatusException(response.getStatusCode(), "Autenticazione fallita");
			}

		} catch (HttpClientErrorException e) {

			throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la validazione utente");
		}
	}

	//RICHIAMO A LOGIN PER VERIFICA ADMIN
	private void isAdmin(String username, String password) {
		try {
			System.out.println("Username ricevuto: " + username);
			System.out.println("Password ricevuta: " + password);

			HttpHeaders headers = new HttpHeaders();
			headers.set("username", username);
			headers.set("password", password);

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange(
					ADMIN_SERVICE_URL,
					HttpMethod.POST,
					entity,
					String.class
					);

			if (response.getStatusCode() != HttpStatus.OK) {
				throw new ResponseStatusException(response.getStatusCode(), "Autenticazione fallita");
			}

		} catch (HttpClientErrorException e) {
			System.out.println("Errore HTTP: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
			throw new ResponseStatusException(e.getStatusCode(), e.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore durante la validazione utente");
		}
	}

	//RICHIESTA LISTA PRODOTTI
	@GetMapping("/listaProdotti")
	public List<Prodotto> ListaProdotti(@RequestHeader("username") String username, @RequestHeader("password") String password) {
		login(username, password);
		return as.getAllProduct();
	}

	//RICHIESTA PRODOTTO SINGOLO PER ID
	@GetMapping("/cercaProdottoConId/{id}")
	public ResponseEntity<Prodotto> cercaProdottoConId(@RequestHeader("username") String username, @RequestHeader("password") String password, @PathVariable("id") Integer id) {
		try {
			login(username, password);

			Prodotto p = as.findProductById(id);

			if (p != null) {
				return new ResponseEntity<Prodotto>(p, HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//RICERCA LISTA PRODOTTI PER NOME
	@GetMapping("/cercaProdottoConNome/{nome}")
	public ResponseEntity<List<Prodotto>> cercaProdottoConNome(@RequestHeader("username") String username, @RequestHeader("password") String password, @PathVariable("nome") String nome) {
		try {
			login(username, password);
			List<Prodotto> p = as.findListProductsByName(nome);

			if (p != null && !p.isEmpty()) {
				return new ResponseEntity<>(p, HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//RICERCA LISTA PRODOTTI PER PREZZO MINIMO
	@GetMapping("/cercaProdottoConPrezzoMin/{prezzo}")
	public ResponseEntity<List<Prodotto>> cercaProdottoConPrezzoMin(@RequestHeader("username") String username, @RequestHeader("password") String password,@PathVariable("prezzo") Float prezzo) {
		try {
			login(username, password);
			List<Prodotto> p = as.findListProductsByPriceLow(prezzo);

			if (p.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
			}

			return ResponseEntity.ok(p); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}

	//RICERCA LISTA PRODOTTI PER NOME E PREZZO MINIMO
	@GetMapping("/cercaProdottoConNomeEPrezzoMin/{nome}/{prezzo}")
	public ResponseEntity<List<Prodotto>> cercaProdottoConNomeEPrezzoMin(@RequestHeader("username") String username, @RequestHeader("password") String password, @PathVariable("prezzo") Float prezzo, @PathVariable("nome") String nome) {
		try {
			login(username, password);
			List<Prodotto> p = as.findListProductsByNameAndPriceLow(nome, prezzo);

			if (p.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
			}

			return ResponseEntity.ok(p); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}

	//CREAZIONE PRODOTTO SINGOLO
	@PostMapping(path="/creaProdotto", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Prodotto> creaProdotto(@RequestHeader("username") String username, @RequestHeader("password") String password,@RequestBody Prodotto p) {
		try {
			isAdmin(username, password);
			
			Boolean bool = as.createProduct(p.getNome(), p.getPrezzo());


			if(!bool) {
				return ResponseEntity.status(HttpStatus.CONFLICT).build(); 
			}
			return ResponseEntity.ok(p); 

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}

	//AGGIORNAMENTO COMPLETO PRODOTTO PER ID 
	@PutMapping(path="/aggiornamentoCompletoProdotto/{id}", consumes = "application/json")
	public ResponseEntity<Prodotto> aggiornamentoCompletoProdotto(@RequestHeader("username") String username, @RequestHeader("password") String password, @RequestBody Prodotto p, @PathVariable("id") Integer id) {
		try {

			Prodotto pDb = as.findProductById(id);

			if (pDb != null) {
				pDb.setNome(p.getNome());
				pDb.setPrezzo(p.getPrezzo());
				as.saveProduct(pDb);
				return new ResponseEntity<>(pDb, HttpStatus.OK);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
		}
	}

	//AGGIORNAMENTO PARZIALE PRODOTTO PER ID
	@PatchMapping(path="/aggiornamentoParzialeProdotto/{id}", consumes = "application/json")
	public ResponseEntity<Prodotto> aggiornamentoParziale(@RequestHeader("username") String username, @RequestHeader("password") String password,@RequestBody Prodotto p, @PathVariable("id") Integer id) {
		try {

			Prodotto pDb = as.findProductById(id);

			if (pDb != null) {

				if (p.getNome() != null) {
					pDb.setNome(p.getNome());
				}
				if (p.getPrezzo() != null) {
					pDb.setPrezzo(p.getPrezzo());
				}
				as.saveProduct(pDb);
				return new ResponseEntity<>(pDb, HttpStatus.OK);
			}


			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//CANCELLA PRODOTTO PER ID
	@DeleteMapping("/cancellaProdotto/{id}")
	public ResponseEntity<Prodotto> cancellaProdotto(@RequestHeader("username") String username, @RequestHeader("password") String password,@PathVariable("id") Integer id) {
		try {

			as.deleteProductById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//CANCELLA PRDOTTI CON PREZZO COMPRESO TRA UN MINIMO ED UN MASSIMO
	@DeleteMapping("/cancellaProdottoConPrezzoCompresoTraMinEMax/{prezzoMin}/{prezzoMax}")
	public ResponseEntity<Prodotto> cancellaProdottoConPrezzoCompresoTraMinEMax(@RequestHeader("username") String username, @RequestHeader("password") String password, @PathVariable("prezzoMin") Float prezzoMin, @PathVariable("prezzoMax") Float prezzoMax) {
		try {

			as.deleteProductsByPriceLowAndMax(prezzoMin, prezzoMax);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}




}
