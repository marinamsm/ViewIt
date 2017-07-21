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

	/**Number of this action's step.
	 * If this is the first action you'll do after opening the page then it's step 1.
	 * As Navigatin step is automatically set to 1 when using the GUI, the others will be incremented by one. 
	 * If CheckingBoxes is chosen as step 1, it is automatically changed to step 2, since Navigating is step 1.*/
	/**Número do passo dessa ação.
	 * Se este for o primeiro passo que fará ao abrir a página, então é o passo 1.
	 * Como a ação Navigating (abrir a página) é automaticamente configurada como o primeiro passo ao usar GUI, as outras ações são incrementadas em um.
	 * Se CheckingBoxes for escolhido como passo 1, ele será automaticamente configurado com stepID 2 e Navigating 1.*/
	@XStreamAsAttribute
	private String stepID;

	/**Description about this object action*/
	/**Descrição sobre a ação deste objeto*/
	@XStreamAsAttribute
	private String description;

	/**Type of locator to use in order to locate the element to be clicked in*/
	/**Tipo de locator usado para encontrar o elemento a ser clicado*/
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

	/**Runs this action (Clicking) during the test according to its stepID*/
	/**Executa essa ação (Clicking) durante o teste de acordo com seu stepID*/
	@Override
	public void executeTest(WebDriver webdriver) {
		actionBot = new ActionOrientedAbstraction(webdriver);
		actionBot.checkBoxes(locatorRadioForm.getByObject(
				locatorRadioForm.getBy(), locatorRadioForm.getValue()),
				visibleTexts);

	}

}
