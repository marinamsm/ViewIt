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
	
	/**Name given to the action of context clicking in this test case.
	 * Important if you have multiple of these actions in the same test case.*/
	/**Nome dado a esta ação neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
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
	
	@FXML
	public void okButtonAction() {
		ContextClicking context = new ContextClicking();
		context.setDescription(description.getText());
		context.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str;
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		context.setStepID(str);
		context.stepProperty().set(str);
		context.actionProperty().set(context.getAction().get());
		main.getTestInput().getContextClickingSteps().add(context);
		for(int i = 0; i < main.getTestInput().getContextClickingSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getContextClickingSteps().get(i));
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(context);
		main.showAdvancedSettingsView();
	}
	
	@FXML
	public void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(ContextClicking context) {
		main.getData().add(context);
	}
}
