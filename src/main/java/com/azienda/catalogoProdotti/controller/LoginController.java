package com.azienda.catalogoProdotti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.azienda.catalogoProdotti.model.Utente;
import com.azienda.catalogoProdotti.service.AziendaService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	AziendaService as;
	
	
	//NAVIGAZIONE LOGIN
	
	
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "paginaLogin";
	}
	
	@GetMapping("/registrazione")
	public String registrazione() {
		return "paginaRegistrazione";
	}
	
	
	//METODI DEL CONTROLLER LOGIN
	
	
	@PostMapping("/loginControllo")
	public String loginControllo(@RequestParam("un") String username, @RequestParam("pwd") String password, Model model, HttpSession session)  {
		try {
			Utente utente = as.login(username, password);
			
			if(utente != null) {
				utente.setLoggato(true);
				as.saveUser(utente);
				session.setAttribute("utenteLoggato", utente);
				return "homeInterna";
			}
			
			throw new Exception();
			
		} catch (Exception e) {
			model.addAttribute("erroreLogin", "Username o password errati.");
			return "paginaLogin";
		}
		
	}
	
	
	@PostMapping("/registrazioneControllo")
	public String registrazioneControllo(@RequestParam("un") String username, @RequestParam("pwd") String password, Model model)  {
		try {
			Boolean bool = as.registration(username, password);
			
			
			if(bool) {
				model.addAttribute("successoRegistrazione", "Registrazione Avvenuta con Successo.");
				return "paginaLogin";
			}
			
			throw new Exception();
			
		} catch (Exception e) {
			model.addAttribute("erroreRegistrazione", "Username già esistente.");
			return "paginaRegistrazione";
		}
		
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session, Model model) {
	    try {
	        Utente utente = (Utente) session.getAttribute("utenteLoggato");
	        if (utente != null) {
	            as.logout(utente);
	        }

	        session.invalidate(); 
	        return "paginaLogin"; 
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errore", "Errore riscontrato nella fase di Logout.");
	        return "esito";
	    }
	}

	
}
