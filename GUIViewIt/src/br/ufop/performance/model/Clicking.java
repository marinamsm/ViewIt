package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("clicking")
//@XmlRootElement(name="click")
public class Clicking extends PerformanceTestCase {
	
	@XStreamAlias("locator")
	private ByLocator locator;
	/**Description about this object action, e.g: click on search button*/
	/**Descrição sobre a ação deste objeto, ex: clique no botão de busca*/
	@XStreamAsAttribute
	private String description;
	
	/**Number of this action's step.
	 * If this is the first action you'll do after opening the page then it's step 1.
	 * As Navigatin step is automatically set to 1 when using the GUI, the others will be incremented by one. 
	 * If Clicking is chosen as step 1, it is automatically changed to step 2, since Navigating is step 1.*/
	/**Número do passo dessa ação.
	 * Se este for o primeiro passo que fará ao abrir a página, então é o passo 1.
	 * Como a ação Navigating (abrir a página) é automaticamente configurada como o primeiro passo ao usar GUI, as outras ações são incrementadas em um.
	 * Se Clicking for escolhido como passo 1, ele será automaticamente configurado com stepID 2 e Navigating 1.*/
	@XStreamAsAttribute
	private String stepID;

	//FOR GUI
	/**Used in the actions view*/
	/**Usado na tela de ações*/
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

	/**Type of locator to use in order to locate the element to be clicked in
	*Tipo de locator usado para encontrar o elemento a ser clicado*/
	public ByLocator getLocator() {
		return locator;
	}
	
	/**FOR XML ONLY*/
	/**Type of locator to use in order to locate the element on the page.
	*Tipo de locator usado para encontrar o elemento na página.*/
	public void setLocator(ByLocator locator) {
		this.locator = locator;
	}
	
	/**FOR GUI ONLY*/
	/**Type of locator to use in order to locate the element on the page.
	 * @param {string} by, {string} value
	*Tipo de locator usado para encontrar o elemento na página.*/
	public void setLocatorGUI(String by, String value) {
		locator = new ByLocator();
		locator.setByFromString(by);
		locator.setValue(value);
	}
	
	/**Runs this action (Clicking) during the test according to its stepID*/
	/**Executa essa ação (Clicking) durante o teste de acordo com seu stepID*/
	@Override
	public void executeTest(WebDriver webdriver) {

		 this.actionBot = new ActionOrientedAbstraction(webdriver);
		 try {
			 actionBot.click(locator.getByObject(locator.getBy(),
			 		 locator.getValue()));
		 }
		 catch(Exception e) {
			 System.out.println("EXCEPTION IN CLICKING");
			 e.printStackTrace();
		 }
		 	
	}

}
