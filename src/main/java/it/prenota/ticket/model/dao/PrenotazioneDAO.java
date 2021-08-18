package it.prenota.ticket.model.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import it.prenota.ticket.model.entity.Prenotazione;
import it.prenota.ticket.model.entity.Visita;


@Repository
public interface PrenotazioneDAO extends CrudRepository<Prenotazione, Integer>{
	
	//CRUD
	
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
	
	//OPERAZIONI VARIE
	
	//FUNZIONANTE but not used
	@Query("SELECT v FROM Visita v WHERE v.nome= :nome ")
	Visita countNumPrenotazioniByVisitaAndByTempo(@Param("nome") String nome);
	
	//conta il numero di prenotazioni di una visita in un range di tempo
	@Query("SELECT COUNT(p) FROM Prenotazione p JOIN p.visita v WHERE v.nome= :nome AND p.data_appuntamento>= :time1 AND  p.data_appuntamento<= :time2 AND p.stato IS TRUE AND v.stato IS TRUE ")
	int countNumPrenotazioniByVisitaAndByTempo(@Param("nome") String nome, @Param("time1") LocalDateTime time1, @Param("time2") LocalDateTime time2);
	
	//lista prenotazioni di un paziente
	@Query("SELECT p FROM Prenotazione p JOIN p.paziente pa WHERE pa.cf= :cf AND p.stato IS TRUE")
	List<Prenotazione> findPrenByPaziente(@Param("cf") String cf);
	
}

