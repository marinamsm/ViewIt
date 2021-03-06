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
        if(this.main == null)
        	this.main = main;
    }
	
	@FXML
	private void analyticsButtonAction() {
		if(!testButton.isHover()){
	
		}
		main.showRootChartView();
	}
	
	@FXML
	private void testButtonAction() {
		main.showRootTestView();
	}
	
	@FXML
	private void projectButtonAction() {
		main.showRootProjectView();
	}
}
