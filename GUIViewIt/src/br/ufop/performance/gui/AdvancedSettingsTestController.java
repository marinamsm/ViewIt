package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.utils.skiplabel.AlertMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdvancedSettingsTestController {
	private Main main;
	
	@FXML
	private Button back;
	
	@FXML
	private Button ok;
	
	@FXML
	private Button typing;
	
	@FXML
	private Button checkingBoxes; 
	
	@FXML
	private Button selectingOption; //the action and the view is the same as checkingBoxes
	
	@FXML
	private Button clicking;
	
	@FXML
	private Button submitting; //the action and the view is the same as clicking
	
	@FXML
	private Button checkingAlert;
	
	@FXML
	private Button contextClicking;
	
	public void setMain(Main main) {
        this.main = main;
	}
	
	@FXML
	public void backButtonAction() {
		main.showTestCreationView();
	}
	
	//saveButton
	public void okButtonAction() {
		AlertMessage.showConfirmationAlert("Advanced Settings", "", "The steps have been saved successfully!");
		main.showTestCreationView();
	}
	
	@FXML
	public void typingButton() {
		main.showTypingView();
	}
	
	@FXML
	public void checkingBoxesButton() {
		main.showCheckingBoxesView();
	}
	
	@FXML
	public void selectingOptionButton() {
		//it has the same action and view as checkingBox
		checkingBoxesButton();
	}
	
	@FXML
	public void clickingButton() {
		main.showClickingView();
	}
	
	@FXML
	public void submittingButton() {
		clickingButton();
	}
	
	@FXML
	public void checkingAlertButton() {
		main.showCheckingAlertView();
	}
	
	@FXML
	public void contextClickingButton() {
		main.showContextClickingView();
	}
}
