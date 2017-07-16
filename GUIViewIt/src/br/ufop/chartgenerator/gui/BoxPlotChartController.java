package br.ufop.chartgenerator.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.chartgenerator.model.BoxPlotChart;
import br.ufop.chartgenerator.model.DataViews;
import br.ufop.chartgenerator.model.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BoxPlotChartController {
	
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
	
	private List<String> dataviews = new LinkedList();
	
	private Main main;
	
	private boolean control = true;
	
	public void setMain(Main main) {
		this.main = main;
	} 
	
	public BoxPlotChartController(){
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
		dataviews.add("waiting");
		dataviews.add("blocking");
		dataviews.add("receiving");
		dataviews.add("totalOfTimings");
		dataviews.add("onLoad");
		DataViews dataview = new DataViews();
		dataview.setDataView(dataviews);
		BoxPlotChart boxPlot = new BoxPlotChart();
		boxPlot.setPages(page);
		boxPlot.setTitle(title.getText());
		boxPlot.setDataViews(dataview);
		main.getChartSuite().getBoxPlotCharts().add(boxPlot);
		main.showChartCreationView();
	}
	
	@FXML 
	public void cancelButtonAction() {
		main.showChartCreationView();
	}
	
	
}
