package br.ufop.chartgenerator.gui;

import java.io.File;

import br.ufop.Main;
import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory.ProvidedInterface;
import br.ufop.chartgenerator.model.ChartTypeSuite;
import br.ufop.utils.skiplabel.AlertMessage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import utils.guiflags.GUIFlag;

public class ChartCreationController {
	
	private Main main;
	
	@FXML
	private Button line; 
	
	@FXML
	private Button pie;
	
	@FXML
	private Button bar;
	
	@FXML
	private Button boxPlot;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button finishButton;
	
	@FXML
	private Button cancelButton;
	
	public void setMain(Main main) {
        this.main = main;
    }
	
	
	public void initialize() {
	}
	
	public ChartCreationController() {
		
	}
	
	@FXML
	public void lineButtonAction() {
		main.showLineChartView();
	}
	
	@FXML
	public void pieButtonAction() {
		main.showPieChartView();
	}
	
	@FXML 
	public void barButtonAction() {
		main.showBarChartView();		
	}
	
	@FXML 
	public void boxPlotButtonAction() {
		main.showBoxPlotChartView();
	}
	
	@FXML
	private void finishButtonAction() {
		main.getChartSuite().addChartsToSetConfig();
		if(main.getChartSuite().getChartsConfig() == null || main.getChartSuite().getChartsConfig().size() == 0){
			AlertMessage.showWarningAlert("No chart set", "No charts were set", "Please, set the charts to generate them");
			System.out.println("chartSuite.chartsConfig == null");
			return;
		}
		if(GUIFlag.csvPathForChart == null){
			AlertMessage.showWarningAlert("No Path Selected", "No path was selected to read the CSV file", "Please, choose the CSV file path");
            FileChooser fileChooser = new FileChooser();

            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);

            // Show open file dialog
            File file = fileChooser.showOpenDialog(main.getPrimaryStage());

            if (file != null) {
                main.getChartSuite().setCsvPath(file.getPath());
                main.setGUIFlag();
                GUIFlag.rootPath = file.getParent();
            }
		}
		for(ChartTypeSuite chartCg : main.getChartSuite().getChartsConfig()) {
			chartCg.setChartFolder(GUIFlag.rootPath);
			System.out.println(chartCg.getClass() + " " + chartCg.getChartFolder());
		}
		main.getChartGenerator().run();
		System.exit(0);
	}

	@FXML
	private void cancelButtonAction() {
		main.showWelcomeView();
	}
	@FXML
	private void backButtonAction() {
		main.showRootChartView();
	}

}
