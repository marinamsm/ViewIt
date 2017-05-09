package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("contextClicking")
public class ContextClicking extends PerformanceTestCase{
	
	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;
	
	@XStreamAsAttribute
	private String name;
	
	
	
	public ByLocator getLocator() {
		return locator;
	}



	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}

	//FOR GUI ONLY
		public void setLocatorGUI(String by, String value) {
			locator = new ByLocator();
			locator.setByFromString(by);
			locator.setValue(value);
		}

	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getStepID() {
		return stepID;
	}



	public void setStepID(String stepID) {
		this.stepID = stepID;
		this.step.set(stepID);
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public void executeTest(WebDriver webDriver) {
		this.actionBot = new ActionOrientedAbstraction(webDriver);
		actionBot.contextClicking(locator.getByObject(locator.getBy(),locator.getValue()), this.name);
	}

}
