package it.prenota.ticket.model.exception;

public class Eccezione extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public Eccezione() {
		super();
	}
	
	public Eccezione(String messaggio) {
		super(messaggio);
	}
	
}
