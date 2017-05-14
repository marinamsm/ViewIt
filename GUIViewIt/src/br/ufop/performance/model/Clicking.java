package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("clicking")
public class Clicking extends PerformanceTestCase {

	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;

	//FOR GUI
	private StringProperty action = new SimpleStringProperty("Clicking");
	
	public StringProperty getAction() {
		return action;
	}
	
	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ByLocator getLocator() {
		return locator;
	}
	
	//FOR XML ONLY
	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}
	
	//FOR GUI ONLY
	public void setLocatorGUI(String by, String value) {
		locator = new ByLocator();
		locator.setByFromString(by);
		locator.setValue(value);
	}
	
	@Override
	public void executeTest(WebDriver webdriver) {

		 this.actionBot = new ActionOrientedAbstraction(webdriver);
		 actionBot.click(locator.getByObject(locator.getBy(),
		 				 		 locator.getValue()));
	}

}
