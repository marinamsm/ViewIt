package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XStreamAlias("selectingOption")
//@XmlRootElement(name="select")
public class SelectingOption extends PerformanceTestCase {

	/**Description about this object action, e.g: select the second option*/
	/**Descrição sobre a ação deste objeto, ex: selecionar a segunda opção*/
	@XStreamAsAttribute
	private String description;

	/**Number of this action's step.
	 * If this is the first action you'll do after opening the page then it's step 1.
	 * As Navigatin step is automatically set to 1 when using the GUI, the others will be incremented by one. 
	 * If Selecting Option is chosen as step 1, it is automatically changed to step 2, since Navigating is step 1.*/
	/**Número do passo dessa ação.
	 * Se este for o primeiro passo que fará ao abrir a página, então é o passo 1.
	 * Como a ação Navigating (abrir a página) é automaticamente configurada como o primeiro passo ao usar GUI, as outras ações são incrementadas em um.
	 * Se SelectingOption for escolhido como passo 1, ele será automaticamente configurado com stepID 2 e Navigating 1.*/
	@XStreamAsAttribute
	private String stepID;
	/**Type of locator to use in order to locate the element to be clicked in*/
	/**Tipo de locator usado para encontrar o elemento a ser clicado*/
	@XStreamAlias("locatorCheckBoxes")
	private ByLocator locatorCheckBoxes;

	@XStreamAlias("selectedElement_visibleText")
	private String visibleText;
	
	//FOR GUI
	private StringProperty action = new SimpleStringProperty("SelectingOption");

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

	public ByLocator getLocator() {
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
	
	/**Runs this action (SelectingOption) during the test according to its stepID*/
	/**Executa essa ação (SelectingOption) durante o teste de acordo com seu stepID*/
	@Override
	public void executeTest(WebDriver webdriver) {

		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.selectOptionInRadioForm(locatorCheckBoxes.getByObject(
				locatorCheckBoxes.getBy(), locatorCheckBoxes.getValue()),
				visibleText);

	}
}
