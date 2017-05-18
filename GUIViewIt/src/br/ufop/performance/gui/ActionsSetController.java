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
	
	@FXML
	private Label descriptionLabel;
	
	@FXML
	private Label keyLabel;
	
	@FXML
	private Label locatorLabel;
	
	@FXML
	private Label typeOfLocatorLabel;
	
	@FXML
	private Label optionToCheckLabel;
	
	@FXML
	private Main main;
	
	private TestInput test;
	
	private boolean control = true; //if control is true then it is the first time this screen is loaded by main
	
	public void setMain(Main main) {
		this.main = main;
		test = main.getTestInput();
		if(control){
			control = false;
		}

		setTable();
		
		stepColumn.setCellValueFactory(new PropertyValueFactory<>("step"));
        
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        
	//	actionTable.getSelectionModel().selectedIndexProperty().addListener(new RowSelectChangeListener());
		
        showTestSuiteDetails();
        
        // Listen for selection changes and show the test suit details when changed
        actionTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTestSuiteDetails());

	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//Initialize the table with the two columns
     }
    
    private void setTable(){
    	//sets tableview
    	actionTable.setItems(main.getData());
    	/*for (int i = 0; i < main.getData().size(); i++) {
    		System.out.println("Step: " + main.getData().get(i).stepProperty().get()+ "  Action: " + main.getData().get(i).actionProperty().get());
    	}*/	
    }
    
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
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(click)) {
            		click = (Clicking)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(click.getDescription());
                    typeOfLocatorLabel.setText(click.getLocator().getBy().toString());
                    locatorLabel.setText(click.getLocator().getValue());
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(submit)) {
            		submit = (Submitting)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(submit.getDescription());
                    typeOfLocatorLabel.setText(submit.getLocator().getBy().toString());
                    locatorLabel.setText(submit.getLocator().getValue());
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(select)) {
            		select = (SelectingOption)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(select.getDescription());
                    typeOfLocatorLabel.setText(select.getLocator().getBy().toString());
                    locatorLabel.setText(select.getLocator().getValue());
                    //SETAR VISIBLETEXT E COLOCAR NA VIEW ESTA LABEL TB??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(context)) {
            		context = (ContextClicking)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(context.getDescription());
                    typeOfLocatorLabel.setText(context.getLocator().getBy().toString());
                    locatorLabel.setText(context.getLocator().getValue());
                    //SETAR NAME E COLOCAR NA VIEW ESTA LABEL TB??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(check)) {
            		check = (CheckingBoxes)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(check.getDescription());
                    typeOfLocatorLabel.setText(check.getLocator().getBy().toString());
                    locatorLabel.setText(check.getLocator().getValue());
                  //SETAR VISIBLETEXTS E COLOCAR NA VIEW ESTA LABEL TB??
            	}
            	else if(actionTable.getSelectionModel().getSelectedItem().getClass().isInstance(alert)) {
            		alert = (CheckingAlert)actionTable.getSelectionModel().getSelectedItem();
            		descriptionLabel.setText(alert.getDescription());
                    optionToCheckLabel.setText(alert.getOption());
            	}
            }
            else{
            	descriptionLabel.setText("-");
                keyLabel.setText("-");
                typeOfLocatorLabel.setText("-");
                locatorLabel.setText("-");
            }
       // });
    }

}
