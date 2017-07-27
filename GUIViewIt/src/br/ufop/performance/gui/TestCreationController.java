package br.ufop.performance.gui;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class TestCreationController {
	
	private Main main;
	
	@FXML
	private AnchorPane self;
	
	@FXML
	private TextField URLField; 
	
	@FXML
	private ComboBox<Integer> x_times;
	
	@FXML
	private ComboBox<String> y_interval;
	
	private ObservableList<Integer> optionX_times = FXCollections.observableArrayList(1,2,3,5,10,15,30);
	
	private ObservableList<String> optionY_interval = FXCollections.observableArrayList("100 ms","200 ms","300 ms", "1 min", "5 min", "15 min", "30 min", "1 h",
																							"3 h", "5 h", "12 h", "24 h");
	
	@FXML
	private Button advancedButton;
	
	@FXML
	private Button runButton;
	
	@FXML
	private Button save;
	
	@FXML
	private Button saveAs;
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button exitButton;
	
	private List <String> pageName = new LinkedList<String>();
	
	private boolean control = false;
	
	//acessa API para ter acesso ao PerformanceTestingSchedule
	IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
			.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	/**This function sets a variable with the main controller of the application.
	 * It also sets options to the view in case the user opens an existing scenario.*/
	/**Recebe o principal controlador (Main) da aplicação
	 * e modifica a view caso o usuário carregue um cenário já existente.*/
	public void setMain(Main main, AnchorPane testView) {
		if(this.main == null){
			this.main = main;
//			self = testView;
		}
		if(main.getTestInput().getURL() != "") {
        	URLField.setText(main.getTestInput().getURL());
            x_times.setValue(main.getTestInput().getX_times()); 
            y_interval.setValue(Integer.toString(main.getTestInput().getY_interval())+" ms");
            control = true;
        }
    }
	
	public void initialize() {
		//bind controller to model
		//setBind();
		x_times.setValue(1);
		x_times.setItems(optionX_times);
		y_interval.setValue("100 ms");
		y_interval.setItems(optionY_interval);
    	URLField.setText("https://");
	}
	
	public TestCreationController() {}	
	
	/** Sets the test case according to the user's input*/
	/** Configura o caso de teste de acordo com a entrada do usuário*/
	private void setTest() {
		main.getTestInput().setMain(main);
		if(x_times.getSelectionModel().getSelectedItem() != null)
			main.getTestInput().setX_times(x_times.getSelectionModel().getSelectedItem());
		else main.getTestInput().setX_times(1);
		if(y_interval.getSelectionModel().getSelectedItem() != null){
			String[] interval = y_interval.getSelectionModel().getSelectedItem().split("\\s");
			//verificar se já está em ms ou não
			if(interval[1].contains("min")){
				main.getTestInput().setY_interval((int)(long)TimeUnit.MILLISECONDS.convert(Integer.parseInt(interval[0]), TimeUnit.MINUTES));
//				System.out.println("TestCreationController - y_interval " + (int)(long)TimeUnit.MILLISECONDS.convert(Integer.parseInt(interval[0]), TimeUnit.MINUTES));
			}
			else if(interval[1].contains("h")){
				main.getTestInput().setY_interval((int)(long)TimeUnit.MILLISECONDS.convert(Integer.parseInt(interval[0]), TimeUnit.HOURS));
//				System.out.println("TestCreationController - y_interval " + (int)(long)TimeUnit.MILLISECONDS.convert(Integer.parseInt(interval[0]), TimeUnit.HOURS));
			}
			else{
				main.getTestInput().setY_interval(Integer.parseInt(interval[0]));
			}
		}
		else main.getTestInput().setY_interval(100);
		main.getTestInput().setURL(URLField.getText());
		main.getTestInput().setNavigationOnSave();
	}
	
	/** Opens file chooser to choose where to save the test case and other files generated in the process (har and csv).
	 * It calls the function "setTest" beforing saving. */
	/** Abre janela de diálogo para escolher onde salvar o caso de teste e outros arquivos gerados durante o processo (har e csv).
	 * Chama a função "setTest" antes de salvar.*/
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
	
	/** Runs the test case after calling "setTest".
	 * Runs the test as background task (using Task).*/
	/** Executa o caso de teste após chamar "setTest".
	 * A execução do teste é feita em segundo plano (usando Task).*/
	@FXML
	private void runButtonAction() {
		boolean stop = true;
		while(stop){
			try{
				if(!control)
					setTest();
				stop = false;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		callTest();
	}
	
	/** Used by "runButtonAction". It runs the test case in a background thread. It uses Task.*/
	/** Usado em "runButtonAction". Executa o caso de teste em uma thread em segundo plano. Usa Task.*/
	private void callTest() {
//		ProgressIndicator progressIndicator = new ProgressIndicator();
//        self.getChildren().addAll(progressIndicator);
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

	/**Returns to home screen*/
	/**Retorna à tela inicial*/
	@FXML
	private void backButtonAction() {
		main.showWelcomeView();
	}
	
	/**Terminates the program with System.exit(0)*/
	/**Finaliza o programa com System.exit(0)*/
	@FXML
	private void exitButtonAction() {
		System.exit(0);
	}
	
	/**Shows the view for advanced settings so the user can choose more actions to be run in the test case*/
	/**Exibe a tela de configurações avançadas para que o usuário escolha mais ações na execução do caso de teste*/
	@FXML
	private void advancedButtonAction() {
		setTest();
		main.showAdvancedSettingsView();
	}
	
//	//não está funcionando
//	private void setBind() {
//		main.getTestInput().URLProperty().bind(URLField.textProperty());
//		//System.out.println("URLProp + URL " + main.getTestInput().URLProperty() + "  " + main.getTestInput().getURL());
//		main.getTestInput().x_timesProperty().bind(x_times.valueProperty());
//		//System.out.println("x_timesProp + value = " + main.getTestInput().x_timesProperty() + "  " + main.getTestInput().getX_times());
////		main.getTestInput().y_intervalProperty().bind(y_interval.valueProperty());
//	}
//	
	
	//saveButton
//	public void saveButtonAction() {
//		setTest();
//		File projectFile = main.getProjectFilePath();
//	       if (projectFile != null) {
//	       	main.saveTestScenarioDataToFile(projectFile);
//	       } else {
//	       saveAs();
//	    }
//		main.showTestCreationView();
//	}

}
