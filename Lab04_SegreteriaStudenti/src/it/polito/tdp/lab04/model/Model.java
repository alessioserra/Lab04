package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO = new CorsoDAO();
	StudenteDAO studenteDAO = new StudenteDAO();

	/**
	 * Restituisco lista di stringhe con i nomi di tutti i corsi
	 * @return
	 */
	public List<String> getStringheCorsi() {
		
		List<Corso> corsi = corsoDAO.getTuttiICorsi();
		List<String> stringhe = new LinkedList<String>();
		
		for (Corso c : corsi) {
			stringhe.add(c.getNome());
		}
		
		return stringhe;
	}
	
	/**
	 * restituisco lo studente, data la matricola
	 * @param matricola
	 * @return
	 */
	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}
	
	
	
}
