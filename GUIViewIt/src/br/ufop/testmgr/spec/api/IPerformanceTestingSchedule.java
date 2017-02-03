package br.ufop.testmgr.spec.api;

import java.util.List;

import br.ufop.Main;
import br.ufop.performance.checker.testsuite.model.TestInput;

public interface IPerformanceTestingSchedule {

	public void setMain(Main main);
	
	public TestInput loadTestInputConfiguration();

	public void runPeriodically(List<String> pageName);

	public void readHarFilesFromDirectory(String harPath, String csvPath, List<String> pageName);

}
