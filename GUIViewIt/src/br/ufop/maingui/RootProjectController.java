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
	private ToggleButton createButton;
	
	@FXML
	private ToggleButton openButton;
	
	@FXML
	private ToggleButton exitButton;
	
	@FXML
	private ToggleButton about;
	
	private boolean control = true; //if control is true then it is the first time the Typing screen is loaded by main
	
	public void setMain(Main main) {
        this.main = main;
        if(control){
			control = false;
		}
    }
	
	public RootProjectController() {
	}
	
	public void createButtonAction() {
		//main.showChartCreationView();
		File projectFile = main.getProjectFilePath();
        if (projectFile != null) {
            main.saveTestScenarioDataToFile(projectFile);
        } else {
            saveAs();
        }
	}
	
	 @FXML
	 private void saveAs() {
	        FileChooser fileChooser = new FileChooser();

	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);

	        File file = fileChooser.showSaveDialog(main.getPrimaryStage());

	        if (file != null) {

	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	                System.out.println("saveAs, file.getPath: " + file.getPath());
	                System.out.println("saveAs, file: " + file);
	            }
	            main.saveTestScenarioDataToFile(file);
	        }
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
        alert.setContentText("Authors: Amanda Sávio Nascimento e Silva, Eduardo Chagas, Marina de Souza Mendes - UFOP\nWebsite: http://www.decom.ufop.br/anascimento/");

        alert.showAndWait();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void exitAction() {
        System.exit(0);
    }
}
