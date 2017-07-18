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

public class SelectingOptionController {

	private Main main;
	/**Name given to the action of selecting option in this test case.
	 * Important if you have multiple of these actions in the same test case.*/
	/**Nome dado à ação de selecionar opção neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**Locator to be used in order to locate the element(e.g: a radio button) in the page where an option will be selected*/
	/**Locator usado para localizar o elemento (ex: um radio button) na página onde uma opção será selecionada*/
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
	
	/**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
	public void setMain(Main main) {
		if(this.main == null){
			this.main = main;
			main.getTestInput().initSelectingOptionList();
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
	
	/**Sets details according to the user's input*/
	/**Configura detalhes de acordo com a entrada do usuário*/
	@FXML
	private void okButtonAction() {
		
		SelectingOption selected = new SelectingOption();
		selected.setDescription(description.getText());
		selected.setLocatorGUI(inputLocator.getSelectionModel().getSelectedItem(), nameOfInputLocator.getText());
		int var = Integer.parseInt(stepNumber.getValue());
		var++;
		String str = "";
		if(var < 10)
			str = "0" + Integer.toString(var);
		else
			str = Integer.toString(var);
		selected.setStepID(str);
		selected.stepProperty().set(str);
		selected.actionProperty().set(selected.getAction().get());
		selected.setVisibleText("");
		main.getTestInput().getSelectingOptionSteps().add(selected);
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(selected);
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
	public void addToObsList(SelectingOption selected) {
		main.getData().add(selected);
	}
}
