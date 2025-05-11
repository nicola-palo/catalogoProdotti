package com.azienda.catalogoProdotti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.azienda.catalogoProdotti.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Integer>{
	
	
	//RICERCA PRODOTTO PER NOME UGUALE
	@Query("SELECT p FROM Prodotto p WHERE p.nome = :nome")
	public Prodotto findProductByNameEquals(@Param("nome") String nome);
	
	//RICERCA DI UNA LISTA PRODOTTI 
	@Query("SELECT p FROM Prodotto p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
	public List<Prodotto> findListProductsByName(@Param("nome") String nome);
	
	//RICERCA DI UNA LISTA TRAMITE NOME E PREZZO MAGGIORE DELL'INSERITO
	@Query("SELECT p FROM Prodotto p WHERE p.nome LIKE CONCAT('%', :nome, '%') AND p.prezzo >= :prezzo")
	public List<Prodotto> findListProductsByNameAndPrice(@Param("nome") String nome, @Param("prezzo") Float prezzo);
	
	//RICERCA DI UNA LISTA TRAMITE IL SOLO PREZZO MAGGIORE DI
	@Query("SELECT p FROM Prodotto p WHERE p.prezzo >= :prezzo")
	public List<Prodotto> findListProductsByPriceMax(@Param("prezzo") Float prezzo);
	
	//RICERCA DI UNA LISTA TRAMITE IL SOLO PREZZO MINORE DI
	@Query("SELECT p FROM Prodotto p WHERE p.prezzo <= :prezzo")
	public List<Prodotto> findListProductsByPriceLow(@Param("prezzo") Float prezzo);
	
	//RICERCA DI UNA LISTRA TRAMITE NOME E PREZZO MINORE DELL'INSERITO
	@Query("SELECT p FROM Prodotto p WHERE p.nome LIKE CONCAT('%', :nome, '%') AND p.prezzo <= :prezzo")
	public List<Prodotto> findListProductsByNameAndPriceLow(@Param("nome") String nome, @Param("prezzo") Float prezzo);
	
	//ELIMINAZIONE DEI PRODOTTI CON IL PREZZO TRA I VALORI INSERITI
	@Modifying
	@Query("DELETE FROM Prodotto p WHERE p.prezzo BETWEEN :prezzoMin AND :prezzoMax")
	public void deleteProductsByPriceLowAndMax(@Param("prezzoMin") Float prezzoMin, @Param("prezzoMax") Float prezzoMax);


}