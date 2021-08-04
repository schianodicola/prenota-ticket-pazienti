package it.prenota.ticket.model.response;

import java.util.List;

import it.prenota.ticket.model.dto.EsitoDTO;
import it.prenota.ticket.model.dto.PrenotazioneDTO;

public class PrenotazioneResponse {
	private EsitoDTO esitoDTO;
	private List<PrenotazioneDTO> prenotazioniDTO;
	
	
	public PrenotazioneResponse() {
		super();
	}

	public PrenotazioneResponse(EsitoDTO esitoDTO, List<PrenotazioneDTO> prenotazioniDTO) {
		super();
		this.esitoDTO = esitoDTO;
		this.prenotazioniDTO = prenotazioniDTO;
	}

	public EsitoDTO getEsitoDTO() {
		return esitoDTO;
	}

	public void setEsitoDTO(EsitoDTO esitoDTO) {
		this.esitoDTO = esitoDTO;
	}

	public List<PrenotazioneDTO> getPrenotazioniDTO() {
		return prenotazioniDTO;
	}

	public void setPrenotazioniDTO(List<PrenotazioneDTO> prenotazioniDTO) {
		this.prenotazioniDTO = prenotazioniDTO;
	}
	
	
	
}
