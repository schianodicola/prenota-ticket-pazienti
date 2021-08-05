package it.prenota.ticket.service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prenota.ticket.model.dao.PazienteDAO;
import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.entity.Paziente;
import it.prenota.ticket.model.mapper.PazienteMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Service
public class PazienteService{
	
	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
	
	@Autowired
	private PazienteDAO pDao;
	
	@Autowired
	private PazienteMapper pazienteMapper;
	
	
	public Set<PazienteDTO> getAll() {
		
		Set<Paziente> listaE=pDao.findAll();
		
		Set<PazienteDTO> listaDTO= new HashSet<>();
		
		if(!listaE.isEmpty()) {
			for(Paziente p: listaE) {
				listaDTO.add( pazienteMapper.toDto(p) );
			}
		}
		return listaDTO;
		
	}
	
	
	public PazienteDTO ricerca(String cf) {
		
		//Restituisce il paziente
		return pazienteMapper.toDto( pDao.findByCf(cf) );
		
	}
	
	
	public PazienteDTO findByCf(String cf) {
		
		return pazienteMapper.toDto( pDao.findByCf(cf) );
		
	}
	
	
	public PazienteDTO inserisci(PazienteDTO pDTO){
		
		LOGGER.info( "[Service_Inserisci]ID Paziente: "+ pazienteMapper.toEntity(pDTO).getId_paziente() );
		
		//trasforma dto in entity e salva
		return pazienteMapper.toDto( pDao.save(pazienteMapper.toEntity(pDTO)) );
		
	}
	
	
	public PazienteDTO aggiorna(PazienteDTO pDTO) {
		
		//Restituisce il paziente
		//Paziente p= pDao.findByCf(pDTO.getCf()); //hibernate non mappa/associa entita' che non siano richiamate tramite l'id (primarykey)
		Paziente p= pDao.findById(pDTO.getId_paziente());
	
		p= pazienteMapper.toEntity(pDTO);
		return pazienteMapper.toDto( pDao.save(p) );
				
	}
	
	
	public boolean elimina(String cf) {
		
		return pDao.deleteByCf(cf)>0;
		
	}
	
	
	
}
