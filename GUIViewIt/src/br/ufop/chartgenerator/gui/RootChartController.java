package br.ufop.chartgenerator.gui;

import java.io.File;

import br.ufop.Main;
import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory.ProvidedInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import utils.guiflags.GUIFlag;

public class RootChartController {
	private Main main;
	
	@FXML
	private ToggleButton createButton;
	
	@FXML
	private ToggleButton openButton;
	
	public void setMain(Main main) {
        this.main = main;
        GUIFlag.GUI = true;
    }
	
	public RootChartController() {
	}
	@FXML
	private void createButtonAction() {
		main.showChartCreationView();
	}
	@FXML
	private void openButtonAction() {
		FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            GUIFlag.csvPathForChart = file.getPath();
            System.out.println(file.getPath());
            System.out.println(GUIFlag.csvPathForChart);
            main.getChartGenerator().run();
        }
	}
}
