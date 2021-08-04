package it.prenota.ticket.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.dto.VisitaDTO;
import it.prenota.ticket.model.response.PazienteResponse;
import it.prenota.ticket.model.response.VisitaResponse;
import it.prenota.ticket.model.util.EsitoUtility;
import it.prenota.ticket.model.util.StringUtility;
import it.prenota.ticket.service.PazienteService;
import it.prenota.ticket.service.VisitaService;
import javassist.bytecode.stackmap.TypeData.ClassName;

@RestController
@RequestMapping(value= "/api/visita")
public class VisitaController {
	
	private static final Logger LOGGER = LogManager.getLogger(ClassName.class.getName());
	
	@Autowired
	private VisitaService visitaService;
	
	
	/*
	 * Restituisco tutte le Visite presenti nel DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getVisite() {
		
		VisitaResponse pr= new VisitaResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Pesco i Pazienti dal DB
		List<VisitaDTO> lvisite= new ArrayList<>();
		lvisite= visitaService.getAll();
		
		if(lvisite.isEmpty()) { 
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			pr.setVisitaDTO(lvisite);
			return new ResponseEntity<>(lvisite, HttpStatus.OK);
		}
		
	}
	
	
	/*
	 * Ricerco una Visita all'interno del DataBase.
	 * Status code restituiti:
	 * - 200: se la chiamata è andata a buon fine
	 */
	@RequestMapping(path = "/ricerca/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> ricercare(@PathVariable int id) {
		
		VisitaResponse pr= new VisitaResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//Pesco il Paziente dal DB
		VisitaDTO visita= visitaService.ricerca(id);
		
		if(visita == null) { 
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "Visita non trovata"));
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			List<VisitaDTO> lista= new ArrayList<>();
			lista.add(visita);
			pr.setVisitaDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(path = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<?> inserire( @RequestBody final VisitaDTO visita ) {
		
		VisitaResponse pr= new VisitaResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		
		//Controllo se la Visita e' già presente nel DB
		if(visitaService.ricerca(visita.getId()) != null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("OK", "Visita gia' esistente"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		//Inserisco la Visita
		try {
			visitaService.inserisci(visita);
			List<VisitaDTO> lista= new ArrayList<>();
			lista.add(visita);
			pr.setVisitaDTO(lista);
			
		}catch(Exception e) {
			LOGGER.error("[Error in method 'Inserire'] Impossibile inserire il paziente ", e);
			
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(pr, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(path = "/aggiorna", method = RequestMethod.PUT)
	public ResponseEntity<?> aggiornare( @RequestBody final VisitaDTO visita ) {
		
		VisitaResponse pr= new VisitaResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		if(visitaService.aggiorna(visita) == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		else {
			List<VisitaDTO> lista= new ArrayList<>();
			lista.add(visita);
			pr.setVisitaDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
	
	@RequestMapping(path = "/elimina/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminare( @PathVariable int id ) {
		
		VisitaResponse pr= new VisitaResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		
		//Controllo se il Paziente è presente nel DB
		VisitaDTO v= visitaService.ricerca(id); //TODO: se più persone vogliono eliminare la stessa visita?
		if(v == null) {
			pr.setEsitoDTO(EsitoUtility.setEsitoGenerico("KO", "La Visita che si vuole eliminare non è presente del DB"));
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		
		//Elimino la Visita
		if(visitaService.elimina(id) == true) {
			List<VisitaDTO> lista= new ArrayList<>();
			lista.add(v);
			pr.setVisitaDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			pr.setEsitoDTO(EsitoUtility.setEsitoKoServer());
			return new ResponseEntity<>(pr , HttpStatus.OK);
		}
		
	}
	
	
	
}
