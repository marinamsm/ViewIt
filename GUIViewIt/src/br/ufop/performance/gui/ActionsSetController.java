package br.ufop.performance.gui;

import java.util.Iterator;

import br.ufop.Main;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.TestInput;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
	private Label nameOfLocatorLabel;
	
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
        /*actionColumn.setCellValueFactory(new Callback<CellDataFeatures<PerformanceTestCase, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<PerformanceTestCase, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                return p.getValue().actionProperty();
            }
         });*/
        
        //showTestSuiteDetails(null);
        
        // Listen for selection changes and show the test suit details when changed
        //actionTable.getSelectionModel().selectedItemProperty().addListener(
          //      (observable, oldValue, newValue) -> showTestSuitDetails(newValue));

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
    	for (int i = 0; i < main.getData().size(); i++) {
    		System.out.println("Step: " + main.getData().get(i).stepProperty().get()+ "  Action: " + main.getData().get(i).actionProperty().get());
    	}	
    }
    
    private void showTestSuiteDetails(PerformanceTestCase testCase) {
        if (testCase != null) {
        	
            // Fill the labels with info from the person object.
            /*descriptionLabel.setText(testCase.getClass()getFirstName());
            keyLabel.setText(person.getLastName());
            locatorLabel.setText(person.getStreet());
            nameOfLocatorLabel.setText(Integer.toString(person.getPostalCode()));
            optionToCheck.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthday()));*/
        } else {
            // Person is null, remove all the text.
            /*firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");*/
        }
    }

}
