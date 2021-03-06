package br.ufop.maingui;

import java.io.File;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootProjectController {
	private Main main;
	
	@FXML
	private ToggleButton openButton;
	
	@FXML
	private ToggleButton exitButton;
	
	@FXML
	private ToggleButton about;
	
	public void setMain(Main main) {
		if(this.main == null)
        	this.main = main;
    }
	
	public RootProjectController() {
	}
	
	@FXML
	private void openButtonAction() {
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
	
	@FXML
    private void aboutAction() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("ViewIt");
        alert.setHeaderText("About");
        alert.setContentText("Authors: Amanda S�vio Nascimento e Silva, Eduardo Chagas, Marina de Souza Mendes - UFOP\nWebsite: http://www.decom.ufop.br/anascimento/");

        alert.showAndWait();
    }

    @FXML
    private void exitAction() {
        System.exit(0);
    }
}
