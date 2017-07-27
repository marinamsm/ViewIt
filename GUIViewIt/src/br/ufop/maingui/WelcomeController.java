package br.ufop.maingui;

import br.ufop.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;

public class WelcomeController {
	private Main main;
	
	ObservableList<String> list = FXCollections.observableArrayList("Tutorial (video)", "Help (pdf)", "FAQs");
	ObservableList<String> llist = FXCollections.observableArrayList("English", "Português");
	
	@FXML
	private ListView gettingStarted;
	
	@FXML
	private ListView language;
	
	@FXML
	private Text about; 
	
	
	public void setMain(Main main) {
		if(this.main == null)
        	this.main = main;
	}
	
	@FXML
	public void initialize() {
		gettingStarted.setItems(list);
		language.setItems(llist);
		about.setText("Authors: Amanda Sávio Nascimento e Silva, Eduardo Chagas, Marina de Souza Mendes - UFOP - DECOM");
	}
	
	@FXML
	public void gettingStartedChoiceBox() {
		
	}
	
}
