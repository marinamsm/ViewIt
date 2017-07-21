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
	/**Description about this object action, e.g: click on search button*/
	/**Descrição sobre a ação deste objeto, ex: clique no botão de busca*/
	@XStreamAsAttribute
	private String description;

	/**Number of this action's step.
	 * If this is the first action you'll do after opening the page then it's step 1.
	 * As Navigatin step is automatically set to 1 when using the GUI, the others will be incremented by one. 
	 * If CheckingAlert is chosen as step 1, it is automatically changed to step 2, since Navigating is step 1.*/
	/**Número do passo dessa ação.
	 * Se este for o primeiro passo que fará ao abrir a página, então é o passo 1.
	 * Como a ação Navigating (abrir a página) é automaticamente configurada como o primeiro passo ao usar GUI, as outras ações são incrementadas em um.
	 * Se CheckingAlert for escolhido como passo 1, ele será automaticamente configurado com stepID 2 e Navigating 1.*/
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

	/**Runs this action (CheckingAlert) during the test according to its stepID*/
	/**Executa essa ação (CheckingAlert) durante o teste de acordo com seu stepID*/
	@Override
	public void executeTest(WebDriver webDriver) {
		actionBot = new ActionOrientedAbstraction(webDriver);
		actionBot.checkAlert(option);
	}
}
