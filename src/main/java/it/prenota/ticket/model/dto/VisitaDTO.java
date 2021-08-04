package it.prenota.ticket.model.dto;

import java.time.LocalDateTime;

public class VisitaDTO {

		private int id;
		private String nome;
		private String tipologia;
		private String descrizione;
		private String esami_necessari;
		private String note;
		private boolean stato;
		private LocalDateTime data_cancellazione;
		
		
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
		
		
}
