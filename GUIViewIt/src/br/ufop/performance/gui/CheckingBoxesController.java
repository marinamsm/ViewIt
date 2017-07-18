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
	
	/**Name given to the action of checking boxes in this test case.
	 * Important if you have multiple of these actions in the same test case.*/
	/**Nome dado à ação Checking Boxes neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**Options to check in a set of check boxes*/
	/**Opções a serem selecionadas em um conjunto de check boxes*/
	@FXML
	private TextField optionsToCheck;
	
	private List<String> options;
	
	/**Locator to be used in order to locate the element(e.g: check box) in the page to select options*/
	/**Locator usado para localizar os elementos (ex: check box) na página a serem selecionados*/
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
        	main.getTestInput().initCheckingBoxList();
			options = new LinkedList();
        }
	}
	
	/**Initializes the fields in the view*/
	/**Inicializa os campos da tela*/
	@FXML
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
		main.getTestInput().sortTestCasesByStepId();
		addToObsList(check);
		main.showAdvancedSettingsView();
	}
	
	/**Returns to preview screen*/
	/**Retorna à tela anterior*/
	@FXML
	private void cancelButtonAction() {
		main.showAdvancedSettingsView();
	}
	
	public void addToObsList(CheckingBoxes check) {
		main.getData().add(check);
	}
	
}
