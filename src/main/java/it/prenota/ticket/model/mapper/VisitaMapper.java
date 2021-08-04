package it.prenota.ticket.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.prenota.ticket.model.dto.VisitaDTO;
import it.prenota.ticket.model.entity.Visita;

@Mapper
public interface VisitaMapper {
	VisitaMapper INSTANCE= Mappers.getMapper(VisitaMapper.class);
	
	VisitaDTO toDto(Visita v);
	
	Visita toEntity(VisitaDTO vDTO);
}
