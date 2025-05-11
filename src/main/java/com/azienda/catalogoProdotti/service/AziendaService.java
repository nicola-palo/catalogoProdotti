package com.azienda.catalogoProdotti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.azienda.catalogoProdotti.model.Prodotto;
import com.azienda.catalogoProdotti.model.Ruolo;
import com.azienda.catalogoProdotti.model.Utente;
import com.azienda.catalogoProdotti.repository.ProdottoRepository;
import com.azienda.catalogoProdotti.repository.RuoloRepository;
import com.azienda.catalogoProdotti.repository.UtenteRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class AziendaService {

	@Autowired
	ProdottoRepository prodottoRepository;
	@Autowired
	RuoloRepository ruoloRepository;
	@Autowired
	UtenteRepository utenteRepository;



	//INIZIALIZZAZIONE UTENTI
	@PostConstruct
	public void initUsers() {
		Ruolo admin = new Ruolo("ADMIN");
		Ruolo utente = new Ruolo("UTENTE");
		ruoloRepository.save(admin);
		ruoloRepository.save(utente);

		Utente amministratore = new Utente("admin", "admin", admin);
		Utente user = new Utente("user", "user", utente);
		utenteRepository.save(amministratore);
		utenteRepository.save(user);
	}


	//INIZIALIZZAZIONE PRODOTTI
	@PostConstruct
	public void initProducts() {
		Prodotto p1 = new Prodotto("latte", 1.22f);
		Prodotto p2 = new Prodotto("pasta", 0.92f);
		Prodotto p3 = new Prodotto("cereali", 3.29f);
		Prodotto p4 = new Prodotto("formaggio", 5.19f);
		Prodotto p5 = new Prodotto("quaderni", 1.49f);

		prodottoRepository.save(p1);
		prodottoRepository.save(p2);
		prodottoRepository.save(p3);
		prodottoRepository.save(p4);
		prodottoRepository.save(p5);

	}


	//OPERAZIONI UTENTI SUL DATABASE


	//SALVA UTENTE
	public void saveUser(Utente utente) {
		utenteRepository.save(utente);
	}

	//LOGIN DI UN UTENTE CON AGGIUNTA STATO TRUE IN 'LOGGATO'
	public Utente login(String username, String password) {
		return utenteRepository.findUserByUnAndPwd(username, password);
	}

	//REGISTRAZIONE DI UN UTENTE 
	public Boolean registration(String username, String password) {
		if (utenteRepository.findUserByUn(username) != null) {
			return false;
		}

		Ruolo ruoloUtente = ruoloRepository.findById(2).orElse(null);
		if (ruoloUtente == null) {
			throw new RuntimeException("Ruolo UTENTE non trovato!");
		}

		Utente nuovoUtente = new Utente(username, password, ruoloUtente);

		utenteRepository.save(nuovoUtente);
		return true;
	}

	//RIMOZIONE DELLO STATO 'LOGGATO' DA TRUE A FALSE
	public void logout(Utente utente) {
		utente.setLoggato(false);
		utenteRepository.save(utente);
	}

	//RICERCA DI UN UTENTE TRAMITE USERNAME
	public Utente findUserByUn(String username) {
		return utenteRepository.findUserByUn(username);
	}


	//OPERAZIONI PRODOTTI SU DATABASE


	//CREAZIONE DI UN PRODOTTO
	public Boolean createProduct(String nomeProdotto, Float prezzoProdotto) {
		Prodotto prodotto = prodottoRepository.findProductByNameEquals(nomeProdotto);
		if (prodotto == null) {
			Prodotto prodottoDaCreare = new Prodotto(nomeProdotto, prezzoProdotto);
			prodottoRepository.save(prodottoDaCreare);
			return true;
		} else {
			return false;
		}
	}

	//RICERCA DEI PRODOTTI TRAMITE NOME E/O PREZZO
	public List<Prodotto> findListProduct(String nome, Float prezzo) {
		boolean nomePresente = nome != null && !nome.trim().isEmpty();
		boolean prezzoPresente = prezzo != null;

		if (nomePresente && prezzoPresente) {
			return prodottoRepository.findListProductsByNameAndPrice(nome, prezzo);
		} else if (nomePresente) {
			return prodottoRepository.findListProductsByName(nome);
		} else if (prezzoPresente) {
			return prodottoRepository.findListProductsByPriceMax(prezzo);
		} else {
			return List.of(); 
		}
	}

	//RICERCA DI UN PRODOTTO TRAMITE NOME UGUALE
	public Prodotto findSingleProductByNameEquals(String nome) {
		return prodottoRepository.findProductByNameEquals(nome);
	}

	//RICERCA DI UNA LISTA PRODOTTI TRAMITE NOME E PREZZO MINORE
	public List<Prodotto> findListProductsByNameAndPriceLow(String nome, Float prezzo) {
		return prodottoRepository.findListProductsByNameAndPriceLow(nome, prezzo);
	}

	//RICERCA DI UNA LISTA PRODOTTI TRAMITE PREZZO MINORE DI
	public List<Prodotto> findListProductsByPriceLow (Float prezzo) {
		return prodottoRepository.findListProductsByPriceLow(prezzo);
	}

	//RICERCA DI UNA LISTA PRODOTTI TRAMITE PREZZO MINORE DI
	public List<Prodotto> findListProductsByPriceMax (Float prezzo) {
		return prodottoRepository.findListProductsByPriceMax(prezzo);
	}

	//RICERCA DI UNA LISTA PRODOTTI TRAMITE NOME
	public List<Prodotto> findListProductsByName (String nomeProdotto) {
		return prodottoRepository.findListProductsByName(nomeProdotto);
	}

	//RICERCA DI UN PRODOTTO TRAMITE ID
	public Prodotto findProductById (Integer id) {
		return prodottoRepository.findById(id).orElse(null);
	}

	//SALVATAGGIO DI UN PRODOTTO
	public void saveProduct(Prodotto p) {
		prodottoRepository.save(p);
	}

	//ELIMINAZIONE DI UN PRODOTTO TRAMITE ID
	public void deleteProductById(Integer id) {
		prodottoRepository.deleteById(id);
	}

	//RICERCA DELLA LISTA PRODOTTI
	public List<Prodotto> getAllProduct () {
		return prodottoRepository.findAll();
	}

	//ELIMINAZIONE DEI PRODOTTI CON IL PREZZO TRA I VALORI INSERITI
	public void deleteProductsByPriceLowAndMax(Float prezzoMin, Float prezzoMax) {
		prodottoRepository.deleteProductsByPriceLowAndMax(prezzoMin, prezzoMax);
	}


}
