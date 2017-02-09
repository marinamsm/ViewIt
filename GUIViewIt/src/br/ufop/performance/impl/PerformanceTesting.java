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

class PerformanceTesting implements IPerformanceTesting {

	private Main main;
	
	private WebDriver webDriver;

	private FirefoxProfile profile;

	private TestInput config;
	
	private TestSuite t;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public TestSuite loadTestSuite() {return t;}
	
	public TestInput loadTestInput() {
		try {
			//faz e retorna as configurações do teste
			config = main.getTestInput();
			config.setNavigation();
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
        config.executeTest(webDriver);
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
        String domain =  "extensions.firebug.";//para versão > 47 do FF: "extensions.firebug@software.joehewitt.com.";   

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.18");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        profile.setPreference(domain + "netexport.defaultLogDir", config.getHarDirectoryPath());
	}

}
