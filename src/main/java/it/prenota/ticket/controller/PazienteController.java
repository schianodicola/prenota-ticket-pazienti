package it.prenota.ticket.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.response.PazienteResponse;
import it.prenota.ticket.model.util.EsitoUtility;
import it.prenota.ticket.model.util.StringUtility;
import it.prenota.ticket.service.PazienteService;
import javassist.bytecode.stackmap.TypeData.ClassName;


@RestController
@RequestMapping(value= "/api/paziente")
public class PazienteController {

	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
	
	@Autowired
	private PazienteService sPaziente;
	
	
	/*
	 * Restituisco tutti i Pazienti presenti nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getPazienti() {
		
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Pesco i Pazienti dal DB
		Set<PazienteDTO> lpazienti= new HashSet<>();
		lpazienti= sPaziente.getAll();
		
		if(lpazienti.isEmpty()) { 
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			List<PazienteDTO> lista= List.copyOf(lpazienti);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
	
	}
	
	
	/*
	 * Ricerco un Paziente all'interno del DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/ricerca/{cf}", method = RequestMethod.GET)
	public ResponseEntity<?> ricercare(@PathVariable String cf) {
		
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(cf) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Pesco il Paziente dal DB
		PazienteDTO paziente= sPaziente.findByCf(cf);
		
		if(paziente == null) { 
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "Codice fiscale inesistente"));
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(paziente);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
	
	}
	
	
	/*
	 * Inserisco un Paziente all'interno del DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<?> inserire( @RequestBody final PazienteDTO paziente ) {
		
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(paziente==null || StringUtility.isEmpty(paziente.getCf()) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se il Paziente e' già presente nel DB
		if(sPaziente.findByCf( paziente.getCf() ) != null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("OK", "Paziente gia' esistente"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		//Inserisco il Paziente
		try {
			sPaziente.inserisci(paziente);
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(paziente);
			pr.setPazientiDTO(lista);
			
		}catch(Exception e) {
			LOGGER.info( "[Error in method 'Inserire'] Impossibile inserire il paziente "+ e.getMessage() ); 
			
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(pr, HttpStatus.OK);
		
	}
	
	
	/*
	 * Aggiorno un Paziente presente nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/aggiorna", method = RequestMethod.PUT)
	public ResponseEntity<?> aggiornare( @RequestBody final PazienteDTO paziente ) {
		
		PazienteResponse pr= new PazienteResponse();
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(paziente==null || StringUtility.isEmpty(paziente.getCf()) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr , HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se il Paziente è presente nel DB
		PazienteDTO p=sPaziente.findByCf(paziente.getCf());
		if(p == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "Il Paziente che si vuole modificare non è presente nel DB"));
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
		//Aggiorno il Paziente
		paziente.setId_paziente(p.getId_paziente());
		if(sPaziente.aggiorna(paziente) == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else {
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(paziente);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
	
	/*
	 * Elimino un Paziente presente nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 * - 400: se i campi obbligatori non sono valorizzati
	 */
	@RequestMapping(path = "/elimina/{cf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminare( @PathVariable String cf ) {
			
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Controllo che i campi non siano Nulli
		if(StringUtility.isEmpty(cf) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKo());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		
		//Controllo se il Paziente è presente nel DB
		PazienteDTO p= sPaziente.findByCf(cf); 
		if(p == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "Il Paziente che si vuole eliminare non è presente del DB"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		//Elimino il Paziente
		if(sPaziente.elimina(cf) == true) {
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(p);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr , HttpStatus.OK);
		}
				
	}
	
	
	
}
