package it.prenota.ticket.model.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class PazienteDTO {
	
	private int id_paziente;
	private String nome;
	private String cognome;
	private LocalDateTime data_nascita;
	private String indirizzo;
	private String cf;
	
	public int getId_paziente() {
		return id_paziente;
	}
	public void setId_paziente(int id_paziente) {
		this.id_paziente = id_paziente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDateTime getData_nascita() {
		return data_nascita;
	}
	public void setData_nascita(LocalDateTime data_nascita) {
		this.data_nascita = data_nascita;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	@Override
	public String toString() {
		return "PazienteDTO [id_paziente=" + id_paziente + ", nome=" + nome + ", cognome=" + cognome + ", data_nascita="
				+ data_nascita + ", indirizzo=" + indirizzo + ", cf=" + cf + "]";
	}
	
	
	
}
