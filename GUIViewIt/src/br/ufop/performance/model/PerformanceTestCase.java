package br.ufop.performance.model;

import org.openqa.selenium.WebDriver;

import br.ufop.performance.action.impl.ActionOrientedAbstraction;

public abstract class PerformanceTestCase {
	
	ActionOrientedAbstraction actionBot;
	
	public abstract void executeTest(WebDriver webDriver);

	
}
