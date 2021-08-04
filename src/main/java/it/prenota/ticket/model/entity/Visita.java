package it.prenota.ticket.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="visita")
public class Visita {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="tipologia")
	private String tipologia;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="esami_necessari")
	private String esami_necessari;
	
	@Column(name="note")
	private String note;
	
	@Column(name="stato")
	private boolean stato;
	
	@Column(name="data_cancellazione")
	private LocalDateTime data_cancellazione;

	@JsonIgnore
	@OneToMany(mappedBy = "visita") //nome attributo nella classe Prenotazione
	private List<Prenotazione> prenotazioni;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getEsami_necessari() {
		return esami_necessari;
	}

	public void setEsami_necessari(String esami_necessari) {
		this.esami_necessari = esami_necessari;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	
	
}
