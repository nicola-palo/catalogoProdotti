package com.azienda.catalogoProdotti.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.azienda.catalogoProdotti.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{
	
	
	//RICERCA UTENTE TRAMITE USERNAME E PASSWORD
	@Query("SELECT u FROM Utente u WHERE u.username = :username AND u.password = :password")
	Utente findUserByUnAndPwd(@Param("username") String username, @Param("password") String password);

	//RICERCA UTENTE TRAMINTE IL SOLO USERNAME
	@Query("SELECT u FROM Utente u WHERE u.username = :username")
	Utente findUserByUn(@Param("username") String username);
	
	
}
