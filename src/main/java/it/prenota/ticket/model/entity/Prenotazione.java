package it.prenota.ticket.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="prenotazione")
public class Prenotazione {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="codice", nullable=false)
	private String codice;
	
	@Column(name="data_appuntamento")
	private LocalDateTime data_appuntamento;
	
	@Column(name="stato")
	private boolean stato;
	
	@Column(name="data_cancellazione")
	private LocalDateTime data_cancellazione;
	
	@ManyToOne
	@JoinColumn(name="id_paziente", referencedColumnName = "id_paziente")
	private Paziente paziente;
	
	@ManyToOne
	@JoinColumn(name="id_visita", referencedColumnName = "id")
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
