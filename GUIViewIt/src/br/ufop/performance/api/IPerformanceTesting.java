package br.ufop.performance.api;

import br.ufop.Main;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;

public interface IPerformanceTesting {

	public void setMain(Main main);
	
	public TestInput loadTestInput();
	
	public TestSuite loadTestSuite();

	public void run();

}
