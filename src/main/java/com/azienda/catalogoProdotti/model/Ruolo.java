package com.azienda.catalogoProdotti.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@OneToMany(mappedBy = "ruolo")
	private List<Utente> listaUtenti;

	public Ruolo(String nome) {
		super();
		this.nome = nome;
	}


	public Ruolo() {
		super();
	}


	public List<Utente> getListaUtenti() {
		return listaUtenti;
	}


	public void setListaUtenti(List<Utente> listaUtenti) {
		this.listaUtenti = listaUtenti;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "Ruolo [id=" + id + ", nome=" + nome + "]";
	}





}
