package it.prenota.ticket.model.mapper.impl;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.entity.Paziente;
import it.prenota.ticket.model.mapper.PazienteMapper;
import javassist.bytecode.stackmap.TypeData.ClassName;

@Component
public class PazienteMapperImpl implements PazienteMapper{

	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
	
	@Override
	public Paziente toEntity(PazienteDTO pDTO) {
		// TODO Auto-generated method stub
		if(pDTO== null) return null;
		//System.out.println("[MapperImpl_toEntity]ID Paziente:" + pDTO.getId_paziente());
		LOGGER.info( "[MapperImpl_toEntity]ID Paziente:" + pDTO.getId_paziente() );
		
		Paziente p= new Paziente();
		p.setId_paziente(pDTO.getId_paziente());
		p.setCf(pDTO.getCf());
		p.setCognome(pDTO.getCognome());
		p.setNome(pDTO.getNome());
		p.setData_nascita(pDTO.getData_nascita());
		p.setIndirizzo(pDTO.getIndirizzo());
		
		return p;
	}

	@Override
	public PazienteDTO toDto(Paziente p) {

		if(p== null) return null;
		
		PazienteDTO pDTO= new PazienteDTO();
		//System.out.println("[Mapper_toDTO]ID Paziente:" + p.getId_paziente());
		pDTO.setId_paziente(p.getId_paziente());
		pDTO.setCf(p.getCf());
		pDTO.setCognome(p.getCognome());
		pDTO.setNome(p.getNome());
		pDTO.setData_nascita(p.getData_nascita());
		pDTO.setIndirizzo(p.getIndirizzo());
		
		return pDTO;
	}

}
