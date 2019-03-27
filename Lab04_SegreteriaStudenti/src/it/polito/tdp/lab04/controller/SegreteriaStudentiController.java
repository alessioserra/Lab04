package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model = new Model();
	
	public void setModel(Model model) {
		
		this.model=model;
		this.getValoriComboBox();
	}
	
	//Setto il comboBox con tutti i nomi dei corsi
	public void getValoriComboBox() {
	    comboBox.getItems().addAll(model.getStringheCorsi());
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Da verificare
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnVerifica;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorso;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorso(ActionEvent event) {

    }

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	txtResult.clear();
    	Corso c = new Corso();

    	//Controllo selezione vuota
    	if (comboBox.getValue() != null) {
    	c = model.getCorsoDatoNome(comboBox.getValue());
    	
    	List<Studente> studenti = model.getStudentiDelCorso(c);
    	
    	for (Studente s : studenti) {
    		txtResult.appendText(s.toString()+"\n");
    	    }
    	}
    	
    	else txtResult.appendText("Errore nella selezione del corso!");
    	

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {

    }

    @FXML
    void doVerifica(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	Studente s = new Studente();
    	
    	try {
    	s = this.model.getStudente(Integer.parseInt(txtInput.getText()));
    	} catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un valore valido!\n");
    		return;
    	}
    	
    	if (s.getNome()!=null && s.getCognome()!=null) {
    		txtNome.setText(s.getNome());
    		txtCognome.setText(s.getCognome());
    	}

    	else txtResult.appendText("Studente non trovato!\n");
    	
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnVerifica != null : "fx:id=\"btnVerifica\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorso != null : "fx:id=\"btnCercaCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
}
