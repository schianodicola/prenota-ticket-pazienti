package it.prenota.ticket.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import it.prenota.ticket.model.entity.Prenotazione;


@Repository
public interface PrenotazioneDAO extends CrudRepository<Prenotazione, Integer>{
	
	//get lista Prenotazioni
	@Query("SELECT p FROM Prenotazione p")
	List<Prenotazione> findAllAlsoDisable();
	
	//get lista Prenotazioni abilitate
	@Query("SELECT p FROM Prenotazione p WHERE p.stato IS TRUE")
	List<Prenotazione> findAll();
	
	//get Prenotazione tramite codice
	@Query("SELECT p FROM Prenotazione p JOIN p.visita vv WHERE p.codice= :cod AND p.stato IS TRUE AND vv.stato IS TRUE")
	Prenotazione findByCodice(@Param("cod")String codice);
	
	//get Prenotazione tramite id
	@Query("SELECT p FROM Prenotazione p WHERE p.id= :id AND p.stato IS TRUE")
	Prenotazione findById(@Param("id")int id);
		
	//elimina Prenotazione tramite codice
	@Transactional
	@Modifying
	@Query("DELETE FROM Prenotazione p WHERE p.codice= :cod")
	int deleteByCodice(@Param("cod")String codice);
}
