package it.prenota.ticket.service;

import java.time.LocalDateTime;
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
		pDTO.setStato(true);
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
	public PrenotazioneDTO elimina(PrenotazioneDTO pDTO) {
		
		pDTO.setStato(false);
		pDTO.setData_cancellazione(LocalDateTime.now());
		
		return aggiorna(pDTO);
		
	}
	
	
	public int contaPrenotazioniOld() {
		
		String nome= "Risonanza Magnetica";
		return pDao.countNumPrenotazioniByVisitaAndByTempo(nome).getPrenotazioni().size();
	}
	
	
	//Coonta il numero di prenotazioni di una visita in un range di tempo
	public int contaPrenotazioni(String nome, LocalDateTime t1, LocalDateTime t2) {
		
		return pDao.countNumPrenotazioniByVisitaAndByTempo(nome, t1, t2);
	}
	
	
	//Lista prenotazioni di un paziente
	public List<PrenotazioneDTO> findPrenByPaziente(String cf){
		
		List<Prenotazione> listaPrenotazioni= pDao.findPrenByPaziente(cf);
		List<PrenotazioneDTO> listaDTO= new ArrayList<>();
		
		if(!listaPrenotazioni.isEmpty()) {
			for(Prenotazione p: listaPrenotazioni) {
				listaDTO.add( prenotazioneMapper.toDto(p) );
			}
		}
		
		return listaDTO;
		
	}
	
	
	
	
}


