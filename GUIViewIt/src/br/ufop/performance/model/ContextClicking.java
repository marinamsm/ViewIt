package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("contextClicking")
//@XmlRootElement(name="context")
public class ContextClicking extends PerformanceTestCase{
	
	@XStreamAlias("locator")
	private ByLocator locator;

	@XStreamAsAttribute
	private String description;

	@XStreamAsAttribute
	private String stepID;
	
	@XStreamAsAttribute
	private String name;
	
	//FOR GUI
	private StringProperty action = new SimpleStringProperty("ContextClicking");
	
	public StringProperty getAction() {
		return action;
	}
	
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

	/**Description about this object action.
	*Descrição sobre a ação deste objeto.*/
	public String getDescription() {
		return description;
	}

	/**Description about this object action.
	*Descrição sobre a ação deste objeto.*/
	public void setDescription(String description) {
		this.description = description;
	}

	/**Number of this action's step.
	 * This step is automatically set to 1 when using GUI.
	 *Número do passo dessa ação.
	 * Este passp é automaticamente configurado como 1 ao usar GUI.*/
	public String getStepID() {
		return stepID;
	}

	/**Number of this action's step.
	 * This step is automatically set to 1 when using GUI.
	 *Número do passo dessa ação.
	 * Este passp é automaticamente configurado como 1 ao usar GUI.*/
	public void setStepID(String stepID) {
		this.stepID = stepID;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	/**Runs this action (ContextClicking) during the test according to its stepID.
	*Executa essa ação (ContextClicking) durante o teste de acordo com seu stepID.*/
	@Override
	public void executeTest(WebDriver webDriver) {
		this.actionBot = new ActionOrientedAbstraction(webDriver);
		actionBot.contextClicking(locator.getByObject(locator.getBy(),locator.getValue()), this.name);
	}
	
}
