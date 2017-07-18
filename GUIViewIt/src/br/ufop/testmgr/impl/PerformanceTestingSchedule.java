package br.ufop.testmgr.impl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.ufop.Main;
import br.ufop.harviewer.api.IHarViewer;
import br.ufop.harviewer.impl.HarViewerFactory;
import br.ufop.performance.api.IPerformanceTesting;
import br.ufop.performance.impl.PerformanceTestingFactory;
import br.ufop.performance.impl.PerformanceTestingFactory.ProvidedInterface;
import br.ufop.performance.model.TestInput;
import br.ufop.performance.model.TestSuite;
import br.ufop.testmgr.api.IPerformanceTestingSchedule;

class PerformanceTestingSchedule implements IPerformanceTestingSchedule {

	private Main main;
	
	private int y_times; // testes serao executados num total de tyimes vezes
	
	private long x_in_x_seconds; // cada teste de x em x minutos

	private Timer timer;
	
	private IPerformanceTesting testExecution;
	
	private TestInput testInput;
	
	private TestSuite testSuite;
	
	private String harPath;
	
	private String csvPath;
	
	private List<String> pageName;

	
	public void setMain(Main main) {
		if(this.main == null)
			this.main = main;
	}
	
	//GUI
	public TestInput loadTestInputConfiguration() {

		testExecution = PerformanceTestingFactory
				.createInstance(ProvidedInterface.ITESTSUITE);
		
		testExecution.setMain(main);
		
		testInput = testExecution.loadTestInput();
		return testInput;

	}
	//GUI
	public void runPeriodically(List<String> pageName) {
		this.loadTestInputConfiguration();
		this.y_times = testInput.getX_times();
		this.x_in_x_seconds = testInput.getY_interval();
		System.out.println("saveFlag " + main.getTestInput().getSaveFlag());
		if(main.getTestInput().getSaveFlag()) {
			this.harPath = testInput.getHarPath();
			this.csvPath = testInput.getCsvFolder();
		}
		else {
			this.harPath = testInput.getHarDirectoryPath();
			this.csvPath = testInput.getCsvDirectoryPath();
		} 
		System.out.println(this.harPath);
		System.out.println(this.csvPath);
		this.pageName = pageName; //just "Home" for now (testing)
		timer = new Timer();
		
		timer.schedule(new Reminder(),
				0, // initial delay
				this.x_in_x_seconds);//intervalo entre execuções
	}

	class Reminder extends TimerTask {
		int numExecutions = y_times;

		public void run() {
			if (numExecutions > 0) {
				testExecution.run();
				numExecutions--;
			} else {
				System.out.println("Fim da execucao do teste!");
				readHarFilesFromDirectory(pageName);
				System.exit(0);
//				this.cancel();
			}
		}
	}
	
	@Override
	public void readHarFilesFromDirectory(List <String> pageName) {

		IHarViewer harviewer = (IHarViewer) HarViewerFactory
				.createInstance(HarViewerFactory.IHARVIEWER);
		System.out.println(this.harPath);
		System.out.println(this.csvPath);
		System.out.println(pageName.get(0));
		harviewer.exportHarFilesToCSVFile(harPath, csvPath, pageName);
	}
	
	//Below are methods for XML
	public TestSuite loadTestSuiteConfiguration(String testConfigPath) {

		testExecution = PerformanceTestingFactory
				.createInstance(ProvidedInterface.ITESTSUITE);

		testSuite = testExecution.loadTestSuite(testConfigPath);
		return testSuite;

	}
	
	public void runPeriodically(long x_in_x_seconds, int y_times) {
		this.y_times = y_times;
		this.x_in_x_seconds = x_in_x_seconds;
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, // initial delay
				this.x_in_x_seconds);
	}

	class RemindTask extends TimerTask {
		int numExecutions = y_times;

		public void run() {

			if (numExecutions > 0) {
				testExecution.run();
				numExecutions--;
			} else {

				System.exit(0);
			}
		}
	}

	@Override
	public void readHarFilesFromDirectory(String harConfigPath) {

		IHarViewer harviewer = (IHarViewer) HarViewerFactory
				.createInstance(HarViewerFactory.IHARVIEWER);

		harviewer.exportHarFilesToCSVFile(harConfigPath);
	}
}
