package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.ContextClicking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContextClickingController {
	
	private Main main;
	
	@FXML
	private TextField description;
	
	@FXML
	private TextField name;
	
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
			main.getTestInput().initContextClickingList();
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
		ContextClicking context = new ContextClicking();
		context.setDescription(description.getText());
		context.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		context.setStepID(stepNumber.getValue()+1);
		main.getTestInput().getContextClickingSteps().add(context);
		for(int i = 0; i < main.getTestInput().getContextClickingSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getContextClickingSteps().get(i));
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
}
