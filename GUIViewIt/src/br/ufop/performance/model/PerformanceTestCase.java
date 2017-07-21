package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlSeeAlso({CheckingAlert.class, CheckingBoxes.class, Clicking.class, ContextClicking.class,Navigating.class, SelectingOption.class, Submitting.class, TestSuite.class, Typing.class})
public abstract class PerformanceTestCase {

	/**Used in actions view*/
	/**Usado na tela de ações*/
	protected StringProperty stepProperty = new SimpleStringProperty();
	
	/**Used in actions view*/
	/**Usado na tela de ações*/
	protected StringProperty actionProperty = new SimpleStringProperty(); 
	
	/**Responsible for automating the tests*/
	/**Responsável por automatizar os testes*/
	ActionOrientedAbstraction actionBot;
	
	/**Runs the tests according to the action.
	 * It's overridden in the derived classes.*/
	/**Usado na tela de ações*/
	public abstract void executeTest(WebDriver webDriver);
	
	public StringProperty stepProperty() {
	        return stepProperty;
	}
	
	public StringProperty actionProperty() {
        return actionProperty;
	}
	
}
