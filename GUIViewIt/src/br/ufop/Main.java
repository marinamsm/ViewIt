package br.ufop;
/*	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} */

import java.io.IOException;

import br.ufop.chartgenerator.gui.ChartCreationController;
import br.ufop.chartgenerator.gui.RootChartController;
import br.ufop.maingui.RootLayoutController;
import br.ufop.performance.gui.AdvancedSettingsTestController;
import br.ufop.performance.gui.CheckingAlertController;
import br.ufop.performance.gui.CheckingBoxesController;
import br.ufop.performance.gui.ClickingController;
import br.ufop.performance.gui.ContextClickingController;
import br.ufop.performance.gui.RootTestController;
import br.ufop.performance.gui.TestCreationController;
import br.ufop.performance.gui.TypingController;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.TestInput;
import br.ufop.testmgr.test.SchedulingTest;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//Controlador principal; se comunica com os outros controladores
public class Main extends Application {

	private TestInput input = new TestInput(1, 100, "");
	//private Clicking click = new Clicking();
	private SchedulingTest testSchedule = new SchedulingTest(this);
    private Stage primaryStage;
    private BorderPane rootLayout;

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
            RootLayoutController controller = loader.getController();
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
            RootTestController controller = loader.getController();
           	controller.setMain(this); 
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
            TestCreationController controller = loader.getController();
            controller.setMain(this);
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
            AdvancedSettingsTestController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   /* public TestSuite getTestSuite() {
    	return suite;
    }*/
    
    public void showRootChartView() {
   	 try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("chartgenerator/gui/RootChart.fxml"));
            AnchorPane chartRootLayout = (AnchorPane) loader.load();
            rootLayout.setTop(chartRootLayout);
         
         // Give the controller access to the main app.
            RootChartController controller = loader.getController();
           	controller.setMain(this); 
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
            ChartCreationController controller = loader.getController();
            controller.setMain(this);
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
            TypingController controller = loader.getController();
            controller.setMain(this);
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
            CheckingBoxesController controller = loader.getController();
            controller.setMain(this);
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
            ClickingController controller = loader.getController();
            controller.setMain(this);
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
            CheckingAlertController controller = loader.getController();
            controller.setMain(this);
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
            ContextClickingController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public TestInput getTestInput() {
    	return input;
    }
    
    public SchedulingTest getSchedulingTest() {
    	return testSchedule;
    }
    
    /*public Clicking getClicking() {
    	return click;
    }*/
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}