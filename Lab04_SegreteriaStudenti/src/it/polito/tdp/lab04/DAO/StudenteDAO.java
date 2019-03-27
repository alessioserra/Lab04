package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/**
	 * Data la matricola restituisce il nome e il cognome dello studente, dal database
	 * @param matricola
	 * @return
	 */
	public Studente getStudente(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola=?";
		Studente s = new Studente();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			//Setto il valore della matricola
			st.setInt(1, matricola);
			
			//Eseguo Query
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				int m = rs.getInt("matricola");
				String codins = rs.getString("CDS");
				
				s.setCognome(cognome);
				s.setMatricola(m);
				s.setNome(nome);
				s.setCodIns(codins);
			}

			return s;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> getCorsoDaMatricola(int matricola){
		
		final String sql = "SELECT * FROM iscrizione WHERE matricola=?";
		CorsoDAO corsoDAO = new CorsoDAO();
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			//Setto il valore della matricola
			st.setInt(1, matricola);
			
			//Eseguo Query
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				Corso c = corsoDAO.getCorsoCodIns(codins);
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}
