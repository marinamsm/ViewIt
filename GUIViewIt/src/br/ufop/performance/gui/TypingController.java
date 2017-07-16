package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.Typing;
import br.ufop.utils.skiplabel.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TypingController {
	private Main main;
	
	/**Name given to the action of typing in this test case.
	 * Important if you have multiple typing actions in the same test case.*/
	/**Nome dado à ação de digitar neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**What is going to be typed when the test is running */
	/**O que será digitado durante a execução do teste */
	@FXML
	private TextField key;
	
	/**Locator to be used in order to locate the element(e.g: a text field) in the page where something(key) will be typed in*/
	/**Locator usado para localizar o elemento (ex: um campo de texto) na página onde algo(key) será digitado*/
	@FXML
	private ComboBox<String> inputLocator;
	
	ObservableList<String> list = FXCollections.observableArrayList("ByClassName", "ByCssSelector", "ById", "ByLinkText", "ByName", "ByPartialLinkText", "ByTagName", "ByXPath");
	
	/**This is the input locator name found when inspecting element in the page to be tested*/
	/**Esse é o nome do locator encontrado ao inspecionar elemento na página a ser testada*/
	@FXML	
	private TextArea nameOfInputLocator;
	
	/**The step number will dictate the order of execution of the actions*/
	/**O número do passo irá governar a ordem de execução das ações*/
	@FXML
	private ComboBox<String> stepNumber;
	
	ObservableList<String> stepsList = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
	
	@FXML
	private Button okButton;
	
	@FXML
	private Button cancelButton;
	
	private boolean control = true; //if control is true then it is the first time the Typing screen is loaded by main
	
	/**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
	public void setMain(Main main) {
		this.main = main;
		if(control){
			main.getTestInput().initTypingList();
			control = false;
		}
	}
	
	/**Initializes the fields in the view*/
	/**Inicializa os campos da tela*/
	public void initialize() {
		//this method is called before any other methods because it is called during screen loading
		inputLocator.setValue("ByXPath");
		inputLocator.setItems(list);
		stepNumber.setItems(stepsList);
	}
	
	/**Sets typing details according to the user's input*/
	/**Configura detalhes de digitação de acordo com a entrada do usuário*/
	@FXML
	private void okButtonAction() {
		Typing type = new Typing();
		type.setDescription(description.getText());
		type.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str;
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		type.setStepID(str);
		type.stepProperty().set(str);
		type.actionProperty().set(type.getAction().get());
		type.setKey(key.getText());
		/*if(main.getTestInput().getMapStepId_PerformanceTest() != null){
			System.out.println("AEEEEEEEEEEE");
			if(main.getTestInput().getMapStepId_PerformanceTest().containsKey(str)){
				AlertMessage.showWarningAlert("Warning", "Step already filled!", "This step has already been filled. Do you want to override it?");
				
			}
		}*/
		main.getTestInput().getTypingSteps().add(type);
		for(int i = 0; i < main.getTestInput().getTypingSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getTypingSteps().get(i)); 
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(type);
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
	public void addToObsList(Typing type) {
		main.getData().add(type);
	}
}
