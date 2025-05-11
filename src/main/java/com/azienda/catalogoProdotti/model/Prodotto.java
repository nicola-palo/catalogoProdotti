package com.azienda.catalogoProdotti.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Prodotto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Float prezzo;



	public Prodotto() {
		super();
	}
	public Prodotto(String nome, Float prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;

	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}
	@Override
	public String toString() {
		return "Prodotto [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + "]";
	}
	public Integer getId() {
		return id;
	}




}
