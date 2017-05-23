package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.Submitting;
import br.ufop.performance.model.Typing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClickingController {
	
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private ComboBox<String> inputLocator; //String to be converted into ByTypes in ByLocator class
	
	ObservableList<String> list = FXCollections.observableArrayList("ByClassName", "ByCssSelector", "ById", "ByLinkText", "ByName", "ByPartialLinkText", "ByTagName", "ByXPath");
	
	@FXML	
	private TextArea nameOfInputLocator; //String to be set in the value field of the ByLocator class
	
	@FXML
	private ComboBox<String> stepNumber;
	
	ObservableList<String> stepsList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	private boolean control = true; //if control is true then it is the first time the Clicking screen is loaded by main
	
	public void setMain(Main main) {
		this.main = main;
		if(control) {
			main.getTestInput().initClickingList();
			control = false;
		}
		
	}
	
	@FXML
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		inputLocator.setValue("ByXPath"); //default value
		inputLocator.setItems(list);
		stepNumber.setItems(stepsList);
	}
	
	@FXML
	public void okButtonAction() {
		Clicking click = new Clicking();
		click.setDescription(description.getText());
		click.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str;
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		click.setStepID(str);
		click.stepProperty().set(str);
		click.actionProperty().set(click.getAction().get());
		main.getTestInput().getClickingSteps().add(click);
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(click);
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(Clicking click) {
		main.getData().add(click);
	}

}
