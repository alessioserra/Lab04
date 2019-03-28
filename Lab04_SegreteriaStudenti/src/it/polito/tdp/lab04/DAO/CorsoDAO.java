package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	StudenteDAO stud = new StudenteDAO();

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	    public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins,nome,numeroCrediti,periodoDidattico);
				corsi.add(c);
			}

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	    
	 public Corso getCorsoCodIns(String codIns) {
		 
			final String sql = "SELECT * FROM corso WHERE codins=?";

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);

				//Setto query
				st.setString(1, codIns);
				
				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					String codins = rs.getString("codins");
					int numeroCrediti = rs.getInt("crediti");
					String nome = rs.getString("nome");
					int periodoDidattico = rs.getInt("pd");

					Corso c = new Corso(codins,nome,numeroCrediti,periodoDidattico);
					return c;
				}

				return null;

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
	 }
	    
	    
	public Corso getCorso(String nomeCorso) {
		
		final String sql = "SELECT * FROM corso WHERE nome=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			//Setto valore del Corso
		    st.setString(1, nomeCorso);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				
				Corso c = new Corso(codins,nome,numeroCrediti,periodoDidattico);
				return c;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		
		final String sql = "SELECT matricola FROM iscrizione WHERE codins=?";
		
		List<Studente> studenti = new LinkedList<Studente>();
		String valore = corso.getCodIns();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			//Setto valore query
			st.setString(1, valore);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int matricola = rs.getInt("matricola");
				
				Studente s = stud.getStudente(matricola);
				studenti.add(s);
				
			}

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	
	public boolean verificaIscrizione(Studente studente,Corso corso) {
		
        final String sql = "SELECT * FROM iscrizione WHERE codins=? AND matricola=?";
		
		List<Studente> studenti = new LinkedList<Studente>();
		String valore1 = corso.getCodIns();
		int valore2 = studente.getMatricola();
		int flag = 0 ;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			//Setto valore query
			st.setString(1, valore1);
			st.setInt(2, valore2);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int matricola = rs.getInt("matricola");
				String codins = rs.getString("codins");
				flag=1;
				
			}

			if (flag == 1) return true;
			else return false;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	//PUNTO 6 *da fare* 
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		
		//Verifico se lo stuente e' già iscritto al quel corso tramite metodo precedente
		if (verificaIscrizione(studente,corso)==true) return false;
		
		//QUERY DI INSERIMENTO
		int matricola = studente.getMatricola();
		String codins = corso.getCodIns();
		
		/* sara' da modificare
		final String sql = "INSERT INTO iscrizione(matricola,codins) VALUES ( ? , ? )";	
		*/
		/*soluzione non del tutto corretta : */
		final String sql = "INSERT INTO iscrizione VALUES ('"+matricola+"' , '"+codins+"')";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			/*Setto parametri query
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodIns());
			*/
			
			ResultSet rs = st.executeQuery();

			conn.commit();
			
			//Verifico l'avvenuta iscrizione con metodo precedente
			if (verificaIscrizione(studente,corso)==true) return true;
			//Altrimenti restituisco false
		    return false;
		    
	} catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}
  }
}

