package it.prenota.ticket.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prenota.ticket.model.dao.PrenotazioneDAO;
import it.prenota.ticket.model.dto.PrenotazioneDTO;
import it.prenota.ticket.model.entity.Prenotazione;
import it.prenota.ticket.model.mapper.PrenotazioneMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Service
public class PrenotazioneService {

private static final Logger LOGGER = LogManager.getLogger(ClassName.class.getName());
	
	@Autowired
	private PrenotazioneDAO pDao;
	
	@Autowired
	private PrenotazioneMapper prenotazioneMapper;
	
	
	//Restituisce tutte le prenotazioni
	public  List<PrenotazioneDTO> getAll(){
		
		List<Prenotazione> listaPrenotazioni= pDao.findAll();
		
		List<PrenotazioneDTO> listaDTO= new ArrayList<PrenotazioneDTO>();
		
		if(!listaPrenotazioni.isEmpty()) {
			for(Prenotazione p: listaPrenotazioni) {
				listaDTO.add( prenotazioneMapper.toDto(p) );
			}
		}
		return listaDTO;
		
	}
	
	//Ricerca Prenotazione
	public PrenotazioneDTO ricerca(String codice) {
		
		//restituisce la prenotazione
		return prenotazioneMapper.toDto( pDao.findByCodice(codice) );
		
	}
	
	//Inserisce Prenotazione
	public PrenotazioneDTO inserisci(PrenotazioneDTO pDTO) {
		
		LOGGER.info( "[Service_Inserisci]ID Prenotazione: " + pDTO.getId());
		
		//trasforma dto in entity e salva
		//pDTO.setId(0); // lo salvo qui o nel controller?
		return prenotazioneMapper.toDto( pDao.save( prenotazioneMapper.toEntity(pDTO) ) );
		
	}
	
	//Aggiorna Prenotazione
	public PrenotazioneDTO aggiorna(PrenotazioneDTO pDTO) {
		
		//restituisce la prenotazione
		Prenotazione p= pDao.findById(pDTO.getId());
		
		p= prenotazioneMapper.toEntity(pDTO);
		return prenotazioneMapper.toDto( pDao.save(p) );
		
	}
	
	//Elimina Prenotazione
	public boolean elimina(String codice) {
		
		return pDao.deleteByCodice(codice)>0;
		
	}
	
	
}
