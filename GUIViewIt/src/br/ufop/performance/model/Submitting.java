package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("submitting")
public class Submitting extends PerformanceTestCase{

	@XStreamAsAttribute
	private String description;
	
	
	@XStreamAlias("locator")
	private ByLocator locator;
	
	@XStreamAsAttribute
	private String stepID;

	

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
		this.step.set(stepID);
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	//FOR XML
	public ByLocator getLocator() {
		return locator;
	}
	
	//FOR GUI ONLY
	public void setLocatorGUI(String by, String value) {
		locator = new ByLocator();
		locator.setByFromString(by);
		locator.setValue(value);
	}

	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}

	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.submit(locator.getByObject(locator.getBy(),
				 locator.getValue()));
		
	}

}

