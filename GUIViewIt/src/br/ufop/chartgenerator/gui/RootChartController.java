package br.ufop.chartgenerator.gui;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class RootChartController {
	private Main main;
	
	@FXML
	private ToggleButton createButton;
	
	@FXML
	private ToggleButton openButton;
	
	@FXML
	private ToggleButton removeButton;
	
	public void setMain(Main main) {
        this.main = main;
    }
	
	public RootChartController() {
	}
	@FXML
	private void createButtonAction() {
		main.showChartCreationView();
	}
	@FXML
	private void openButtonAction() {
		
	}
	@FXML
	private void removeButtonAction() {
		
	}
}
