package br.ufop.testmgr.test;

import java.util.LinkedList;
import java.util.List;

import br.ufop.Main;
import br.ufop.performance.model.TestInput;
import br.ufop.testmgr.api.IPerformanceTestingSchedule;
import br.ufop.testmgr.impl.TestMgrFactory;
import br.ufop.testmgr.impl.TestMgrFactory.TestMgrProvidedInterface;

public class SchedulingTest implements Runnable{

	/**
	 * @param args
	 */
	private Main main;
	
	private List <String> pageName = new LinkedList<String>();
	
	private List<TestInput> cenarios = new LinkedList<TestInput>();
	
	
	//acessa API para ter acesso ao PerformanceTestingSchedule
	IPerformanceTestingSchedule testSchedule = (IPerformanceTestingSchedule) TestMgrFactory
			.createInstance(TestMgrProvidedInterface.IPERFORMANCETESTINGSCHEDULE);
	
	public SchedulingTest(Main main) {
		this.main = main;
	}
	
	public void test() {
		testSchedule.setMain(main);
		this.run();
	}
	/*
	public static void main(String[] args) {
		SchedulingTest t = new SchedulingTest();
        t.run();
	}*/
	
	public void run() {
		pageName.add("Home");
		//do the tests and save the results in .har files
		testSchedule.runPeriodically(pageName);
		
		//convert .har files to .csv
        /*synchronized(testSchedule){
        	try {
                Thread.sleep(0);
                testSchedule
                .readHarFilesFromDirectory(harPath, test, pageName);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
	}
	
	public void addCenario(TestInput cenario) {
		cenarios.add(cenario);
	}
	
	public List<TestInput> getCenarios() {
		return cenarios;
	}
	
	public void setCenarios (List<TestInput> cenarios) {
		this.cenarios = cenarios;
	}
}
