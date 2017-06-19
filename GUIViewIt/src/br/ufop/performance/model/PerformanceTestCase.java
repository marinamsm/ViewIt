package br.ufop.performance.model;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlSeeAlso({CheckingAlert.class, CheckingBoxes.class, Clicking.class, ContextClicking.class,Navigating.class, SelectingOption.class, Submitting.class, TestSuite.class, Typing.class})
//@XmlTransient
public abstract class PerformanceTestCase {

	protected StringProperty stepProperty = new SimpleStringProperty();
	
	protected StringProperty actionProperty = new SimpleStringProperty(); 
	
	ActionOrientedAbstraction actionBot;
	
	public abstract void executeTest(WebDriver webDriver);
	
	public StringProperty stepProperty() {
	        return stepProperty;
	}
	
	public StringProperty actionProperty() {
        return actionProperty;
	}
	
}
