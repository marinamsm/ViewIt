package br.ufop.performance.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.SelectingOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CheckingBoxesController {
	
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField optionsToCheck;
	
	private List<String> options;
	
	@FXML
	private ComboBox<String> inputLocator;
	
	ObservableList<String> list = FXCollections.observableArrayList("ByClassName", "ByCssSelector", "ById", "ByLinkText", "ByName", "ByPartialLinkText", "ByTagName", "ByXPath");
	
	@FXML	
	private TextArea nameOfInputLocator;
	
	
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
			main.getTestInput().initCheckingBoxList();
			main.getTestInput().initSelectingOptionList();
			options = new LinkedList();
			control = false;
		}
	}
	
	@FXML
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		inputLocator.setValue("ByXPath");
		inputLocator.setItems(list);
		stepNumber.setItems(stepsList);
	}
	
	@FXML
	public void okButtonAction() {
		CheckingBoxes check = new CheckingBoxes();
		check.setDescription(description.getText());
		check.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
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
		String[] s = optionsToCheck.getText().split(" ");
		for(int i = 0; i < s.length; i++) {
			options.add(s[i]);
		}
		check.setVisibleTexts(options);
		main.getTestInput().getCheckingBoxSteps().add(check);
		
		SelectingOption selected = new SelectingOption();
		selected.setDescription(description.getText());
		selected.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		var = Integer.parseInt(stepNumber.getValue());
		var++;
		str = "";
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		selected.setStepID(str);
		selected.stepProperty().set(str);
		selected.actionProperty().set(selected.getAction().get());
		selected.setVisibleText("");
		main.getTestInput().getSelectingOptionSteps().add(selected);
		for(int i = 0; i < main.getTestInput().getCheckingBoxSteps().size(); i++){
			System.out.println("   " + main.getTestInput().getCheckingBoxSteps().get(i));
			System.out.println("   " + main.getTestInput().getSelectingOptionSteps().get(i));
		}
		
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(check, selected);
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(CheckingBoxes check, SelectingOption selected) {
		main.getData().add(check);
		main.getData().add(selected);
	}
	
}
