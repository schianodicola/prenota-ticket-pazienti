package it.prenota.ticket.model.response;

import java.util.List;

import it.prenota.ticket.model.dto.EsitoDTO;
import it.prenota.ticket.model.dto.PazienteDTO;

public class PazienteResponse {
	private EsitoDTO esitoDTO;
	private List<PazienteDTO> pazientiDTO;
	
	public PazienteResponse() {
		super();
	}

	public PazienteResponse(EsitoDTO esitoDTO, List<PazienteDTO> pazientiDTO) {
		super();
		this.esitoDTO = esitoDTO;
		this.pazientiDTO = pazientiDTO;
	}

	public EsitoDTO getEsitoDTO() {
		return esitoDTO;
	}

	public void setEsitoDTO(EsitoDTO esitoDTO) {
		this.esitoDTO = esitoDTO;
	}

	public List<PazienteDTO> getPazientiDTO() {
		return pazientiDTO;
	}

	public void setPazientiDTO(List<PazienteDTO> pazientiDTO) {
		this.pazientiDTO = pazientiDTO;
	}
	
	
	
}
