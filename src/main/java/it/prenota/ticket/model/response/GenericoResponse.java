package it.prenota.ticket.model.response;

import it.prenota.ticket.model.dto.EsitoDTO;

public class GenericoResponse<S,I> {
	private EsitoDTO esitoDTO;
	private S attributo1;
	private I attributo2;
	
	
	public GenericoResponse() {
		super();
	}

	public GenericoResponse(EsitoDTO esitoDTO, S attributo1, I attributo2) {
		super();
		this.esitoDTO = esitoDTO;
		this.attributo1 = attributo1;
		this.attributo2 = attributo2;
	}

	public EsitoDTO getEsitoDTO() {
		return esitoDTO;
	}

	public void setEsitoDTO(EsitoDTO esitoDTO) {
		this.esitoDTO = esitoDTO;
	}

	public S getAttributo1() {
		return attributo1;
	}

	public void setAttributo1(S attributo1) {
		this.attributo1 = attributo1;
	}

	public I getAttributo2() {
		return attributo2;
	}

	public void setAttributo2(I attributo2) {
		this.attributo2 = attributo2;
	}

	
	
	
}
