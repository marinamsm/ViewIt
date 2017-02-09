package br.ufop.maingui;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class RootLayoutController {
	
	private Main main;
	@FXML
	private ToggleButton analyticsButton;
	@FXML
	private ToggleButton testButton;
	@FXML
	private ToggleButton projectButton;

	public RootLayoutController() {
		
	}
	
	public void setMain(Main main) {
        this.main = main;
    }
	
	@FXML
	private void analyticsButtonAction() {
	
	}
	
	@FXML
	private void testButtonAction() {
		main.showRootTestView();
	}
	
	@FXML
	private void projectButtonAction() {
		
	}
}
