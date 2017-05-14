package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
