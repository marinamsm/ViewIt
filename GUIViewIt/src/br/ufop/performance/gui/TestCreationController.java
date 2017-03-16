package br.ufop.performance.gui;

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
	private Button finishButton;
	
	@FXML
	private Button cancelButton;
	
	private List <String> pageName = new LinkedList<String>();
	
	//acessa API para ter acesso ao PerformanceTestingSchedule
	IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
			.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	
	public void setMain(Main main) {
        this.main = main;
        //inicializa os campos para input do usu�rio
        /*no construtor n�o funciona porque o construtor � chamado antes de carregar a interface gr�fica,
         * assim os campos da interface ainda s�o nulos (s�o alocados automaticamente no fxml pelo SceneBuilder)*/ 
        URLField.setText("https://");
        x_times.setItems(optionX_times);
		y_interval.setItems(optionY_interval);
		//init();
		
    }
	
	public void init() {
		//bind controller to model
		//setBind();
	}
	
	//n�o est� funcionando
	public void setBind() {
		main.getTestInput().URLProperty().bind(URLField.textProperty());
		//System.out.println("URLProp + URL " + main.getTestInput().URLProperty() + "  " + main.getTestInput().getURL());
		main.getTestInput().x_timesProperty().bind(x_times.valueProperty());
		//System.out.println("x_timesProp + value = " + main.getTestInput().x_timesProperty() + "  " + main.getTestInput().getX_times());
		main.getTestInput().y_intervalProperty().bind(y_interval.valueProperty());
	}
	
	public TestCreationController() {
	}
	
	@FXML
	private void finishButtonAction() {
		boolean control = true;
		while(control){
			try{
				main.getTestInput().setX_times(x_times.getSelectionModel().getSelectedItem());
				main.getTestInput().setY_interval(y_interval.getSelectionModel().getSelectedItem());
				main.getTestInput().setURL(URLField.getText());
				System.out.println(main.getTestInput().getURL() + "  " + main.getTestInput().getX_times() + "  " + main.getTestInput().getY_interval());
				main.getTestInput().setMain(main);
				control = false;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		callTest();
	}
	
	private void callTest() {
		//executa os testes em segundo plano para n�o travar a interface gr�fica
		Task<Void> task = new Task<Void>() {
		    @Override public Void call() {
		        //main.getSchedulingTest().test();
		    	testSchedule.setMain(main);
		    	pageName.add("Home");
		    	//do the tests and save the results in .har files
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
		main.showAdvancedSettingsView();
	}

}