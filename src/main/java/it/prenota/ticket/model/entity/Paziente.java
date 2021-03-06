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
@Table(name="paziente")
public class Paziente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	//@JsonIgnore
	@Column(name="id_paziente")
	private int id_paziente;
	
	/*
	@Id
	@SequenceGenerator(name = "paziente_id_gen", sequenceName = "paziente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paziente_id_gen")
	@Column(name = "id_paziente")
	private int id_paziente;
	*/
	@Column(name="nome", length= 30)
	private String nome;
	
	@Column(name="cognome", length= 30)
	private String cognome;
	
	@Column(name="data_nascita")
	private LocalDateTime data_nascita;
	
	@Column(name="indirizzo", length= 30)
	private String indirizzo;
	
	@Column(name="cf", length= 16, nullable=false)
	private String cf;

	@JsonIgnore
	@OneToMany(mappedBy = "paziente")
	private List<Prenotazione> prenotazioni;
	
	
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

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	
	
}
