package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.Typing;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TypingController {
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField key;
	
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
	
	private boolean control = true; //if control is true then it is the first time the Typing screen is loaded by main
	
	public void setMain(Main main) {
		this.main = main;
		if(control){
			main.getTestInput().initTypingList();
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
	
	public void okButtonAction() {
		Typing type = new Typing();
		type.setDescription(description.getText());
		type.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		type.setStepID(stepNumber.getValue()+1);
		type.setKey(key.getText());
		System.out.println("stepProperty.toString em typingController: " + type.stepProperty().toString());
		main.getTestInput().getTypingSteps().add(type);
		for(int i = 0; i < main.getTestInput().getTypingSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getTypingSteps().get(i));
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
}
