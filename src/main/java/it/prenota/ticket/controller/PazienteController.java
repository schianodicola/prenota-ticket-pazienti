package it.prenota.ticket.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.entity.Paziente;
import it.prenota.ticket.model.mapper.PazienteMapper;
import it.prenota.ticket.service.PazienteService;
import javassist.bytecode.stackmap.TypeData.ClassName;


@RestController
@RequestMapping(value= "/api/paziente")
public class PazienteController {

	private static final Logger LOGGER = Logger.getLogger( ClassName.class.getName() );
	
	@Autowired
	private PazienteService sPaziente;
	
	
	@RequestMapping(path = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getPazienti() {
		Set<PazienteDTO> lpazienti= new HashSet<>();
		
		lpazienti= sPaziente.getAll();
		if(lpazienti.isEmpty()) return new ResponseEntity<>("[getPazienti] Non ho trovato pazienti", HttpStatus.OK);
		else return new ResponseEntity<>(lpazienti, HttpStatus.OK);
	
	}
	
	@RequestMapping(path = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<?> inserire( @RequestBody final PazienteDTO paziente ) {
		
		if(paziente==null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		LOGGER.info( "[RequestInserire_ID: ]"+ paziente.getId_paziente() );
		
		//Controllo se il CF e' già presente, per vedere se salvarlo o meno
		if(sPaziente.findByCf( paziente.getCf() ) != null) return new ResponseEntity<>("Paziente gia' esistente", HttpStatus.OK);
		/*
			//salvo il paziente
			if(sPaziente.inserisci(paziente) != null) return new ResponseEntity<>("Paziente Salvato!", HttpStatus.NO_CONTENT);
			else return new ResponseEntity<>("Paziente non inserito", HttpStatus.OK); 
		*/
		//non mi piace
		try {
			sPaziente.inserisci(paziente);
		}catch(IllegalArgumentException e) {
			System.err.println("[Errore] Impossibile inserire il paziente (paziente null)");
			return new ResponseEntity<>("Paziente non inserito", HttpStatus.OK); 
		}
		//finally {
		return new ResponseEntity<>("Paziente Salvato!", HttpStatus.NO_CONTENT);
		//}
		
	}
	
	
	@RequestMapping(path = "/aggiorna", method = RequestMethod.PUT)
	public ResponseEntity<?> aggiornare( @RequestBody final PazienteDTO paziente ) {
		
		if(paziente==null || paziente.getCf()==null) return new ResponseEntity<>("[ERRORE] Hai lascito Uno o più campi vuoti. MORE INFO: " + paziente.toString(), HttpStatus.BAD_REQUEST);
		PazienteDTO p=sPaziente.findByCf(paziente.getCf());
		
		if(p == null) return new ResponseEntity<>("Il Paziente che vuoi modificare non presente nel db", HttpStatus.OK);
		
		//aggiorno Paziente
		paziente.setId_paziente(p.getId_paziente());
		if(sPaziente.aggiorna(paziente) == null) return new ResponseEntity<>("errore modifica", HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>("Dati Paziente modificati", HttpStatus.OK);
		
	}
	
	//cancella l'articolo tramite un id
	@RequestMapping(path = "/elimina/{cf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminare( @PathVariable String cf ) {
				
		//controllo se il paziente è presente
		if(sPaziente.findByCf(cf) == null) return new ResponseEntity<>("paziente non presente", HttpStatus.OK);
					
		//TODO esito			
		if(sPaziente.elimina(cf) == true) return new ResponseEntity<>("paziente eliminato", HttpStatus.NO_CONTENT);
		else return new ResponseEntity<>("errore query " , HttpStatus.NOT_FOUND);
				
	}
	
	
	
}
