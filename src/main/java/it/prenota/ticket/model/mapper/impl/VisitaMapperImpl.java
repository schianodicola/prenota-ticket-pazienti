package it.prenota.ticket.model.mapper.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import it.prenota.ticket.model.dto.VisitaDTO;
import it.prenota.ticket.model.entity.Visita;
import it.prenota.ticket.model.mapper.VisitaMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
public class VisitaMapperImpl implements VisitaMapper {
	
	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );

	@Override
	public VisitaDTO toDto(Visita v) {

		if(v== null) return null;
		
		LOGGER.info( "[MapperImpl_toDto] Sono in VisitaMapper" );
		
		VisitaDTO vDTO= new VisitaDTO();
		vDTO.setId(v.getId());
		vDTO.setNome(v.getNome());
		vDTO.setTipologia(v.getTipologia());
		vDTO.setDescrizione(v.getDescrizione());
		vDTO.setEsami_necessari(v.getEsami_necessari());
		vDTO.setNote(v.getNote());
		vDTO.setStato(v.isStato());
		vDTO.setData_cancellazione(v.getData_cancellazione());
		
		return vDTO;
	}

	@Override
	public Visita toEntity(VisitaDTO vDTO) {

		if(vDTO== null) return null;
		
		LOGGER.info( "[MapperImpl_toEntity] Sono in VisitaMapper" );
		
		Visita v= new Visita();
		v.setId(vDTO.getId());
		v.setNome(vDTO.getNome());
		v.setTipologia(vDTO.getTipologia());
		v.setDescrizione(vDTO.getDescrizione());
		v.setEsami_necessari(vDTO.getEsami_necessari());
		v.setNote(vDTO.getNote());
		v.setStato(vDTO.isStato());
		v.setData_cancellazione(vDTO.getData_cancellazione());
		
		return v;		
	}
	
	
}
