package it.prenota.ticket.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import it.prenota.ticket.model.dto.PrenotazioneDTO;
import it.prenota.ticket.model.entity.Prenotazione;

@Mapper
public interface PrenotazioneMapper {
	PrenotazioneMapper INSTANCE= Mappers.getMapper(PrenotazioneMapper.class);
	
	PrenotazioneDTO toDto(Prenotazione p);
	
	Prenotazione toEntity(PrenotazioneDTO pDTO);
}
