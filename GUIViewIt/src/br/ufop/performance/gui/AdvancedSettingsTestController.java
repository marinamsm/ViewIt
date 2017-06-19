package br.ufop.performance.gui;

import java.io.File;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

public class AdvancedSettingsTestController {
	private Main main;
	
	@FXML
	private Button back;
	
	@FXML
	private Button myActions;
	
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
	
	@FXML
	public void myActionsButtonAction() {
		main.showMyActionsView();
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
		main.showSelectingOptionView();
	}
	
	@FXML
	public void clickingButton() {
		main.showClickingView();
	}
	
	@FXML
	public void submittingButton() {
		main.showSubmittingView();
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
