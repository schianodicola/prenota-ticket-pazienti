package it.prenota.ticket.model.dao;

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
		
		//elimina paziente tramite id
		@Transactional
		@Modifying
		@Query("DELETE FROM Paziente p WHERE p.cf= :cf")
		int deleteByCf(@Param("cf")String cf);
	
	
}
