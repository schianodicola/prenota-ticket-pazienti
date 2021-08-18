package it.prenota.ticket.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.prenota.ticket.model.dto.PrenotazioneDTO;
import it.prenota.ticket.model.response.GenericoResponse;
import it.prenota.ticket.model.response.PrenotazioneResponse;
import it.prenota.ticket.model.util.EsitoUtility;
import it.prenota.ticket.model.util.StringUtility;
import it.prenota.ticket.service.PrenotazioneService;
import javassist.bytecode.stackmap.TypeData.ClassName;

@RestController
@RequestMapping(value= "/api/prenotazione")
public class PrenotazioneController {
	
	private static final Logger LOGGER = LogManager.getLogger(ClassName.class.getName());
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	
	/*
	 * Restituisco tutte le Prenotazioni presenti nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getPrenotazioni() {
		
		PrenotazioneResponse pr= new PrenotazioneResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Pesco le Prenotazioni dal DB
		List<PrenotazioneDTO> lprenotazioni= new ArrayList<>();
		lprenotazioni= prenotazioneService.getAll();
		
		if(lprenotazioni.isEmpty()) {
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}else {
			pr.setPrenotazioniDTO(lprenotazioni);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
	
	/*
	 * Ricerco una Prenotazione all'interno del DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/ricerca/{codice}", method = RequestMethod.GET)
	public ResponseEntity<?> ricercare(@PathVariable String codice) {
		
		PrenotazioneResponse pr= new PrenotazioneResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(codice) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Pesco la Prenotazione dal DB
		PrenotazioneDTO prenotazioneOut= prenotazioneService.ricerca(codice);
		
		if(prenotazioneOut == null) { 
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "Codice prenotazione inesistente"));
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			List<PrenotazioneDTO> lista= new ArrayList<>();
			lista.add(prenotazioneOut);
			pr.setPrenotazioniDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
	
	}
	
	
	/*
	 * Inserisco una Prenotazione all'interno del DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<?> inserire( @RequestBody final PrenotazioneDTO prenotazione ) {
		
		PrenotazioneResponse pr= new PrenotazioneResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(prenotazione.getCodice()) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se la Prenotazione e' già presente nel DB
		if(prenotazioneService.ricerca(prenotazione.getCodice()) != null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("OK", "Prenotazione gia' esistente"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		//Inserisco la Prenotazione
		try {
			PrenotazioneDTO prenotazioneOut= prenotazioneService.inserisci(prenotazione);
			List<PrenotazioneDTO> lista= new ArrayList<>();
			lista.add(prenotazioneOut);
			pr.setPrenotazioniDTO(lista);
			
		}catch(Exception e) {
			LOGGER.error( "[Error in method 'Inserire'] Impossibile inserire la prenotazione "+ e ); 
			
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(pr, HttpStatus.OK);
		
	}
	
	
	/*
	 * Aggiorno una Prenotazione presente nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/aggiorna", method = RequestMethod.PUT)
	public ResponseEntity<?> aggiornare( @RequestBody final PrenotazioneDTO prenotazione ) {
		
		PrenotazioneResponse pr= new PrenotazioneResponse();
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(prenotazione.getCodice() ) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr , HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se la Prenotazione è presente nel DB
		PrenotazioneDTO p= prenotazioneService.ricerca(prenotazione.getCodice());
		if(p == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "La Prenotazione che si vuole modificare non è presente nel DB"));
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
		//Aggiorno la Prenotazione
		PrenotazioneDTO prenotazioneOut= prenotazioneService.aggiorna(prenotazione);
		if(prenotazioneOut == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else {
			List<PrenotazioneDTO> lista= new ArrayList<>();
			lista.add(prenotazioneOut);
			pr.setPrenotazioniDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
	
	/*
	 * Elimino una Prenotazione presente nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/elimina/{codice}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminare( @PathVariable String codice ) {
		PrenotazioneResponse pr= new PrenotazioneResponse();
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(codice) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se la Prenotazione è presente nel DB
		PrenotazioneDTO p= prenotazioneService.ricerca(codice);
		if(p == null) {
			pr.setEsitoDTO( EsitoUtility.setEsitoGenerico("KO", "Il Paziente che si vuole eliminare non è presente del DB") );
			return new ResponseEntity<>(pr, HttpStatus.OK);
		} 
		
		//Elimino la Prenotazione
		PrenotazioneDTO prenotazioneOut= prenotazioneService.elimina(p);
		if(prenotazioneOut != null) {
			List<PrenotazioneDTO> lista= new ArrayList<>();
			lista.add(prenotazioneOut);
			pr.setPrenotazioniDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr , HttpStatus.OK);
		}
		
	}
	
	
	/*
	 * Conto le Prenotazioni di una Visita in una determinata fascia Oraria
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/conta-prenotazioni", method = RequestMethod.GET)
	public ResponseEntity<?> contaPrenotazioniRange(@RequestParam String nome, 
													@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime tempoInizio,
													@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime tempoFine 
													){

		GenericoResponse<String, Integer> pr= new GenericoResponse<>();
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//String nome = null;
		//LocalDateTime tempoInizio = null;
		//LocalDateTime tempoFine = null;
		int numeroPrenotazioni;
		
		/*
		//Controllo che i campi non siano Nulli
		if(nome== null || tempoInizio== null || tempoFine== null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		*/
		
		//Calcolo il Numero di Prenotazioni di una visita
		try {
			numeroPrenotazioni= prenotazioneService.contaPrenotazioni(nome,tempoInizio,tempoFine);
		}catch(Exception e) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
			
		pr.setAttributo1("Numero Prenotazioni: ");
		pr.setAttributo2(numeroPrenotazioni);
		return new ResponseEntity<>(pr, HttpStatus.OK);
		
		//"2021-07-21T13:23:11.366";	
		//2021-08-05T13:23:11.366
	}
	
	
	/*
	 * Restituisco tutte le Prenotazioni di un Paziente
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/ricercaPrenPaziente/{cf}", method = RequestMethod.GET)
	public ResponseEntity<?> ricercaPrenotazioniPaziente(@PathVariable String cf) {
		
		PrenotazioneResponse pr= new PrenotazioneResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
			
		//Pesco le Prenotazioni dal DB
		List<PrenotazioneDTO> lprenotazioni= prenotazioneService.findPrenByPaziente(cf);
		
		if(lprenotazioni.isEmpty()) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("OK", "Il Paziente non ha Prenotazioni"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}else {
			pr.setPrenotazioniDTO(lprenotazioni);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
}




