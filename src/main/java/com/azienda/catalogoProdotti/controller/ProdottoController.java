package com.azienda.catalogoProdotti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azienda.catalogoProdotti.model.Prodotto;
import com.azienda.catalogoProdotti.service.AziendaService;

@Controller
public class ProdottoController {

	@Autowired
	AziendaService as;


	//NAVIGAZIONE PRODOTTO


	@GetMapping("/creaProdotto")
	public String creaProdotto() {
		return "creaProdotto";
	}

	@GetMapping("/homeInterna")
	public String homeInterna() {
		return "homeInterna";
	}

	@GetMapping("/ricercaProdotti")
	public String ricercaProdotti() {
		return "ricercaProdotti";
	}

	@GetMapping("/modificaCompletaProdotto")
	public String modificaCompletaProdotto(@RequestParam("id") Integer id, Model model) {
		Prodotto prodotto = as.findProductById(id); 
		model.addAttribute("prodotto", prodotto); 
		return "modificaCompletaProdotto";
	}

	@GetMapping("/modificaParzialeProdotto")
	public String modificaParzialeProdotto(@RequestParam("id") Integer id, Model model) {
		Prodotto prodotto = as.findProductById(id); 
		model.addAttribute("prodotto", prodotto); 
		return "modificaParzialeProdotto";
	}


	//METODI DEL CONTROLLER PRODOTTO

	
	//CREAZIONE PRODOTTO CON CONTROLLO DOPPIONE
	@PostMapping("/creaProdottoControllo")
	public String creazioneProdotti(@RequestParam("np") String nomeProdotto, @RequestParam("price") Float prezzoProdotto, Model model) {
		try {
			Boolean bool = as.createProduct(nomeProdotto, prezzoProdotto);

			if(bool) {
				model.addAttribute("successo", "Prodotto Creato con Successo.");
				return "esito";
			}

			throw new Exception();

		} catch (Exception e) {
			model.addAttribute("errore", "Il prodotto esiste già, oppure errore non riconosciuto.");
			return "esito";
		}
	}

	//RICERCA PRODOTTI
	@PostMapping("/ricercaProdottiControllo") 
	public String ricercaProdottiControllo(@RequestParam(value = "np", required = false) String nomeProdotto, @RequestParam(value = "price", required = false) Float prezzoProdotto, Model model) {
		try {
			List<Prodotto> ris = as.findListProduct(nomeProdotto, prezzoProdotto);

			if(!ris.isEmpty()) {
				model.addAttribute("prodotti", ris);
				return "risultatiRicercaProdotti";
			}

			throw new Exception();

		} catch (Exception e) {
			model.addAttribute("errore", "Non esistono prodotti con i caratteri da te inseriti, oppure errore non riconosciuto.");
			return "esito";
		}
	}

	//MODIFICA COMPLETA DEL PRODOTTO
	@PostMapping("/modificaCompletaProdottoControllo")
	public String modificaCompletaProdottoControllo( @RequestParam("id") Integer id, @RequestParam("np") String nome, @RequestParam("price") Float prezzo, Model model) {
		try {
			Prodotto prodotto = as.findProductById(id); 
			if (prodotto != null && !prodotto.getNome().equals(nome) && !prodotto.getPrezzo().equals(prezzo)) {
				prodotto.setNome(nome);
				prodotto.setPrezzo(prezzo);
				as.saveProduct(prodotto);

				model.addAttribute("successo", "Il prodotto è stato modificato con successo.");
				return "esito"; 
			}
			throw new Exception();

		} catch (Exception e) {
			model.addAttribute("errore", "Errore nella modifica del prodotto, probabilmente non hai modificato TUTTI i dati disponibili.");
			return "esito";
		}
	}

	//MODIFICA PARZIALE DEL PRODOTTO
	@PostMapping("/modificaParzialeProdottoControllo")
	public String modificaParzialeProdottoControllo( @RequestParam("id") Integer id, @RequestParam("np") String nome, @RequestParam("price") Float prezzo, Model model) {
		try {
			Prodotto prodotto = as.findProductById(id); 
			if (prodotto != null) {
				prodotto.setNome(nome);
				prodotto.setPrezzo(prezzo);
				as.saveProduct(prodotto);

				model.addAttribute("successo", "Il prodotto è stato modificato con successo.");
				return "esito"; 
			}
			throw new Exception();

		} catch (Exception e) {
			model.addAttribute("errore", "Errore nella modifica del prodotto.");
			return "esito";
		}
	}

	//ELIMINAZIONE DEL PRODOTTO
	@PostMapping("/eliminaProdotto")
	public String eliminaProdotto(@RequestParam("id") Integer id, Model model) {
		try {
			Prodotto prodotto = as.findProductById(id); 

			if (prodotto.getId() != null) {
				as.deleteProductById(id);
				return "ricercaProdotti";
			}
			throw new Exception();

		} catch (Exception e) {
			model.addAttribute("errore", "Errore nell'eliminazione del prodotto.");
			return "esito";
		}
	}



}
