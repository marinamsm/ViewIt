package br.ufop.maingui;

import br.ufop.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class WelcomeController {
	private Main main;
	
	ObservableList<String> list = FXCollections.observableArrayList("Tutorial (video)", "Help (pdf)", "FAQs");
	ObservableList<String> llist = FXCollections.observableArrayList("English", "Português");
	
	@FXML
	private ChoiceBox gettingStarted;
	
	@FXML
	private ChoiceBox language;
	
	@FXML
	private Label about; 
	
	
	public void setMain(Main main) {
		if(this.main == null)
        	this.main = main;
	}
	
	@FXML
	public void initialize() {
		gettingStarted.setItems(list);
		language.setValue("English");
		language.setItems(llist);
	}
	
	@FXML
	public void gettingStartedChoiceBox() {
		
	}
	
}
