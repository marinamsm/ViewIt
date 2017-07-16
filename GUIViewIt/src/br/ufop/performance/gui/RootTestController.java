package br.ufop.performance.gui;

import java.io.File;

import br.ufop.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import utils.guiflags.GUIFlag;

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
	
	/**This function is called when the button "Create Scenario" is pressed.
	 * It redirects to the main test page*/
	/**Esta função é chamada ao pressionar o botão "Create Scenario".
	 * Ela redireciona para a página principal de testes*/
	@FXML
	private void createTestButtonAction() {
		main.showTestCreationView();
	}
	
	/** This Opens the file chooser*/
	/** Abre a janela de diálogo para seleção de arquivo*/
	@FXML
	private void openTestButtonAction() {
		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        GUIFlag.rootPath = file.getParent();
        
        if (file != null) {
            main.loadTestScenarioDataFromFile(file);
        }
	}

}
