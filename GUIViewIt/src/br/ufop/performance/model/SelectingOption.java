package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("selectingOption")
public class SelectingOption extends PerformanceTestCase {

	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;

	@XStreamAlias("locatorCheckBoxes")
	private ByLocator locatorCheckBoxes;

	@XStreamAlias("selectedElement_visibleText")
	private String visibleText;

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

	public ByLocator getLocatorCheckBoxes() {
		return locatorCheckBoxes;
	}

	//FOR GUI ONLY
	public void setLocatorGUI(String by, String value) {
		locatorCheckBoxes = new ByLocator();
		locatorCheckBoxes.setByFromString(by);
		locatorCheckBoxes.setValue(value);
	}

	
	public void setLocatorCheckBoxes(ByLocator locatorCheckBoxes) {
		this.locatorCheckBoxes = locatorCheckBoxes;
	}

	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
	}

	@Override
	public void executeTest(WebDriver webdriver) {

		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.selectOptionInRadioForm(locatorCheckBoxes.getByObject(
				locatorCheckBoxes.getBy(), locatorCheckBoxes.getValue()),
				visibleText);

	}

}