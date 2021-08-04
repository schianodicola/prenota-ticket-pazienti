package it.prenota.ticket.model.response;

import java.util.List;

import it.prenota.ticket.model.dto.EsitoDTO;
import it.prenota.ticket.model.dto.VisitaDTO;

public class VisitaResponse {
	private EsitoDTO esitoDTO;
	private List<VisitaDTO> visitaDTO;
	
	
	public VisitaResponse() {
		super();
	}
	
	public VisitaResponse(EsitoDTO esitoDTO, List<VisitaDTO> visitaDTO) {
		super();
		this.esitoDTO = esitoDTO;
		this.visitaDTO = visitaDTO;
	}

	public EsitoDTO getEsitoDTO() {
		return esitoDTO;
	}

	public void setEsitoDTO(EsitoDTO esitoDTO) {
		this.esitoDTO = esitoDTO;
	}

	public List<VisitaDTO> getVisitaDTO() {
		return visitaDTO;
	}

	public void setVisitaDTO(List<VisitaDTO> visitaDTO) {
		this.visitaDTO = visitaDTO;
	}
	
	
}
