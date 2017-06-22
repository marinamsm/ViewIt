package br.ufop;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import br.ufop.performance.model.CheckingAlert;
import br.ufop.performance.model.CheckingBoxes;
import br.ufop.performance.model.Clicking;
import br.ufop.performance.model.ContextClicking;
import br.ufop.performance.model.Navigating;
import br.ufop.performance.model.PerformanceTestCase;
import br.ufop.performance.model.SelectingOption;
import br.ufop.performance.model.Submitting;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;
import br.ufop.performance.model.Typing;

@XmlSeeAlso({PerformanceTestCase.class, CheckingAlert.class, CheckingBoxes.class, Clicking.class, ContextClicking.class,Navigating.class, SelectingOption.class, Submitting.class, TestSuite.class, Typing.class})
@XmlRootElement(name="actions")
public class TestScenarioListWrapper {
	
	private int x_times;
	private int y_interval;
	private String URL;
    private List<PerformanceTestCase> actions;

    @XmlElement(name = "action")
    public List<PerformanceTestCase> getActions() {
        return actions;
    }
    
    @XmlElement
    public int getX_times() {
    	return x_times;
    }
    
    @XmlElement
    public int getY_interval() {
    	return y_interval;
    }
    
    @XmlElement
    public String getURL() {
    	return URL;
    }
    
    public void setActions(List<PerformanceTestCase> actions) {
        this.actions = actions;
    }
    
    public void setX_times(int x_times){
    	this.x_times = x_times;
    }
    
    public void setY_interval(int y_interval){
    	this.y_interval = y_interval;
    }
    
    public void setURL(String URL) {
    	this.URL = URL;
    }
   
}
