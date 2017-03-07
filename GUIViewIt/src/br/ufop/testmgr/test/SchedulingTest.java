package br.ufop.testmgr.test;

import br.ufop.Main;
import br.ufop.testmgr.api.IPerformanceTestingSchedule;
import br.ufop.testmgr.impl.TestMgrFactory;
import br.ufop.testmgr.impl.TestMgrFactory.TestMgrProvidedInterface;

public class SchedulingTest {

	/**
	 * @param args
	 */
	private Main main;
	
	//acessa API para ter acesso ao PerformanceTestingSchedule
	IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
			.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	
	public SchedulingTest(Main main) {
		this.main = main;
	}
	
	public static void main(String args[]) {
		int y_times = 1; // testes serao executados num total de 1
		// vezes
		long x_in_x_seconds = 2 * 60 * 1000; // cada teste inicia
		IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
				.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
		
			
		testSchedule
				.loadTestSuiteConfiguration("D:\\ViewItGUIRepo\\GUIViewIt\\src\\br\\ufop\\testmgr\\executionconfig\\testGoogle.xml");
		
		testSchedule.runPeriodically(x_in_x_seconds, y_times);
		
		
		/*	
		testSchedule
			.readHarFilesFromDirectory(
				"src/br/ufop/testmgr/executionconfig/testeBlazerHar.xml");
		*/

	}
}	
