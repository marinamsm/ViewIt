package br.ufop;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
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
import br.ufop.performance.model.TestSuite;
import br.ufop.performance.model.Typing;

@XmlSeeAlso({PerformanceTestCase.class, CheckingAlert.class, CheckingBoxes.class, Clicking.class, ContextClicking.class,Navigating.class, SelectingOption.class, Submitting.class, TestSuite.class, Typing.class})
@XmlRootElement(name="actions")
//@XmlSeeAlso(PerformanceTestCase.class)
public class TestScenarioListWrapper {

//	@XmlElements({
//        @XmlElement(name="click",type=Clicking.class),
//        @XmlElement(name="type",type=Typing.class),
//        @XmlElement(name="navigate",type=Navigating.class),
//        @XmlElement(name="suit",type=TestSuite.class),
//        @XmlElement(name="context",type=ContextClicking.class),
//        @XmlElement(name="check",type=CheckingAlert.class),
//        @XmlElement(name="checkBox",type=CheckingBoxes.class),
//        @XmlElement(name="select",type=SelectingOption.class),
//        @XmlElement(name="submit",type=Submitting.class),
//    })
//	@XmlElementWrapper(name="actions")
    private List<PerformanceTestCase> actions;

    @XmlElement(name = "action")
    public List<PerformanceTestCase> getActions() {
        return actions;
    }

    public void setActions(List<PerformanceTestCase> actions) {
        this.actions = actions;
    }
}
