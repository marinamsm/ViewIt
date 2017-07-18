package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.Submitting;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SubmittingController {

	private Main main;
	
	/**Name given to the action of submitting in this test case.
	 * Important if you have multiple submitting actions in the same test case.*/
	/**Nome dado à ação de submeter formulário neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**Locator to be used in order to locate the element(e.g: a button) in the page to submit the form*/
	/**Locator usado para localizar o elemento (ex: um botão) na página para submeter formulário*/
	@FXML
	private ComboBox<String> inputLocator; //String to be converted into ByTypes in ByLocator class
	
	ObservableList<String> list = FXCollections.observableArrayList("ByClassName", "ByCssSelector", "ById", "ByLinkText", "ByName", "ByPartialLinkText", "ByTagName", "ByXPath");
	
	/**This is the input locator name found when inspecting element in the page to be tested*/
	/**Esse é o nome do locator encontrado ao inspecionar elemento na página a ser testada*/
	@FXML	
	private TextArea nameOfInputLocator; //String to be set in the value field of the ByLocator class
	
	/**The step number will dictate the order of execution of the actions*/
	/**O número do passo irá governar a ordem de execução das ações*/
	@FXML
	private ComboBox<String> stepNumber;
	
	ObservableList<String> stepsList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	/**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
	public void setMain(Main main) {
		if(this.main == null){
			this.main = main;
			main.getTestInput().initSubmittingList();
		}
	}
	
	/**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
	@FXML
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		inputLocator.setValue("ByXPath"); //default value
		inputLocator.setItems(list);
		stepNumber.setItems(stepsList);
	}
	
	/**Sets submitting details according to the user's input*/
	/**Configura detalhes de submissão de acordo com a entrada do usuário*/
	@FXML
	private void okButtonAction() {
		
		Submitting submit = new Submitting();
		submit.setDescription(description.getText());
		submit.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str = "";
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		submit.setStepID(str);
		submit.stepProperty().set(str);
		submit.actionProperty().set(submit.getAction().get());
		main.getTestInput().getSubmittingSteps().add(submit);
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(submit);
		main.showAdvancedSettingsView();
	}
	
	/**Returns to preview screen*/
	/**Retorna à tela anterior*/
	@FXML
	private void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	/**Adds Typing object already set to an ObservableList (JAVAFX list).
	 * The list is used by "ActionsSetController" to show the all the actions.*/
	/**Adiciona objeto de Typing já configurado a uma ObservableList (lista do JAVAFX).
	 * A lista é usada em "ActionsSetController" para mostrar todas as ações.*/
	public void addToObsList(Submitting submit) {
		main.getData().add(submit);
	}
}
