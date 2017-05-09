package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class PerformanceTestCase {

	protected StringProperty step = new SimpleStringProperty();
	
	protected StringProperty action = new SimpleStringProperty(); 
	
	ActionOrientedAbstraction actionBot;
	
	public abstract void executeTest(WebDriver webDriver);
	
	public StringProperty stepProperty() {
	        return step;
	}
	
	public StringProperty actionProperty() {
        return action;
	}
	
}
