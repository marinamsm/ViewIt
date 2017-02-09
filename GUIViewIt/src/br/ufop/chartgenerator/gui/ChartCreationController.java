package br.ufop.chartgenerator.gui;

import br.ufop.Main;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class ChartCreationController {
	
	private Main main;
	
	@FXML
	private CheckBox Line; 
	
	@FXML
	private CheckBox pie;
	
	@FXML
	private CheckBox bar;
	
	@FXML
	private CheckBox boxplot;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button finishButton;
	
	@FXML
	private Button cancelButton;
	
	public void setMain(Main main) {
        this.main = main;
     }
	
	public ChartCreationController() {
	}
	
	@FXML
	private void finishButtonAction() {
		
	}

	@FXML
	private void cancelButtonAction() {
		main.showWelcomeView();
	}
	@FXML
	private void backButtonAction() {
		
	}

}
