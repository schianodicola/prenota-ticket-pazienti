package it.prenota.ticket.model.dto;

public class EsitoDTO {
	
	String codice;
	String descrizioneEsito;
	
	public EsitoDTO() {
		super();
		
	}
	public EsitoDTO(String codice, String descrizioneEsito) {
		super();
		this.codice = codice;
		this.descrizioneEsito = descrizioneEsito;
	}
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getDescrizioneEsito() {
		return descrizioneEsito;
	}
	public void setDescrizioneEsito(String descrizioneEsito) {
		this.descrizioneEsito = descrizioneEsito;
	}
	
	
	
}
