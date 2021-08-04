package it.prenota.ticket.model.mapper.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import it.prenota.ticket.model.dto.PrenotazioneDTO;
import it.prenota.ticket.model.entity.Prenotazione;
import it.prenota.ticket.model.mapper.PrenotazioneMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
public class PrenotazioneMapperImpl implements PrenotazioneMapper {

	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
	
	@Override
	public PrenotazioneDTO toDto(Prenotazione p) {
		
		if(p== null) return null;
		
		LOGGER.info( "[MapperImpl_toDto] Sono in PrenotazioneMapper" );
		
		PrenotazioneDTO pDTO= new PrenotazioneDTO();
		pDTO.setId(p.getId());
		pDTO.setCodice(p.getCodice());
		pDTO.setData_appuntamento(p.getData_appuntamento());
		pDTO.setStato(p.isStato());
		pDTO.setData_cancellazione(p.getData_cancellazione());
		
		pDTO.setPaziente(p.getPaziente());
		pDTO.setVisita(p.getVisita());
		
		return pDTO;
	}

	@Override
	public Prenotazione toEntity(PrenotazioneDTO pDTO) {

		if(pDTO== null) return null;
		
		LOGGER.info( "[MapperImpl_toDto] Sono in PrenotazioneMapper" );
		
		Prenotazione p= new Prenotazione();
		p.setId(pDTO.getId());
		p.setCodice(pDTO.getCodice());
		p.setData_appuntamento(pDTO.getData_appuntamento());
		p.setStato(pDTO.isStato());
		p.setData_cancellazione(pDTO.getData_cancellazione());
		
		p.setPaziente(pDTO.getPaziente());
		p.setVisita(pDTO.getVisita());
		
		return p;
	}

	
	
}
