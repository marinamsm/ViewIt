package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("checkingAlert")
public class CheckingAlert extends PerformanceTestCase{
	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;
	
	@XStreamAsAttribute
	private String option;
	
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