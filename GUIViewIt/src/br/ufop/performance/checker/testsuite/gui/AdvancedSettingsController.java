package br.ufop.performance.checker.testsuite.gui;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdvancedSettingsController {
	private Main main;
	
	@FXML
	private Button back;
	
	public void setMain(Main main) {
        this.main = main;
	}
	
	public void backButtonAction() {
		main.showTestCreationView();
	}
}
