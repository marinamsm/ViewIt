package br.ufop.chartgenerator.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory;
import br.ufop.chartgenerator.impl.ChartGeneratorFactory.ProvidedInterface;
import br.ufop.chartgenerator.model.ChartSuite;
import br.ufop.chartgenerator.model.LineChart;
import br.ufop.chartgenerator.model.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

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
		IChartGenerator chartExecution = 
				ChartGeneratorFactory.createInstance(ProvidedInterface.ICHARTGENERATOR);
		chartExecution.run();
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
