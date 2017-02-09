package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("navigating")
public class Navigating extends PerformanceTestCase {

	@XStreamAsAttribute
	private String description;
	@XStreamAsAttribute
	private String pageURL;
	
	@XStreamAsAttribute
	private String stepID;
	
	public String getPageURL() {
		return pageURL;
	}

	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
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

	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot=new ActionOrientedAbstraction(webdriver);
		actionBot.navigate(pageURL);
		
	}
	

	
	
}
