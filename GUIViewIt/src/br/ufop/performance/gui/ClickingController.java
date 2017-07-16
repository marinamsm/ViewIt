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
	
	/**Name given to the action of clicking in this test case.
	 * Important if you have multiple of these actions in the same test case.*/
	/**Nome dado à ação de clique neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**Locator to be used in order to locate the element(e.g: button) in the page to click in*/
	/**Locator usado para localizar o elemento (ex: um botão) na página a ser clicado*/
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
	
	private boolean control = true; //if control is true then it is the first time the Clicking screen is loaded by main
	
	/**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
	public void setMain(Main main) {
		this.main = main;
		if(control) {
			main.getTestInput().initClickingList();
			control = false;
		}
		
	}
	
	/**Initializes the fields in the view*/
	/**Inicializa os campos da tela*/
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		inputLocator.setValue("ByXPath"); //default value
		inputLocator.setItems(list);
		stepNumber.setItems(stepsList);
	}
	
	/**Sets details according to the user's input*/
	/**Configura detalhes de acordo com a entrada do usuário*/
	@FXML
	private void okButtonAction() {
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
	
	/**Returns to preview screen*/
	/**Retorna à tela anterior*/
	@FXML
	private void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(Clicking click) {
		main.getData().add(click);
	}

}
