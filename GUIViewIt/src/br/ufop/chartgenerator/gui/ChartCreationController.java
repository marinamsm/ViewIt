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
	private Button boxplot;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button finishButton;
	
	@FXML
	private Button cancelButton;
	
	private boolean control = true; //if it is the first time this class is called, then it is "true"
	
	private ChartSuite chartSuite;
	
	public void setMain(Main main) {
        this.main = main;
        if(control) {
        	IChartGenerator chartExecution = ChartGeneratorFactory.createInstance(ProvidedInterface.ICHARTGENERATOR);
        	chartSuite = new ChartSuite();
        	control = false;
        }
    }
	
	@FXML
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
		
	}
	
	@FXML 
	public void barButtonAction() {
		
	}
	
	@FXML 
	public void boxplotButtonAction() {
		
	}
	
	@FXML
	private void finishButtonAction() {
		chartSuite.addChartsToSetConfig();
	}

	@FXML
	private void cancelButtonAction() {
		main.showWelcomeView();
	}
	@FXML
	private void backButtonAction() {
		
	}

}
