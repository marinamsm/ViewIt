package br.ufop.performance.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.CheckingAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CheckingAlertController {
	
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField optionInTheCheckbox;
	
	@FXML
	private ComboBox<String> stepNumber;
	
	ObservableList<String> stepsList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	private boolean control = true;
	
	public void setMain(Main main) {
		this.main = main;
		if(control){
			main.getTestInput().initCheckingAlertList();
			control = false;
		}
	}
	
	@FXML
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		stepNumber.setItems(stepsList);
	}
	
	public void okButtonAction() {
		CheckingAlert check = new CheckingAlert();
		check.setDescription(description.getText());
		
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str;
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		check.setStepID(str);
		check.stepProperty().set(str);
		check.actionProperty().set(check.getAction().get());
		check.setOption(optionInTheCheckbox.getText());
		main.getTestInput().getCheckingAlertSteps().add(check);
		for(int i = 0; i < main.getTestInput().getCheckingAlertSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getCheckingAlertSteps().get(i));
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(check);
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(CheckingAlert check) {
		main.getData().add(check);
	}
	
}
