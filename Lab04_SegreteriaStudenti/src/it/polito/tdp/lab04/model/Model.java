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
	
	/**
	 * Restituisco la lista degli studenti iscritti a quel corso
	 * @param c
	 * @return
	 */
	public List<Studente> getStudentiDelCorso(Corso c){
		return corsoDAO.getStudentiIscrittiAlCorso(c);
	}
	
	public Corso getCorsoDatoNome(String nomeCorso) {
		return corsoDAO.getCorso(nomeCorso);
	}
	
	/**
	 * Data la matricola, restituisce tutti i corsi a cui lo studente e' iscritto
	 * @param matricola
	 * @return
	 */
	public List<Corso> getListaCorsiDaMatricolaModel(int matricola){
		return studenteDAO.getCorsoDaMatricola(matricola);
	}
	
	/**
	 * Verifica iscrizione dello studente al corso
	 * @param s
	 * @param c
	 * @return true se lo studente e' iscritto, false altrimenti
	 */
	public boolean verificaIscrizoneModel(Studente s,Corso c) {
		return corsoDAO.verificaIscrizione(s, c);
	}
	
	public boolean inscriviStudenteACorsoModel(Studente s, Corso c) {
		return corsoDAO.inscriviStudenteACorso(s, c); 
	}
}
