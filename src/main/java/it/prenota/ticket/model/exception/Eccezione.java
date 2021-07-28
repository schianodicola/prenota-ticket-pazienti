package it.prenota.ticket.model.exception;

public class Eccezione extends RuntimeException{

	public Eccezione() {
		super();
	}
	
	public Eccezione(String messaggio) {
		super(messaggio);
	}
	
}
