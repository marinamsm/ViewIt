package br.ufop.chartgenerator.gui;

import java.util.List;

import br.ufop.Main;
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
	
	private List<Pages> pages;
	
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	} 
	
	public LineChartController(){
	}
	
	public void initialize() {
		
	}
	
	@FXML
	public void okButtonAction() {
		main.showChartCreationView();
	}
	
	@FXML 
	public void cancelButtonAction() {
		
	}
	
	
}
