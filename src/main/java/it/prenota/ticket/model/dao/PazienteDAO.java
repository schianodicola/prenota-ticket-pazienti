package it.prenota.ticket.model.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.prenota.ticket.model.entity.Paziente;

@Repository
public interface PazienteDAO extends CrudRepository<Paziente, Integer>{

	//CRUD
	
	//get lista pazienti
	@Query("SELECT p FROM Paziente p")
	Set<Paziente> findAll();
		
	//get paziente tramite cf
	@Query("SELECT p FROM Paziente p WHERE p.cf= :cf")
	Paziente findByCf(@Param("cf")String cf);
		
	//get paziente tramite id
	@Query("SELECT p FROM Paziente p WHERE p.id_paziente= :id")
	Paziente findById(@Param("id")int id);
		
	//elimina paziente
	@Transactional
	@Modifying
	@Query("DELETE FROM Paziente p WHERE p= :paziente")
	int deleteByPaziente(@Param("paziente") Paziente paziente);
		
	//elimina paziente tramite cf
	@Transactional
	@Modifying
	@Query("DELETE FROM Paziente p WHERE p.cf= :cf")
	int deleteByCf(@Param("cf")String cf);
	
	//OPERAZIONI VARIE
	
	//get lista pazienti prenotati ad una determinata visita
	@Query("SELECT DISTINCT pa FROM Prenotazione pr JOIN pr.visita vi JOIN pr.paziente pa WHERE vi.nome= :nome AND pr.stato IS TRUE AND vi.stato IS TRUE")
	List<Paziente> findByVisitaNome(@Param("nome") String nome);
	
}
