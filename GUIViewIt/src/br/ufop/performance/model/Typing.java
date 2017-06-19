package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("typing")
//@XmlRootElement(name="type")
public class Typing extends PerformanceTestCase {

	@XStreamAsAttribute
	private String description;

	@XStreamAlias("key")
	private String key;

	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String stepID;

	//FOR GUI
	private StringProperty action = new SimpleStringProperty("Typing");
	
	public StringProperty getAction() {
		return action;
	}
	
	public ByLocator getLocator() {
		return locator;
	}

	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	
	//FOR GUI ONLY
	public void setLocatorGUI(String by, String value) {
		locator = new ByLocator();
		locator.setByFromString(by);
		locator.setValue(value);
	}
		
	@Override
	public void executeTest(WebDriver webdriver) {

		actionBot = new ActionOrientedAbstraction(webdriver);

		try {
			actionBot.type(locator.getByObject(locator.getBy(),
					locator.getValue()), key);
		}
		catch(Exception e) {
			System.out.println("EXCEPTION IN TYPING");
			e.printStackTrace();
		}
			

		
		
	}

}
