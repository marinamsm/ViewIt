package br.ufop.performance.gui;

import java.io.File;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;

public class RootTestController {
	private Main main;
	
	@FXML
	private ToggleButton createTestButton;
	
	@FXML
	private ToggleButton openTestButton;
	
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
		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadTestScenarioDataFromFile(file);
        }
	}

}
