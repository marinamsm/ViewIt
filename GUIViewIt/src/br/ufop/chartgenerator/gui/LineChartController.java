package br.ufop.chartgenerator.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.chartgenerator.model.LineChart;
import br.ufop.chartgenerator.model.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LineChartController {
	
	@FXML
	private TextField title;
	
	@FXML
	private TextArea pagesName;
	
	@FXML
	private Button okButton;
	
	@FXML 
	private Button cancelButton;
	
	private Pages page;
	
	private List<String> pages;
	
	private Main main;
	
	private boolean control = true;
	
	public void setMain(Main main) {
		this.main = main;
		if(control) {
        	main.getChartSuite().initLineChart();
            control = false;
        }
	} 
	
	public LineChartController(){
	}
	
	public void initialize() {
		
	}
	
	@FXML
	public void okButtonAction() {
		
		page = new Pages();
		pages = new LinkedList<String>();
		String[] s = pagesName.getText().split(" ");
		for(int i = 0; i < s.length; i++)
			pages.add(s[i]);
		page.setPages(pages);
		LineChart line = new LineChart();
		line.setPages(page);
		line.setTitle(title.getText());
		main.getChartSuite().getLineCharts().add(line);
		main.showChartCreationView();
	}
	
	@FXML 
	public void cancelButtonAction() {
		main.showChartCreationView();
	}
	
	
}
