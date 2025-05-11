package com.azienda.catalogoProdotti.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private Boolean loggato = false;
	@ManyToOne
	private Ruolo ruolo;



	public Utente() {
		super();
	}


	public Utente(String username, String password, Ruolo ruolo) {
		super();
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoggato() {
		return loggato;
	}
	public void setLoggato(Boolean loggato) {
		this.loggato = loggato;
	}
	@Override
	public String toString() {
		return "Utente [id=" + id + ", username=" + username + ", password=" + password + ", loggato=" + loggato + "]";
	}

}
