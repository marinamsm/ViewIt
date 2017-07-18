package br.ufop.chartgenerator.gui;

import br.ufop.Main;
import br.ufop.chartgenerator.model.PieChart;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PieChartController {
	
	@FXML
	private TextField title;
	
	@FXML
	private TextField pageName;
	
	@FXML
	private TextField type;
	
	@FXML
	private TextField information;
	
	@FXML
	private Button okButton;
	
	@FXML 
	private Button cancelButton;
	
	private Main main;
	
	private boolean control = true;
	
	public void setMain(Main main) {
		if(this.main == null)
			this.main = main;
	}
	
	public PieChartController(){
	}
	
	public void initialize() {
		
	}
	
	@FXML
	public void okButtonAction() {
		
		PieChart pie = new PieChart();
		pie.setTitle(title.getText());
		pie.setPageName(pageName.getText());
		pie.setType(type.getText());
		pie.setInformation(information.getText());
		main.getChartSuite().getPieCharts().add(pie);
		main.showChartCreationView();
	}
	
	@FXML 
	public void cancelButtonAction() {
		main.showChartCreationView();
	}
	
	

}
