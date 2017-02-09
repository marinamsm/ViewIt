package br.ufop.testmgr.api;

import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;

public interface IPerformanceTestingSchedule {

	public void setMain(Main main);
	
	public TestInput loadTestInputConfiguration();
	
	public TestSuite loadTestSuiteConfiguration();

	public void runPeriodically(List<String> pageName);

	public void readHarFilesFromDirectory(String harPath, String csvPath, List<String> pageName);

}
