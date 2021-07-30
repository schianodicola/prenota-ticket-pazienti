package it.prenota.ticket.model.util;

import it.prenota.ticket.model.dto.EsitoDTO;

public class EsitoUtility {

	public static EsitoDTO setEsitoOk() {
		return new EsitoDTO("OK", "Chiamata eseguita correttamente");
	}
	
	public static EsitoDTO setEsitoOkButError() {
		return new EsitoDTO("KO", "Sistema non disponibile, riprovare più tardi ");
	}
	
	public static EsitoDTO setEsitoBad() {
		return new EsitoDTO("KO", "Campi obbligatori non valorizzati");
	}
	
	//aggiungere metodo generale
	
}
