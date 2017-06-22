package br.ufop;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.ufop.chartgenerator.gui.BarChartController;
import br.ufop.chartgenerator.gui.BoxPlotChartController;
import br.ufop.chartgenerator.gui.ChartCreationController;
import br.ufop.chartgenerator.gui.LineChartController;
import br.ufop.chartgenerator.gui.PieChartController;
import br.ufop.chartgenerator.gui.RootChartController;
import br.ufop.chartgenerator.model.ChartSuite;
import br.ufop.maingui.RootLayoutController;
import br.ufop.maingui.RootProjectController;
import br.ufop.performance.gui.ActionEditionController;
import br.ufop.performance.gui.ActionsSetController;
import br.ufop.performance.gui.AdvancedSettingsTestController;
import br.ufop.performance.gui.CheckingAlertController;
import br.ufop.performance.gui.CheckingBoxesController;
import br.ufop.performance.gui.ClickingController;
import br.ufop.performance.gui.ContextClickingController;
import br.ufop.performance.gui.RootTestController;
import br.ufop.performance.gui.SelectingOptionController;
import br.ufop.performance.gui.SubmittingController;
import br.ufop.performance.gui.TestCreationController;
import br.ufop.performance.gui.TypingController;
import br.ufop.performance.model.CheckingAlert;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.ContextClicking;
import br.ufop.performance.model.Navigating;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.SelectingOption;
import br.ufop.performance.model.Submitting;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;
import br.ufop.performance.model.Typing;
import br.ufop.testmgr.test.SchedulingTest;
import br.ufop.utils.skiplabel.AlertMessage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.guiflags.GUIFlag;

//Controlador principal; se comunica com os outros controladores
public class Main extends Application {
	private GUIFlag flag;
	private TestInput input = new TestInput(1, 100, "");
	private static ChartSuite chart = new ChartSuite();
	private SchedulingTest testSchedule = new SchedulingTest(this);
    private Stage primaryStage;
    private BorderPane rootLayout;
    private RootLayoutController controller;
    private RootTestController testController;
    private TestCreationController testCreationController;
    private AdvancedSettingsTestController advSettingsController;
    private ActionsSetController actionsSetController;
    private RootChartController rootChartController;
    private ChartCreationController chartCreationController;
    private TypingController typingController;
    private CheckingBoxesController checkingBoxesController;
    private SelectingOptionController selectingOptionController;
    private ClickingController clickingController;
    private SubmittingController submittingController;
    private CheckingAlertController checkingAlertController;
    private ContextClickingController contextClickingController;
    private LineChartController lineChartController;
    private PieChartController pieChartController;
    private BarChartController barChartController;
    private BoxPlotChartController boxplotChartController;
    private ActionEditionController actionEditionController;
    private RootProjectController rootProjectController;
    private ObservableList<PerformanceTestCase> data = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
    	
    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ViewIt");

        initRootLayout();

