package br.ufop.performance.model;

import org.openqa.selenium.By;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("locator")
public class ByLocator {

	@XStreamAsAttribute
	private ByTypes by;

	@XStreamAlias("value")
	private String value;

	public ByLocator(){
	}
	
	public ByTypes getBy() {
		return by;
	}
	
	//FOR GUI
	public void setByFromString(String by) {
		switch (by) {
		case "ById":
			setBy(ByTypes.ById);
			break;
		case "ByClassName":
			setBy(ByTypes.ByClassName);
			break;
		case "ByCssSelector":
			setBy(ByTypes.ByCssSelector);
			break;
		case "ByLinkText":
			setBy(ByTypes.ByLinkText);
			break;
		case "ByName":
			setBy(ByTypes.ByName);
			break;
		case "ByPartialLinkText":
			setBy(ByTypes.ByPartialLinkText);
			break;
		case "ByTagName":
			setBy(ByTypes.ByTagName);
			break;
		case "ByXPath":
			setBy(ByTypes.ByXPath);
			break;
		default:
			setBy(ByTypes.ByXPath);
		}
	}
	
	//FOR XML ONLY
	public void setBy(ByTypes by) {
		this.by = by;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public By getByObject(ByTypes by, String value) {

		switch (by) {
		case ById:

			return By.id(value);
		case ByClassName:
			return By.className(value);

		case ByCssSelector:
			return By.cssSelector(value);
		case ByLinkText:
			return By.linkText(value);
		case ByName:
			return By.name(value);
		case ByPartialLinkText:
			return By.partialLinkText(value);
		case ByTagName:
			return By.tagName(value);
		case ByXPath:
			return By.xpath(value);
		default:
			return null;
		}

	}

	public enum ByTypes {
		ByClassName, ByCssSelector, ById, ByLinkText, ByName, ByPartialLinkText, ByTagName, ByXPath

	}

}
