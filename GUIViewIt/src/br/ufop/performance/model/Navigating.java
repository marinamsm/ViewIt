package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

@XStreamAlias("navigating")
//@XmlRootElement(name="navigate")
public class Navigating extends PerformanceTestCase {

	/**Description about this object action, e.g: open the home page*/
	/**Descri��o sobre a a��o deste objeto, ex: abrir a p�gina inicial*/
	@XStreamAsAttribute
	private String description;
	
	/**URL to navigate to*/
	/**URL a ser aberta para navega��o*/
	@XStreamAsAttribute
	private String pageURL;
	
	/**Number of this action's step.
	 * This step is automatically set to 1 when using GUI.*/
	/**N�mero do passo dessa a��o.
	 * Este passp � automaticamente configurado como 1 ao usar GUI.*/
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

	/**Runs this action (Clicking) during the test according to its stepID*/
	/**Executa essa a��o (Clicking) durante o teste de acordo com seu stepID*/
	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot=new ActionOrientedAbstraction(webdriver);
		actionBot.navigate(pageURL);
		
	}
	
}
