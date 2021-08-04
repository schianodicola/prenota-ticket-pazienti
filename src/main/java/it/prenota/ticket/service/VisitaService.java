package it.prenota.ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prenota.ticket.model.dao.VisitaDAO;
import it.prenota.ticket.model.dto.VisitaDTO;
import it.prenota.ticket.model.entity.Visita;
import it.prenota.ticket.model.mapper.VisitaMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;

@Service
public class VisitaService {

	private static final Logger LOGGER = LogManager.getLogger(ClassName.class.getName());
	
	@Autowired
	private VisitaDAO vDao;
	
	@Autowired
	private VisitaMapper visitaMapper;
	
	
	public  List<VisitaDTO> getAll(){
		
		List<Visita> listaVisite= vDao.findAll();
		
		List<VisitaDTO> listaDTO= new ArrayList<VisitaDTO>();
		
		if(!listaVisite.isEmpty()) {
			for(Visita v: listaVisite) {
				listaDTO.add( visitaMapper.toDto(v) );
			}
		}
		return listaDTO;
		
	}
	
	
	public VisitaDTO ricerca(int id) {
		
		//restituisce la visita
		return visitaMapper.toDto( vDao.findById(id) );
		
	}
	
	
	public VisitaDTO inserisci(VisitaDTO vDTO) {
		
		//trasforma dto in entity e salva
		return visitaMapper.toDto( vDao.save( visitaMapper.toEntity(vDTO)) );
		
	}
	
	
	public VisitaDTO aggiorna(VisitaDTO vDTO) {
		
		//Restituisce la Visita e la mappa
		Visita v= vDao.findById(vDTO.getId());
		
		v= visitaMapper.toEntity(vDTO);
		return visitaMapper.toDto( vDao.save(v) );
		
	}
	
	
	public boolean elimina(int id) {
		
		return vDao.deleteById(id)>0;
		
	}
	
	
	
}
