package it.prenota.ticket.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

import it.prenota.ticket.model.dto.EsitoDTO;
import it.prenota.ticket.model.dto.PazienteDTO;
import it.prenota.ticket.model.entity.Paziente;
import it.prenota.ticket.model.mapper.PazienteMapper;
import it.prenota.ticket.model.response.PazienteResponse;
import it.prenota.ticket.model.util.EsitoUtility;
import it.prenota.ticket.model.util.ListUtility;
import it.prenota.ticket.model.util.StringUtility;
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
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		if(lpazienti.isEmpty()) { 
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			
			//List<PazienteDTO> lista= new ArrayList<>(lpazienti); //TODO prova a togliere i warning
			List<PazienteDTO> lista= List.copyOf(lpazienti);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
	
	}
	
	@RequestMapping(path = "/inserisci", method = RequestMethod.POST)
	public ResponseEntity<?> inserire( @RequestBody final PazienteDTO paziente ) {
		
		PazienteResponse pr= new PazienteResponse(); 
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		
		//class string utility
		if(paziente==null || StringUtility.isEmpty(paziente.getCf()) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoBad());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		//if(paziente==null || paziente.getCf()==null || paziente.getCf().equals("") ) return new ResponseEntity<>("non hai inserito i dati del paziente", HttpStatus.BAD_REQUEST);
		
		//Controllo se il CF e' già presente, per vedere se salvarlo o meno
		if(sPaziente.findByCf( paziente.getCf() ) != null) {
			return new ResponseEntity<>("Paziente gia' esistente", HttpStatus.OK); //TODO ricontrollare
		}
		
		
		//TODO da rivedere
		try {
			sPaziente.inserisci(paziente);
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(paziente);
			pr.setPazientiDTO(lista);
		}catch(Exception e) {
			System.err.println("[Errore] Impossibile inserire il paziente (paziente null)");
			pr.setEsitoDTO(EsitoUtility.setEsitoOkButError());
			return new ResponseEntity<>(pr, HttpStatus.OK); 
		}
		
		return new ResponseEntity<>(pr, HttpStatus.OK);
		
		
	}
	
	
	@RequestMapping(path = "/aggiorna", method = RequestMethod.PUT)
	public ResponseEntity<?> aggiornare( @RequestBody final PazienteDTO paziente ) {
		
		PazienteResponse pr= new PazienteResponse();
		if(paziente==null || StringUtility.isEmpty(paziente.getCf()) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoBad());
			pr.setPazientiDTO(ListUtility.PazienteToList(paziente)); //nella casistica di errore , non c'è bisogno
			return new ResponseEntity<>(pr , HttpStatus.BAD_REQUEST);
		}
		PazienteDTO p=sPaziente.findByCf(paziente.getCf());
		
		if(p == null) {
			return new ResponseEntity<>("Il Paziente che vuoi modificare non presente nel db", HttpStatus.OK);
		}
		
		
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		//aggiorno Paziente
		paziente.setId_paziente(p.getId_paziente());
		if(sPaziente.aggiorna(paziente) == null) {
			return new ResponseEntity<>("errore modifica", HttpStatus.NOT_FOUND);
		}
		else {
			//pr.getPazientiDTO().add(p); 
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(paziente);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}
		
	}
	
	//cancella l'articolo tramite CF
	@RequestMapping(path = "/elimina/{cf}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminare( @PathVariable String cf ) {
			
		PazienteResponse pr= new PazienteResponse(); 
		
		if(cf==null || StringUtility.isEmpty(cf) ) {
			pr.setEsitoDTO(EsitoUtility.setEsitoBad());
			return new ResponseEntity<>(pr, HttpStatus.BAD_REQUEST);
		}
		//controllo se il paziente è presente
		PazienteDTO p= sPaziente.findByCf(cf); //Sei sicuro che restituisca null se non è presente un paziente?
		if(p == null) {
			return new ResponseEntity<>("paziente non presente", HttpStatus.OK); 
		}
					
			
		pr.setEsitoDTO(EsitoUtility.setEsitoOk());
		if(sPaziente.elimina(cf) == true) {
			//pr.getPazientiDTO().add(p); // solo in caso di esito negativo, o anche positivo?
			List<PazienteDTO> lista= new ArrayList<>();
			lista.add(p);
			pr.setPazientiDTO(lista);
			return new ResponseEntity<>(pr, HttpStatus.OK);
		}else {
			//pr.setEsitoDTO(EsitoUtility.setEsitoOkButError()); Corretto, ma è un uso improprio dell'utility?
			return new ResponseEntity<>(pr , HttpStatus.NOT_FOUND);//cambia
		}
				
	}
	
	
	
}
