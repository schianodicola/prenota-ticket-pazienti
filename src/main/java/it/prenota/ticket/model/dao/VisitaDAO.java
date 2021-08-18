package it.prenota.ticket.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.prenota.ticket.model.entity.Visita;

@Repository
public interface VisitaDAO extends CrudRepository<Visita, Integer>{

	//CRUD
	
	//get lista visite
	@Query("SELECT v FROM Visita v")
	List<Visita> findAllAlsoDisable();
	
	//get lista visite abilitate
	@Query("SELECT v FROM Visita v WHERE v.stato IS TRUE")
	List<Visita> findAll();
	
	//get Visita tramite id
	@Query("SELECT v FROM Visita v WHERE v.id= :id AND v.stato IS TRUE")
	Visita findById(@Param("id")int id);
	
	//get Visita tramite nome
	@Query("SELECT v FROM Visita v WHERE v.nome= :nome AND v.stato IS TRUE")
	Visita findByNome(@Param("nome")String Nome);
	
	//elimina visita tramite id
	@Transactional
	@Modifying
	@Query("DELETE FROM Visita v WHERE v.id= :id")
	int deleteById(@Param("id")int id);
	
	//OPERAZIONI VARIE
	
	
	
	
}
