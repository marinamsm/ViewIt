package br.ufop.performance.gui;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.CheckingAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CheckingAlertController {
	
	private Main main;
	
	/**Name given to the action of checking alert message in this test case.
	 * Important if you have multiple of these actions in the same test case.*/
	/**Nome dado à ação de confirmar mensagem de alerta neste caso de teste.
	 * Importante se houver várias dessas ações no mesmo caso de teste.*/
	@FXML
	private TextField description;
	
	/**Checking option to check alert message*/
	/**Opção selecionada para confirmar mensagem*/
	@FXML
	private TextField optionInTheCheckbox;
	
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
        }
	}
	
	/**Initializes the fields in the view*/
	/**Inicializa os campos da tela*/
	public void initialize() { 
		//this method is called before any other methods because it is called during screen loading
		stepNumber.setItems(stepsList);
	}
	
	/**Sets details according to the user's input*/
	/**Configura detalhes de acordo com a entrada do usuário*/
	@FXML
	private void okButtonAction() {
		CheckingAlert check = new CheckingAlert();
		check.setDescription(description.getText());
		
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
		check.setOption(optionInTheCheckbox.getText());
		main.getTestInput().getCheckingAlertSteps().add(check);
		for(int i = 0; i < main.getTestInput().getCheckingAlertSteps().size(); i++)
			System.out.println("   " + main.getTestInput().getCheckingAlertSteps().get(i));
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
	
	public void addToObsList(CheckingAlert check) {
		main.getData().add(check);
	}
	
}
