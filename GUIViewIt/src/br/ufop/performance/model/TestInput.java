package br.ufop.performance.model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import br.ufop.Main;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//FOR GUI
public class TestInput extends TestSuite{
	
	private Main main;
        
	private IntegerProperty x_times;// = new SimpleIntegerProperty(1); //how many times the test will be executed
    
	private IntegerProperty y_interval;// = new SimpleIntegerProperty(100); //interval between each execution
    
	private StringProperty URL;// = new SimpleStringProperty("https://www.google.com");
    
	private StringProperty harFolder = new SimpleStringProperty("harDirectory");
    
	private final StringProperty csvFolder = new SimpleStringProperty("csvDirectory.csv");
    
	private final StringProperty testDescription = new SimpleStringProperty("description");
    
	private final StringProperty pageName = new SimpleStringProperty("pageName");
	
	private boolean saveFlag = false;

    public void setMain(Main main) {
    	if(this.main == null)
    		this.main = main;
    }
    /**
     * Default constructor.
     */
    public TestInput(){
    	
    }
    
    public TestInput(int x_times, int y_interval, String URL) {
        super.setHarPath(this.getHarFolder());
    	this.x_times = new SimpleIntegerProperty();
        this.y_interval = new SimpleIntegerProperty();
        this.URL = new SimpleStringProperty();
    	setX_times(x_times);
    	setY_interval(y_interval);
    	setURL(URL);
    }
    
    public void setNavigation() {
		setNavigationOnSave();
		sortTestCasesByStepId();
		//return this;
	}

    public void setNavigationOnSave() {
    	Navigating nav = new Navigating();
		nav.setDescription("descricaoNavigating");
		nav.setStepID("01");
		nav.setPageURL(this.getURL());
		List<Navigating> navigatingSteps = new LinkedList<Navigating>(); 
		navigatingSteps.add(nav);
		setNavigatingSteps(navigatingSteps);
		main.getData().add(nav);
    }
    
    public TestInput getThis() {
    	return this;
    }
    
    public String getCsvDirectoryPath() {
		
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + File.separator + getCsvFolder();
		return defaulLogDir;
	}
    
    public int getX_times() {
        return x_times.get();
    }

    public void setX_times(Integer x_times) {
        this.x_times.set(x_times);
    }

    public IntegerProperty x_timesProperty() {
        return x_times;
    }

    public int getY_interval() {
        return y_interval.get();
    }

    public void setY_interval(Integer y_interval) {
        this.y_interval.set(y_interval);
    }

    public IntegerProperty y_intervalProperty() {
        return y_interval;
    }
    
    public String getURL() {
        return URL.get();
    }

    public void setURL(String URL) {
        this.URL.set(URL);
    }

    public StringProperty URLProperty() {
        return URL;
    }

    public String getHarFolder() {
        return harFolder.get();
    }

    public void setHarFolder(String harFolder) {
    	super.setHarPath(harFolder);
    	this.harFolder.set(harFolder);
    }

    public StringProperty harFolderProperty() {
        return harFolder;
    }

    public String getCsvFolder() {
        return csvFolder.get();
    }

    public void setCsvFolder(String csvFolder) {
        this.csvFolder.set(csvFolder);
    }

    public StringProperty csvFolderProperty() {
        return csvFolder;
    }
 
    public String getTestDescription() {
        return testDescription.get();
    }

    public void setTestDescription(String testDescription) {
    	super.setDescription(testDescription);
        this.testDescription.set(testDescription);
    }

    public StringProperty testDescriptionProperty() {
        return testDescription;
    }

    public String getPageName() {
        return pageName.get();
    }

    public void setPageName(String pageName) {
        this.pageName.set(pageName);
    }

    public StringProperty pageNameProperty() {
        return pageName;
    }
    
    public SortedMap<String, PerformanceTestCase> getSortedMap(){
    	return super.getMapStepId_PerformanceTest();
    }
    
    public boolean getSaveFlag() {
    	return saveFlag;
    }
    
    public void setSaveFlag(boolean saveFlag) {
    	this.saveFlag = saveFlag;
    }
    
    public void loadTestCasesByStepId(Main main) {
    	if(this.main == null){
    		this.main = main;
    	}
		Typing type = new Typing();
    	Clicking click = new Clicking();
    	Submitting submit = new Submitting();
    	SelectingOption select = new SelectingOption();
    	ContextClicking context = new ContextClicking();
    	CheckingBoxes check = new CheckingBoxes();
    	CheckingAlert alert = new CheckingAlert();
    	PerformanceTestCase action;
		// buscando testes do tipo Typing
		for(PerformanceTestCase tc : main.getData()) {
			if(tc.getClass().isInstance(type)) {
				initTypingList();
				getTypingSteps().add((Typing)tc);
			}
			else if(tc.getClass().isInstance(click)) {
				initClickingList();
				getClickingSteps().add((Clicking)tc);
			}
			else if(tc.getClass().isInstance(submit)) {
				initSubmittingList();
				getSubmittingSteps().add((Submitting)tc);
			}
			else if(tc.getClass().isInstance(select)) {
				initSelectingOptionList();
				getSelectingOptionSteps().add((SelectingOption)tc);
			}
			else if(tc.getClass().isInstance(context)) {
				initContextClickingList();
				getContextClickingSteps().add((ContextClicking)tc);
			}
			else if(tc.getClass().isInstance(check)) {
				initCheckingBoxList();
				getCheckingBoxSteps().add((CheckingBoxes)tc);
			}
			else if(tc.getClass().isInstance(alert)){
				initCheckingAlertList();
				getCheckingAlertSteps().add((CheckingAlert)tc);
			}
		}
		
	}

    
}