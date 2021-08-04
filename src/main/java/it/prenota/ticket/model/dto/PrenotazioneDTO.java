package it.prenota.ticket.model.dto;

import java.time.LocalDateTime;

import it.prenota.ticket.model.entity.Paziente;
import it.prenota.ticket.model.entity.Visita;

public class PrenotazioneDTO {

	private int id;
	private String codice;
	private LocalDateTime data_appuntamento;
	private boolean stato;
	private LocalDateTime data_cancellazione;
	
	private Paziente paziente;
	private Visita visita;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public LocalDateTime getData_appuntamento() {
		return data_appuntamento;
	}
	public void setData_appuntamento(LocalDateTime data_appuntamento) {
		this.data_appuntamento = data_appuntamento;
	}
	public boolean isStato() {
		return stato;
	}
	public void setStato(boolean stato) {
		this.stato = stato;
	}
	public LocalDateTime getData_cancellazione() {
		return data_cancellazione;
	}
	public void setData_cancellazione(LocalDateTime data_cancellazione) {
		this.data_cancellazione = data_cancellazione;
	}
	public Paziente getPaziente() {
		return paziente;
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	
	
	
	
}
