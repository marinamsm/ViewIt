package br.ufop.performance.gui;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CheckingAlertController {
	
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private ComboBox optionInTheCheckbox;
	
	@FXML
	private ComboBox stepNumber;
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void okButtonAction() {
		main.showAdvancedSettingsView();
	}
	

}
