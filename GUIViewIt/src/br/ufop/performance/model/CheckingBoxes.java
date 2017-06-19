package br.ufop.performance.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("ckeckingBoxes")
//@XmlRootElement(name="checkBox")
public class CheckingBoxes extends PerformanceTestCase {

	@XStreamAsAttribute
	private String stepID;

	@XStreamAsAttribute
	private String description;

	@XStreamAlias("locatorRadioForm")
	private ByLocator locatorRadioForm;

	@XStreamImplicit(itemFieldName = "selectedElements_visibleText")
	private List<String> visibleTexts;
	
	//FOR GUI
	private StringProperty action = new SimpleStringProperty("CheckingBoxes");

	public StringProperty getAction() {
		return action;
	}
	
	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}
	
	public ByLocator getLocator() {
		return locatorRadioForm;
	}

	public void setLocatorRadioForm(ByLocator locatorRadioForm) {
		this.locatorRadioForm = locatorRadioForm;
	}
	
	//FOR GUI ONLY
	public void setLocatorGUI(String by, String value) {
		locatorRadioForm = new ByLocator();
		locatorRadioForm.setByFromString(by);
		locatorRadioForm.setValue(value);
	}

	public List<String> getVisibleTexts() {
		return visibleTexts;
	}

	public void setVisibleTexts(List<String> visibleTexts) {
		this.visibleTexts = visibleTexts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.checkBoxes(locatorRadioForm.getByObject(
				locatorRadioForm.getBy(), locatorRadioForm.getValue()),
				visibleTexts);

	}

}