        showWelcomeView();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("maingui/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            

            
         // Give the controller access to the main app.
            controller = loader.getController();
        	controller.setMain(this);
        	
            primaryStage.setFullScreen(true);
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				
				@Override
				public void handle(WindowEvent event) {
					System.exit(0);				
				}
			});
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showWelcomeView() {
    	try {
    		//Load welcome page
    		FXMLLoader load = new FXMLLoader();
            load.setLocation(Main.class.getResource("maingui/WelcomeLayout.fxml"));
            AnchorPane welcomeLayout = (AnchorPane) load.load();
            rootLayout.setCenter(welcomeLayout);
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Shows the RootTest with the main root layout.
    */ 
    public void showRootTestView() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/RootTest.fxml"));
            AnchorPane testRootLayout = (AnchorPane) loader.load();
            rootLayout.setTop(testRootLayout);
         
         // Give the controller access to the main app.
            testController = loader.getController();
        	testController.setMain(this);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the view to create a scenario of test with main and test root layouts.
    */ 
    public void showTestCreationView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/TestCreation.fxml"));
            AnchorPane testView = (AnchorPane) loader.load();
            rootLayout.setCenter(testView);
            
            // Give the controller access to the main app.
            testCreationController = loader.getController();
            testCreationController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public void showAdvancedSettingsView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/AdvancedSettingsForTest.fxml"));
            AnchorPane advSetView = (AnchorPane) loader.load();
            rootLayout.setCenter(advSetView);
            
            // Give the controller access to the main app.
            advSettingsController = loader.getController();
            advSettingsController.setMain(this);
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
        
    public void showMyActionsView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/ActionsSet.fxml"));
            AnchorPane actionsSetView = (AnchorPane) loader.load();
            rootLayout.setCenter(actionsSetView);
            
            // Give the controller access to the main app.
            actionsSetController = loader.getController();
            actionsSetController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showRootChartView() {
   	 try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/RootChart.fxml"));
            AnchorPane chartRootLayout = (AnchorPane) loader.load();
            rootLayout.setTop(chartRootLayout);
         
         // Give the controller access to the main app.
        	rootChartController = loader.getController();
           	rootChartController.setMain(this);
           	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showChartCreationView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/ChartCreation.fxml"));
            AnchorPane chartView = (AnchorPane) loader.load();
            rootLayout.setCenter(chartView);
            
            // Give the controller access to the main app.
        	chartCreationController = loader.getController();
            chartCreationController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTypingView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/Typing.fxml"));
            AnchorPane typingView = (AnchorPane) loader.load();
            rootLayout.setCenter(typingView);
            
            // Give the controller access to the main app.
        	typingController = loader.getController();
            typingController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCheckingBoxesView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/CheckingBoxes.fxml"));
            AnchorPane checkingBoxesView = (AnchorPane) loader.load();
            rootLayout.setCenter(checkingBoxesView);
            
            // Give the controller access to the main app.
            checkingBoxesController = loader.getController();
            checkingBoxesController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSelectingOptionView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/SelectingOption.fxml"));
            AnchorPane selectingOptionView = (AnchorPane) loader.load();
            rootLayout.setCenter(selectingOptionView);
            
            // Give the controller access to the main app.
            selectingOptionController = loader.getController();
            selectingOptionController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showClickingView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/Clicking.fxml"));
            AnchorPane clickingView = (AnchorPane) loader.load();
            rootLayout.setCenter(clickingView);
            
            // Give the controller access to the main app.
            clickingController = loader.getController();
            clickingController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showSubmittingView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/Submitting.fxml"));
            AnchorPane submittingView = (AnchorPane) loader.load();
            rootLayout.setCenter(submittingView);
            
            // Give the controller access to the main app.
            submittingController = loader.getController();
            submittingController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCheckingAlertView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/CheckingAlert.fxml"));
            AnchorPane checkingAlertView = (AnchorPane) loader.load();
            rootLayout.setCenter(checkingAlertView);
            
            // Give the controller access to the main app.
            checkingAlertController = loader.getController();
            checkingAlertController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showContextClickingView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/ContextClicking.fxml"));
            AnchorPane contextClickingView = (AnchorPane) loader.load();
            rootLayout.setCenter(contextClickingView);
            
            // Give the controller access to the main app.
        	contextClickingController = loader.getController();
            contextClickingController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void showLineChartView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/LineChart.fxml"));
            AnchorPane lineView = (AnchorPane) loader.load();
            rootLayout.setCenter(lineView);
            
            // Give the controller access to the main app.
        	lineChartController = loader.getController();
            lineChartController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPieChartView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/PieChart.fxml"));
            AnchorPane pieView = (AnchorPane) loader.load();
            rootLayout.setCenter(pieView);
            
            // Give the controller access to the main app.
        	pieChartController = loader.getController();
            pieChartController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBarChartView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/BarChart.fxml"));
            AnchorPane barView = (AnchorPane) loader.load();
            rootLayout.setCenter(barView);
            
            // Give the controller access to the main app.
            barChartController = loader.getController();
            barChartController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showBoxPlotChartView() {
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/BoxPlotChart.fxml"));
            AnchorPane boxPlotView = (AnchorPane) loader.load();
            rootLayout.setCenter(boxPlotView);
            
            // Give the controller access to the main app.
            boxplotChartController = loader.getController();
            boxplotChartController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showActionEditionView(PerformanceTestCase action){
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("performance/gui/ActionEdition.fxml"));
            AnchorPane actionView = (AnchorPane) loader.load();
            rootLayout.setCenter(actionView);
            
            // Give the controller access to the main app.
            actionEditionController = loader.getController();
            actionEditionController.setMain(this);
            actionEditionController.setAction(action);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showRootProjectView(){
    	try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("maingui/RootProjectLayout.fxml"));
            AnchorPane projectView = (AnchorPane) loader.load();
            rootLayout.setTop(projectView);
            
            // Give the controller access to the main app.
            rootProjectController = loader.getController();
            rootProjectController.setMain(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadTestScenarioDataFromFile(File file){
    	try {
    		
            JAXBContext context = JAXBContext
                    .newInstance(TestScenarioListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            try{
            	TestScenarioListWrapper wrapper = (TestScenarioListWrapper) um.unmarshal(file);
            	
                data.clear();
                data.addAll(wrapper.getActions());
//                System.out.println(wrapper.getX_times());
//                System.out.println(wrapper.getY_interval());
//                System.out.println(wrapper.getURL());
                getTestInput().setX_times(wrapper.getX_times());
                getTestInput().setY_interval(wrapper.getY_interval());
                getTestInput().setURL(wrapper.getURL());
//                System.out.println(getTestInput().getX_times());
//                System.out.println(getTestInput().getY_interval());
//                System.out.println(getTestInput().getURL());
//                for(int i = 0; i < data.size(); i++){
//                	System.out.println("Tamanho da lista de acoes: " + data.size());
//                	System.out.println(data);
//                }
      
                showTestCreationView();
                setProjectFilePath(file);
            }
            catch(Exception e){
            	e.printStackTrace();
            }
            

        } catch (Exception e) { 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    public void saveTestScenarioDataToFile(File file) {
        try {
//        	for(int i = 0; i < data.size(); i++){
//            	System.out.println("Tamanho da lista de acoes: " + data.size());
//            	System.out.println(data);
//            }
        	
            JAXBContext context = JAXBContext
                    .newInstance(TestScenarioListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TestScenarioListWrapper wrapper = new TestScenarioListWrapper();
            wrapper.setActions(data);
            wrapper.setX_times(getTestInput().getX_times());
            wrapper.setY_interval(getTestInput().getY_interval());
            wrapper.setURL(getTestInput().getURL());
//            System.out.println(getTestInput().getX_times());
//            System.out.println(getTestInput().getY_interval());
//            System.out.println(getTestInput().getURL());
//            System.out.println(wrapper.getX_times());
//            System.out.println(wrapper.getY_interval());
//            System.out.println(wrapper.getURL());
           
            m.marshal(wrapper, file);
            setProjectFilePath(file);
            AlertMessage.showConfirmationAlert("Advanced Settings", "", "The test scenario has been saved successfully!");
        } catch (Exception e) {
        	e.printStackTrace();
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    
    public File getProjectFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        System.out.println("filepath in main.getProjectFilePath " + filePath);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    public void setProjectFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            primaryStage.setTitle("Project - " + file.getName());
        } else {
            prefs.remove("filePath");

            primaryStage.setTitle("Project");
        }
    }
    
    public TestInput getTestInput() {
    	return input;
    }
    
    private void setTestInput(TestInput input) {
    	this.input = input;
    	System.out.println("main TestInput apos receber wrapper.input " + this.input);
    	System.out.println(this.input.getX_times());
    	System.out.println(this.input.getY_interval());
    }
    
    public ChartSuite getChartSuite() {
    	return chart;
    }

    public SchedulingTest getSchedulingTest() {
    	return testSchedule;
    }
    
    public ObservableList<PerformanceTestCase> getData() {
    	return data;
    }
    
    public void setGUIFlag() {
    	flag = new GUIFlag(true, getChartSuite().getCsvPath(), getChartSuite());
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}