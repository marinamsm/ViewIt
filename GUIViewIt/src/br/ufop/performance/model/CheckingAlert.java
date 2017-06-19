package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("checkingAlert")
//@XmlRootElement(name="check")
public class CheckingAlert extends PerformanceTestCase{
	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;
	
	@XStreamAsAttribute
	private String option;
	
	//FOR GUI
	private StringProperty action = new SimpleStringProperty("CheckingAlert");
	
	public StringProperty getAction() {
		return action;
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
	}


	public String getOption() {
		return option;
	}


	public void setOption(String option) {
		this.option = option;
	}


	@Override
	public void executeTest(WebDriver webDriver) {
		actionBot = new ActionOrientedAbstraction(webDriver);
		actionBot.checkAlert(option);
	}

}
