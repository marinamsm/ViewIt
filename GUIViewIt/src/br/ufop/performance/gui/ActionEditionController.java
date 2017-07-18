package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.CheckingAlert;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.ContextClicking;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.SelectingOption;
import br.ufop.performance.model.Submitting;
import br.ufop.performance.model.Typing;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ActionEditionController {
	
	/**Name given to the action.
	 * Important if you have multiple actions of the same type.*/
	/**Nome dado à ação.
	 * Importante se houver várias ações do mesmo tipo.*/
	@FXML
    private TextField description;
	
	/**What is going to be typed when the test is running. 
	 * Used only for Typing action. */
	/**O que será digitado durante a execução do teste.
	 * Usado apenas na ação de digitar. */
    @FXML
    private TextField key;
    
    /**Locator to be used in order to locate the element in the page.
     * Unnecessary in Checking Alert action.*/
	/**Locator usado para localizar o elemento na página.
	 * Desnecessário para a ação de Checking Alert.*/
    @FXML
    private TextField typeOfLocator;
    
    /**This is the input locator name found when inspecting element in the page to be tested.
     *Unnecessary in Checking Alert action.*/
	/**Esse é o nome do locator encontrado ao inspecionar elemento na página a ser testada.
	 * Desnecessário para a ação de Checking Alert.*/
    @FXML
    private TextArea locator;
    
    /**Checking option to check alert message.
     * Used only for Checking Alert action. */
	/**Opção selecionada para confirmar mensagem.
	 * Usado apenas na ação Checking Alert.*/
    @FXML
    private TextField optionToCheck;
    
    @FXML
    private Button okButton;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button cancelButton;
    
    private PerformanceTestCase action;
    
    private Main main;
    
    private boolean control = true; //if control is true then it is the first time the Typing screen is loaded by main
    
    /**This function sets a variable with the main controller of the application.*/
	/**Recebe o principal controlador (Main) da aplicação*/
    public void setMain(Main main) {
    	if(this.main == null){
    		this.main = main;
        	if(control){
    			main.getTestInput().initTypingList();
    			control = false;
    		}
    	}
    }
    /**Sets the view with the actions the user chose*/
    /**Configura a tela com as ações que o usuário escolheu*/
    public void setAction(PerformanceTestCase action) {
    	this.action = action;
    	Typing type = new Typing();
    	Clicking click = new Clicking();
    	Submitting submit = new Submitting();
    	SelectingOption select = new SelectingOption();
    	ContextClicking context = new ContextClicking();
    	CheckingBoxes check = new CheckingBoxes();
    	CheckingAlert alert = new CheckingAlert();
    	if(action.getClass().isInstance(type)) {
    		type = (Typing)action;
    		description.setText(type.getDescription());
            key.setText(type.getKey());
            typeOfLocator.setText(type.getLocator().getBy().toString());
            locator.setText(type.getLocator().getValue());
            optionToCheck.setText("-");
    	}
    	else if(action.getClass().isInstance(click)) {
    		click = (Clicking)action;
    		description.setText(click.getDescription());
    		key.setText("-");
            typeOfLocator.setText(click.getLocator().getBy().toString());
            locator.setText(click.getLocator().getValue());
            optionToCheck.setText("-");
    	}
    	else if(action.getClass().isInstance(submit)) {
    		submit = (Submitting)action;
    		description.setText(submit.getDescription());
    		key.setText("-");
            typeOfLocator.setText(submit.getLocator().getBy().toString());
            locator.setText(submit.getLocator().getValue());
            optionToCheck.setText("-");
    	}
    	else if(action.getClass().isInstance(select)) {
    		select = (SelectingOption)action;
    		description.setText(select.getDescription());
    		key.setText("-");
            typeOfLocator.setText(select.getLocator().getBy().toString());
            locator.setText(select.getLocator().getValue());
            optionToCheck.setText("-");
            //SET VISIBLETEXT??
    	}
    	else if(action.getClass().isInstance(context)) {
    		context = (ContextClicking)action;
    		description.setText(context.getDescription());
    		key.setText("-");
            typeOfLocator.setText(context.getLocator().getBy().toString());
            locator.setText(context.getLocator().getValue());
            optionToCheck.setText("-");
            //SET NAME??
    	}
    	else if(action.getClass().isInstance(check)) {
    		check = (CheckingBoxes)action;
    		description.setText(check.getDescription());
    		key.setText("-");
            typeOfLocator.setText(check.getLocator().getBy().toString());
            locator.setText(check.getLocator().getValue());
            optionToCheck.setText("-");
          //SET VISIBLETEXTS??
    	}
    	else if(action.getClass().isInstance(alert)) {
    		alert = (CheckingAlert)action;
    		description.setText(alert.getDescription());
    		key.setText("-");
    		typeOfLocator.setText("-");
    		locator.setText("-");
            optionToCheck.setText(alert.getOption());
    	}
    	else{
    	description.setText("-");
        key.setText("-");
        typeOfLocator.setText("-");
        locator.setText("-");
        optionToCheck.setText("-");
    	}
    }
    
    @FXML
    private void okButtonAction() {
    	Typing type = new Typing();
    	Clicking click = new Clicking();
    	Submitting submit = new Submitting();
    	SelectingOption select = new SelectingOption();
    	ContextClicking context = new ContextClicking();
    	CheckingBoxes check = new CheckingBoxes();
    	CheckingAlert alert = new CheckingAlert();
    	if(action.getClass().isInstance(type)) {
    		type = (Typing)action;
    		type.setDescription(description.getText());
    		type.setKey(key.getText());
    		type.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    	}
    	else if(action.getClass().isInstance(click)) {
    		click = (Clicking)action;
    		click.setDescription(description.getText());
    		click.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    	}
    	else if(action.getClass().isInstance(submit)) {
    		submit = (Submitting)action;
    		submit.setDescription(description.getText());
    		submit.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    	}
    	else if(action.getClass().isInstance(select)) {
    		select = (SelectingOption)action;
    		select.setDescription(description.getText());
    		select.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    		//SET VISIBLETEXT??
    	}
    	else if(action.getClass().isInstance(context)) {
    		context = (ContextClicking)action;
    		context.setDescription(description.getText());
    		context.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    		//SET NAME??
    	}
    	else if(action.getClass().isInstance(check)) {
    		check = (CheckingBoxes)action;
    		check.setDescription(description.getText());
    		check.setLocatorGUI(typeOfLocator.getText(), locator.getText());
    		//SET VISIBLETEXTS??
    	}
    	else if(action.getClass().isInstance(alert)) {
    		alert = (CheckingAlert)action;
    		alert.setDescription(description.getText());
    		alert.setOption(optionToCheck.getText());
    	}
    	main.showMyActionsView();
    }
    
    /**Returns to preview screen*/
	/**Retorna à tela anterior*/
    @FXML
    private void backButtonAction() {
    	main.showMyActionsView();
    }
    
    /**Returns to preview screen*/
	/**Retorna à tela anterior*/
    @FXML
    private void cancelButtonAction() {
    	main.showMyActionsView();
    }
}
