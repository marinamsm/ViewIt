package br.ufop.performance.checker.testsuite.api;

import br.ufop.Main;
import br.ufop.performance.checker.testsuite.model.TestInput;

public interface IPerformanceTesting {

	public void setMain(Main main);
	
	public TestInput loadTestInput();

	public void run();

}
