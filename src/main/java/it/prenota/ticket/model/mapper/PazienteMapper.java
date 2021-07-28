package it.prenota.ticket.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.entity.Paziente;

@Mapper
public interface PazienteMapper {
	PazienteMapper INSTANCE= Mappers.getMapper(PazienteMapper.class);
	
	PazienteDTO toDto(Paziente p);
	
	Paziente toEntity(PazienteDTO pDTO);
}
