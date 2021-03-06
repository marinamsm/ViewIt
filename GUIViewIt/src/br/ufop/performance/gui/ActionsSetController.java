package br.ufop.performance.gui;

import br.ufop.Main;
import br.ufop.performance.model.CheckingAlert;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.ContextClicking;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.SelectingOption;
import br.ufop.performance.model.Submitting;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.Typing;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ActionsSetController {
	
	@FXML
    private TableView<PerformanceTestCase> actionTable;
    
	@FXML
    private TableColumn<PerformanceTestCase, String> stepColumn;
    
	@FXML
    private TableColumn<PerformanceTestCase, String> actionColumn;
	
	/**Name given to the action.
	 * Important if you have multiple actions of the same type.*/
	/**Nome dado � a��o.
	 * Importante se houver v�rias a��es do mesmo tipo.*/
	@FXML
	private Label descriptionLabel;
	
	/**What is going to be typed when the test is running. 
	 * Used only for Typing action. */
	/**O que ser� digitado durante a execu��o do teste.
	 * Usado apenas na a��o de digitar. */
	@FXML
	private Label keyLabel;
	
	/**This is the input locator name found when inspecting element in the page to be tested.
     *Unnecessary in Checking Alert action.*/
	/**Esse � o nome do locator encontrado ao inspecionar elemento na p�gina a ser testada.*/
	@FXML
	private Label locatorLabel;
	
	/**Locator to be used in order to locate the element in the page.
     * Unnecessary in Checking Alert action.*/
	/**Locator usado para localizar o elemento na p�gina.
	 * Desnecess�rio para a a��o de Checking Alert.*/
	@FXML
	private Label typeOfLocatorLabel;
	
	 /**Checking option to check alert message.
     * Used only for Checking Alert action. */
	/**Op��o selecionada para confirmar mensagem.
	 * Usado apenas na a��o Checking Alert.*/
	@FXML
	private Label optionToCheckLabel;
	
	@FXML
	private Main main;
	
	@FXML
	private Button newButton;
	
	@FXML
	private Button editButton;
	
	@FXML
	private Button deleteButton;
	
	@FXML
	private Button backButton;
	
	private TestInput test;
	
	/**This function sets a variable with the main controller of the application*/
	/**Recebe o principal controlador (Main) da aplica��o*/
	public void setMain(Main main) {
		if(this.main == null){
			this.main = main;
			test = main.getTestInput();
			
			setTable();
			
			stepColumn.setCellValueFactory(new PropertyValueFactory<>("step"));
	        
			actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
	        showTestSuiteDetails();
	        
	        // Listen for selection changes and show the test suite details when changed
	        actionTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showTestSuiteDetails());
		}
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public void initialize() {
    	//Initialize the table with the two columns
     }
    /**Sets TableView in order to show all the actions chosen by the user */
    /**Configura TableView para mostrar todas as a��es escolhidas pelo usu�rio*/
    private void setTable(){
    	//sets tableview
//    	for(int i = 0; i < main.getData().size(); i++) {
//    		System.out.println(main.getData());
//    	}
    	actionTable.setItems(main.getData());
    	System.out.println(actionTable.getItems());
    	/*for (int i = 0; i < main.getData().size(); i++) {
    		System.out.println("Step: " + main.getData().get(i).stepProperty().get()+ "  Action: " + main.getData().get(i).actionProperty().get());
    	}*/	
    }
    
    /**Updates the view accordig to actions*/
    /**Atualiza a tela de acordo com as a��es*/
    private void showTestSuiteDetails() {
    	
    	//Add change listener
        //actionTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            //Check whether item is selected and set value of selected item to Label
            if (actionTable.getSelectionModel().getSelectedItem() != null) {
            	Typing type = new Typing();
            	Clicking click = new Clicking();
            	Submitting submit = new Submitting();
            	SelectingOption select = new SelectingOption();
            	ContextClicking context = new ContextClicking();
            	CheckingBoxes check = new CheckingBoxes();
            	CheckingAlert alert = new CheckingAlert();
            	if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(type)) {
            		type = (Typing)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(type.getDescription());
                    keyLabel.setText(type.getKey());
                    typeOfLocatorLabel.setText(type.getLocator().getBy().toString());
                    locatorLabel.setText(type.getLocator().getValue());
                    optionToCheckLabel.setText("-");
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(click)) {
            		click = (Clicking)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(click.getDescription());
            		keyLabel.setText("-");
                    typeOfLocatorLabel.setText(click.getLocator().getBy().toString());
                    locatorLabel.setText(click.getLocator().getValue());
                    optionToCheckLabel.setText("-");
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(submit)) {
            		submit = (Submitting)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(submit.getDescription());
            		keyLabel.setText("-");
                    typeOfLocatorLabel.setText(submit.getLocator().getBy().toString());
                    locatorLabel.setText(submit.getLocator().getValue());
                    optionToCheckLabel.setText("-");
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(select)) {
            		select = (SelectingOption)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(select.getDescription());
            		keyLabel.setText("-");
                    typeOfLocatorLabel.setText(select.getLocator().getBy().toString());
                    locatorLabel.setText(select.getLocator().getValue());
                    optionToCheckLabel.setText("-");
                    //SET VISIBLETEXT??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(context)) {
            		context = (ContextClicking)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(context.getDescription());
            		keyLabel.setText("-");
                    typeOfLocatorLabel.setText(context.getLocator().getBy().toString());
                    locatorLabel.setText(context.getLocator().getValue());
                    optionToCheckLabel.setText("-");
                    //SET NAME??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(check)) {
            		check = (CheckingBoxes)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(check.getDescription());
            		keyLabel.setText("-");
                    typeOfLocatorLabel.setText(check.getLocator().getBy().toString());
                    locatorLabel.setText(check.getLocator().getValue());
                    optionToCheckLabel.setText("-");
                  //SET VISIBLETEXTS??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(alert)) {
            		alert = (CheckingAlert)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(alert.getDescription());
            		keyLabel.setText("-");
            		typeOfLocatorLabel.setText("-");
            		locatorLabel.setText("-");
                    optionToCheckLabel.setText(alert.getOption());
            	}
            }
            else{
            	descriptionLabel.setText("-");
                keyLabel.setText("-");
                typeOfLocatorLabel.setText("-");
                locatorLabel.setText("-");
                optionToCheckLabel.setText("-");
            }
    }
    
    @FXML
    private void newButton() {
    	main.showAdvancedSettingsView();
    }
    
    @FXML
    private void editButton() {
    	 PerformanceTestCase selectedAction = actionTable.getSelectionModel().getSelectedItem();
         if (selectedAction != null) {
             main.showActionEditionView(selectedAction);
         }
    }
    
    @FXML
    private void deleteButton() {
    
        int selectedIndex = actionTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            actionTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Action Selected");
            alert.setContentText("Please select an action in the table.");
            alert.showAndWait();
            }
    
    }
    
    @FXML
    private void backButton() {
    	main.showAdvancedSettingsView();
    }

}
