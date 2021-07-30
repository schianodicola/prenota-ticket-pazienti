package it.prenota.ticket.model.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import it.prenota.ticket.model.dto.PazienteDTO;

public class ListUtility {

	public static List<PazienteDTO> PazienteToList(PazienteDTO p) {
	    List<PazienteDTO> list = new ArrayList<>();
	    list.add(p);
	        
	    return list;
	}
	//TODO da testare.
	public static List<?> SetToList(Set<?> obj ) {
		
		return List.copyOf(obj);
	}
	
	
}
