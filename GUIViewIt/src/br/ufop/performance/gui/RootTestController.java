package br.ufop.performance.gui;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class RootTestController {
	private Main main;
	
	@FXML
	private ToggleButton createTestButton;
	
	@FXML
	private ToggleButton openTestButton;
	
	@FXML
	private ToggleButton removeTestButton;
	
	public void setMain(Main main) {
        this.main = main;
    }
	
	public RootTestController() {
	}
	@FXML
	private void createTestButtonAction() {
		main.showTestCreationView();
	}
	@FXML
	private void openTestButtonAction() {
		
	}
	@FXML
	private void removeTestButtonAction() {
		
	}
}
