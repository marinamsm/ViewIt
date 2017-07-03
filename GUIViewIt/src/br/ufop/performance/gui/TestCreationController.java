package br.ufop.performance.gui;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.testmgr.api.IPerformanceTestingSchedule;
import br.ufop.testmgr.impl.TestMgrFactory;
import br.ufop.testmgr.impl.TestMgrFactory.TestMgrProvidedInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import utils.guiflags.GUIFlag;

public class TestCreationController {
	
	private Main main;
	
	@FXML
	private TextField URLField; 
	
	@FXML
	private ComboBox<Integer> x_times;
	
	@FXML
	private ComboBox<Integer> y_interval;
	
	private ObservableList<Integer> optionX_times = FXCollections.observableArrayList(1,2,3);
	
	private ObservableList<Integer> optionY_interval = FXCollections.observableArrayList(100,200,300);
	
	@FXML
	private Button advancedButton;
	
	@FXML
	private Button runButton;
	
	@FXML
	private Button save;
	
	@FXML
	private Button saveAs;
	
	@FXML
	private Button cancelButton;
	
	private List <String> pageName = new LinkedList<String>();
	
	//acessa API para ter acesso ao PerformanceTestingSchedule
	IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
			.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	
	public void setMain(Main main) {
        this.main = main;
        //inicializa os campos para input do usuário
        /*no construtor não funciona porque o construtor é chamado antes de carregar a interface gráfica,
         * assim os campos da interface ainda são nulos (são alocados automaticamente no fxml pelo SceneBuilder)*/
        x_times.setItems(optionX_times);
		y_interval.setItems(optionY_interval);
        if(main.getTestInput().getURL() != "") {
        	URLField.setText(main.getTestInput().getURL());
            x_times.setValue(main.getTestInput().getX_times());
            y_interval.setValue(main.getTestInput().getY_interval());
//            main.getTestInput().loadTestCasesByStepId();
//            if(GUIFlag.rootPath != ""){
//            	System.out.println("TestCreationController class " + GUIFlag.rootPath);
//            	main.getTestInput().setCsvFolder(GUIFlag.rootPath + "\\" + main.getTestInput().getURL());
//            	main.getTestInput().setHarFolder(GUIFlag.rootPath + "\\" + main.getTestInput().getURL());
//            	GUIFlag.csvPathForChart = GUIFlag.rootPath + "\\" + main.getTestInput().getURL();
//            }
        }
        else {
        	URLField.setText("https://");
        }
        
		//init();
		
    }
	
	public void init() {
		//bind controller to model
		//setBind();
	}
	
	//não está funcionando
	public void setBind() {
		main.getTestInput().URLProperty().bind(URLField.textProperty());
		//System.out.println("URLProp + URL " + main.getTestInput().URLProperty() + "  " + main.getTestInput().getURL());
		main.getTestInput().x_timesProperty().bind(x_times.valueProperty());
		//System.out.println("x_timesProp + value = " + main.getTestInput().x_timesProperty() + "  " + main.getTestInput().getX_times());
		main.getTestInput().y_intervalProperty().bind(y_interval.valueProperty());
	}
	
	public TestCreationController() {
	}
	
	//saveButton
	public void saveButtonAction() {
		setTest();
		File projectFile = main.getProjectFilePath();
	       if (projectFile != null) {
	       	main.saveTestScenarioDataToFile(projectFile);
	       } else {
	       saveAs();
	    }
		main.showTestCreationView();
	}
	
	public void setTest() {
		main.getTestInput().setMain(main);
		if(x_times.getSelectionModel().getSelectedItem() != null)
			main.getTestInput().setX_times(x_times.getSelectionModel().getSelectedItem());
		if(y_interval.getSelectionModel().getSelectedItem() != null)
			main.getTestInput().setY_interval(y_interval.getSelectionModel().getSelectedItem());
		main.getTestInput().setURL(URLField.getText());
		main.getTestInput().setNavigationOnSave();
	}
	
	public void saveAsButtonAction() {
		setTest();
		saveAs();
	}
	
	@FXML
	private void saveAs() {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
		          "XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            System.out.println("file.getPath " + file.getPath());
            System.out.println("file.getParentPath " + file.getParent());
            try{
            	System.out.println("file.getCanonicalPath " + file.getCanonicalPath());
            }
            catch(Exception e){
            	e.printStackTrace();
            }
//            main.getTestInput().setHarFolder(file.getParent()+ "\\harDirectory");
//            main.getTestInput().setCsvFolder(file.getParent()+ "\\csvDirectory");
//            main.getTestInput().setSaveFlag(true);
            main.saveTestScenarioDataToFile(file);
        }
	}
	
	@FXML
	private void runButtonAction() {
		boolean control = true;
		while(control){
			try{
				setTest();
				control = false;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		callTest();
	}
	
	private void callTest() {
		//executa os testes em segundo plano para não travar a interface gráfica
		Task<Void> task = new Task<Void>() {
		    @Override public Void call() {
		    	testSchedule.setMain(main);
		    	pageName.add("Home");
		    	//run the tests and save the results in .har files
		    	testSchedule.runPeriodically(pageName);
		    	
		    	return null;
		    }
		};
		new Thread(task).start();
	}

	
	@FXML
	private void cancelButtonAction() {
		main.showWelcomeView();
	}
	@FXML
	private void advancedButtonAction() {
		setTest();
		main.showAdvancedSettingsView();
	}

}
