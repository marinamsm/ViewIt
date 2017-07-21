package br.ufop.performance.impl;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import br.ufop.Main;
import br.ufop.performance.api.IPerformanceTesting;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;
import javafx.scene.control.ProgressIndicator;

class PerformanceTesting implements IPerformanceTesting {

	private Main main;
	
	private WebDriver webDriver;

	private FirefoxProfile profile;

	private TestInput config;
	
	private TestSuite testSuite;
	
	private boolean XML = true; //defines how the program is being run, with XML or with GUI
	
	private int sleepTime; //used to prevent asynchronous testing and har reading
	
	public void setMain(Main main) {
		if(this.main == null)
			this.main = main;
	}
	
	public TestInput loadTestInput() {
		try {
			//faz e retorna as configurações do teste
			//config = main.getTestInput();
//			config.setNavigation();
			main.getTestInput().setNavigation();
			sleepTime = main.getTestInput().getY_interval();
			XML = false; //running ViewIt's GUI
//			return config;
			return main.getTestInput();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TestSuite loadTestSuite(String testSuitePath) {

		// inicialmente o numero de passos eh o.

		testSuite = TestSuiteConfigLoader.loadTestSuiteConfiguration(testSuitePath);
		testSuite.sortTestCasesByStepId();

		return testSuite;

	}
	
	public void run() {
        // configura FirefoxProfile
        setProfilePreferences();
        FirefoxBinary binary = new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe"));
        //webDriver = new FirefoxDriver(profile);
        webDriver = new FirefoxDriver(binary, profile);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        webDriver.manage().window().maximize();
        if(XML == false)
        	main.getTestInput().executeTest(webDriver);
        else
        	testSuite.executeTest(webDriver);
        webDriver.quit();
}
	private void setProfilePreferences() {

		profile = new FirefoxProfile();
		File firebug = new File("src/firefox.add-ons/firebug-2.0.18.xpi");
		File netExport = new File("src/firefox.add-ons/netExport-0.9b7.xpi");
		
		try {
			profile.addExtension(firebug);
			profile.addExtension(netExport);

			//profile.addExtension(fireStarter);

		} catch (Exception e) {
			throw new RuntimeException(
					"Could not load required extensions, did you download them to the above location? ",
					e);
		}
		// Configura preferências do Firefox
        profile.setPreference("app.update.enabled", false);
        String domain =  "extensions.firebug.";//ou para versão > 47 do FF: "extensions.firebug@software.joehewitt.com.";   

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.18");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

         
     // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        if(XML == false){
        	if(main.getTestInput().getSaveFlag()) {
//        		System.out.println("PerformanceTesting class " + main.getTestInput().getHarPath());
        		profile.setPreference(domain + "netexport.defaultLogDir", main.getTestInput().getHarPath());
        	}
        	else {
//        		System.out.println("PerformanceTesting class " + main.getTestInput().getHarDirectoryPath());
        		profile.setPreference(domain + "netexport.defaultLogDir", main.getTestInput().getHarDirectoryPath());
        	}
        }
        else{
        	profile.setPreference(domain + "netexport.defaultLogDir", testSuite.getHarDirectoryPath());
        }
        	
        /*profile.setPreference(domain + "netexport.sendToConfirmation", false);
        profile.setPreference(domain + "netexport.pageLoadedTimeout", 1500);
		profile.setPreference(domain + "netexport.Automation", true);*/
         
	}

}
