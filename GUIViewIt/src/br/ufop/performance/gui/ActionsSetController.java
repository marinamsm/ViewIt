package br.ufop.performance.gui;

import java.util.Iterator;

import br.ufop.Main;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.TestInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	
	private final ObservableList<PerformanceTestCase> data = FXCollections.observableArrayList();
	
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
	
	private TestInput test = null;
	
	private boolean control = true; //if control is true then it is the first time the Typing screen is loaded by main
	
	public void setMain(Main main) {
		this.main = main;
		if(control){
			control = false;
		}
	}
	
	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//Initialize the table with the two columns
    	//test = main.getTestInput();
    	Iterator<String> iterator = main.getTestInput().getMapStepId_PerformanceTest().keySet()
				.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			PerformanceTestCase testCase = main.getTestInput().getMapStepId_PerformanceTest().get(key);
			data.add(testCase);
		}
		//sets tableview
		actionTable.setItems(data);
		
		stepColumn = new TableColumn<>("");
		stepColumn.setCellValueFactory(new PropertyValueFactory<>("step"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
        
        //showTestSuiteDetails(null);
        
        // Listen for selection changes and show the test suit details when changed
        //actionTable.getSelectionModel().selectedItemProperty().addListener(
          //      (observable, oldValue, newValue) -> showTestSuitDetails(newValue));
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
