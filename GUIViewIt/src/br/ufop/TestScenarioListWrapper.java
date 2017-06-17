package br.ufop;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import br.ufop.performance.model.PerformanceTestCase;


public class TestScenarioListWrapper {

    private List<PerformanceTestCase> actions;

    @XmlElement(name = "action")
    public List<PerformanceTestCase> getActions() {
        return actions;
    }

    public void setActions(List<PerformanceTestCase> actions) {
        this.actions = actions;
    }
}
