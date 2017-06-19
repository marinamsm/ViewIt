package br.ufop.testmgr.api;

import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;

public interface IPerformanceTestingSchedule {

	public void setMain(Main main);
	
	public TestInput loadTestInputConfiguration();
	
	public void runPeriodically(List<String> pageName);

	public void readHarFilesFromDirectory(List<String> pageName);
	
	public TestSuite loadTestSuiteConfiguration(String testConfigPath); //FOR XML

	public void runPeriodically(long x_in_x_seconds, int y_times); //FOR XML

	public void readHarFilesFromDirectory(String harConfigPath); //FOR XML


}
