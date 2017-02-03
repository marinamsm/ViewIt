package br.ufop.performance.checker.testsuite.model;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.openqa.selenium.WebDriver;

import br.ufop.Main;

public class TestSuite extends PerformanceTestCase {

	
	private SortedMap<String, PerformanceTestCase> mapStepId_PerformanceTest;

	private String description;
	
	private String harPath; //folder to store .har files
	
	private List<Navigating> navigatingSteps;

	private List<Typing> typingSteps;

	private List<CheckingBoxes> checkingBoxSteps;

	private List<SelectingOption> selectingOptionSteps;

	private List<Clicking> clickingSteps;

	private List<Submitting> submittingSteps;
	
	private List<CheckingAlert> checkingAlertSteps;
	
	private List<ContextClicking> contextclickingSteps;
	
	public TestSuite() {}	
	
	public void initNavigatingList() {
		navigatingSteps = new LinkedList<Navigating>();
	}
	
	public void initTypingList() {
		typingSteps = new LinkedList<Typing>();
	}
	
	public void initCheckingBoxList() {
		checkingBoxSteps = new LinkedList<CheckingBoxes>();
	}
	
	public void initSelectingOptionList() {
		selectingOptionSteps = new LinkedList<SelectingOption>();
	}
	
	public void initClickingList() {
		clickingSteps = new LinkedList<Clicking>();
	}
	
	public void initSubmittingList() {
		submittingSteps = new LinkedList<Submitting>();
	}
	
	public void initCheckingAlertList() {
		checkingAlertSteps = new LinkedList<CheckingAlert>();
	}
	
	public void initContextClickingList() {
		contextclickingSteps = new LinkedList<ContextClicking>();
	}
	
	
	public List<Navigating> getNavigatingSteps() {
		return navigatingSteps;
	}

	public void setNavigatingSteps(List<Navigating> navigatingSteps) {
		this.navigatingSteps = navigatingSteps;
	}

	public List<Typing> getTypingSteps() {
		return typingSteps;
	}

	public void setTypingSteps(List<Typing> typingSteps) {
		this.typingSteps = typingSteps;
	}

	public List<CheckingBoxes> getCheckingBoxSteps() {
		return checkingBoxSteps;
	}

	public void setCheckingBoxSteps(List<CheckingBoxes> checkingBoxSteps) {
		this.checkingBoxSteps = checkingBoxSteps;
	}

	public List<SelectingOption> getSelectingOptionSteps() {
		return selectingOptionSteps;
	}

	public void setSelectingOptionSteps(List<SelectingOption> selectingOptionSteps) {
		this.selectingOptionSteps = selectingOptionSteps;
	}

	public List<Clicking> getclickingStepss() {
		return clickingSteps;
	}

	public void setclickingStepss(List<Clicking> clickingSteps) {
		this.clickingSteps = clickingSteps;
	}

	public List<Submitting> getSubmittingSteps() {
		return submittingSteps;
	}

	public void setSubmittingSteps(List<Submitting> submittingSteps) {
		this.submittingSteps = submittingSteps;
	}

	public List<CheckingAlert> getCheckingAlertSteps() {
		return checkingAlertSteps;
	}

	public void setCheckingAlertSteps(List<CheckingAlert> checkingAlertSteps) {
		this.checkingAlertSteps = checkingAlertSteps;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setHarPath(String harPath) {
		this.harPath = harPath;
	}

	public SortedMap<String, PerformanceTestCase> getMapStepId_PerformanceTest() {
		return mapStepId_PerformanceTest;
	}

	public void setMapStepId_PerformanceTest(
			SortedMap<String, PerformanceTestCase> mapStepId_PerformanceTest) {
		this.mapStepId_PerformanceTest = mapStepId_PerformanceTest;
	}

	public List<Clicking> getclickingSteps() {
		return clickingSteps;
	}

	public void setclickingSteps(List<Clicking> clickingSteps) {
		this.clickingSteps = clickingSteps;
	}
	
	
	
	public List<ContextClicking> getContextclickingStepss() {
		return contextclickingSteps;
	}

	public void setContextclickingStepss(List<ContextClicking> contextclickingSteps) {
		this.contextclickingSteps = contextclickingSteps;
	}

	public void sortTestCasesByStepId() {
		if (mapStepId_PerformanceTest == null)
			mapStepId_PerformanceTest = new TreeMap<String, PerformanceTestCase>();

		// buscando testes do tipo navegacao
		if (navigatingSteps != null)
			for (Navigating navigate : navigatingSteps) {
				if (mapStepId_PerformanceTest == null)
					System.out.println("NULLLL");
				mapStepId_PerformanceTest.put(navigate.getStepID(), navigate);
			}

		// testes do tipo Clicar
		if (clickingSteps != null)
			for (Clicking click : clickingSteps) {
				mapStepId_PerformanceTest.put(click.getStepID(), click);
			}
		// do tipo selecionar
		if (selectingOptionSteps != null)
			for (SelectingOption select : selectingOptionSteps) {
				mapStepId_PerformanceTest.put(select.getStepID(), select);
			}

		// do tipo checkBoxes
		if (checkingBoxSteps != null)
			for (CheckingBoxes check : checkingBoxSteps) {
				mapStepId_PerformanceTest.put(check.getStepID(), check);
			}
		// tipo submeter
		if (submittingSteps != null)
			for (Submitting submit : submittingSteps) {
				mapStepId_PerformanceTest.put(submit.getStepID(), submit);
			}
		// digitar
		if (typingSteps != null)
			for (Typing typingStep : typingSteps) {
				mapStepId_PerformanceTest.put(typingStep.getStepID(), typingStep);
			}
		//alert
		if(checkingAlertSteps != null)
			for(CheckingAlert alert : checkingAlertSteps){
				mapStepId_PerformanceTest.put(alert.getStepID(), alert);
			}
		//contextClicking
		if(contextclickingSteps != null)
			for(ContextClicking contextClicking : contextclickingSteps){
				mapStepId_PerformanceTest.put(contextClicking.getStepID(), contextClicking);
			}
	}

	
	public String getHarDirectoryPath() {
		
		String canonicalPath = null;
		try {
			canonicalPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// determinar o diretório
		String defaulLogDir = canonicalPath + File.separator + harPath;
		return defaulLogDir;
	}

	@Override
	public void executeTest(WebDriver webDriver) {
		Iterator<String> iterator = mapStepId_PerformanceTest.keySet()
				.iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			PerformanceTestCase testCase = mapStepId_PerformanceTest.get(key);
			testCase.executeTest(webDriver);

		}

	}

}
