package br.ufop.performance.action.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import br.ufop.performance.action.api.IActionOrientedAbstraction;

/*
 * A "bot" - Bot Style Tests is an action-oriented abstraction over the raw
 * Selenium APIs.
 */
public class ActionOrientedAbstraction implements IActionOrientedAbstraction {

	private final int sleepTime = 3000;
	private final int waitTime = 20; 

	private final WebDriver driver;

	public ActionOrientedAbstraction(WebDriver driver) {
		this.driver = driver;
	}
	
	public void click(By locator) {
		try {
			// esperar ate firebox carregar
		//	Thread.sleep(sleepTime);

			driver.findElement(locator).click();
			// esperar ate aquivo HAR ser exportado
			// (ref:http://www.softwareishard.com/blog/firebug/automate-page-load-performance-testing-with-firebug-and-selenium/)
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			Thread.sleep(sleepTime);

			// esperar ate que o har seja exportado - caso abra nova pagina
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void submit(By locator) {
		try {
			driver.findElement(locator).submit();
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void navigate(String URL) {

		try {
			// Thread.sleep(sleepTime);

			// carregar pagina de teste
			driver.get(URL);
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

			
			
			// esperar ateh que o Har seja exportado (Har sempre eh exportado
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void checkBoxes(By locatorCheckBox, List<String> selectedVisibleTexts) {

		Select select = new Select(driver.findElement(locatorCheckBox));
		for (String option : selectedVisibleTexts) {
			select.selectByVisibleText(option);
		}

	}

	public void selectOptionInRadioForm(By locatorRadioForm,
			String selectedVisibleText) {
		if(locatorRadioForm == null){
			System.out.println("O erro eh aqui");
		}
		
		Select sel = new Select(driver.findElement(locatorRadioForm));
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
		sel.selectByVisibleText(selectedVisibleText);

	}

	public void type(By locator, String text) {

		try {
			WebElement element = driver.findElement(locator);
			element.clear();
			element.sendKeys(text);
			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			Thread.sleep(sleepTime);
		}
		catch(Exception e) {
			System.err.println(e.toString());
		}
		

		
	}
	
	public void checkAlert(String option){
		try{
			Alert alert = driver.switchTo().alert();
			if(option.equals("accept")){
				alert.accept();
			}else{
				alert.dismiss();
			}
		}catch(Exception e){
			System.err.println(e.toString());
			System.exit(0);
		}
	}
	
	public void contextClicking(By locator, String name){
		WebElement webelement = this.driver.findElement(locator);
		Actions actions = new Actions(driver);
		//actions.contextClick(webelement).click(driver.findElement(By.name(name))).build().perform();
		actions.contextClick(webelement);
		for(int i = 0;i < Integer.parseInt(name);i++)
			actions.sendKeys(Keys.ARROW_DOWN);
		actions.sendKeys(Keys.RETURN);
		actions.build().perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

}
