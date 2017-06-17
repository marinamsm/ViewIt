package br.ufop.chartgenerator.impl;

import br.ufop.chartgenerator.api.IChartGenerator;
import br.ufop.chartgenerator.data.api.ICsvPreview;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory;
import br.ufop.chartgenerator.data.impl.CsvPreviewFactory.ProvidedInterface;
import br.ufop.chartgenerator.model.ChartSuite;
import utils.guiflags.GUIFlag;

class ChartGenerator implements IChartGenerator{
	
	private ChartSuite chartSuite;
	
	private ICsvPreview csvPreview;
	
	//FOR GUI
	private static boolean GUI; 
	
	//FOR GUI
	private static String csvPath; 
	
	@Override
	public ChartSuite loadChartSuite(String chartSuitePath) {
		chartSuite = ChartSuiteConfigLoader.loadChartSuiteConfiguration(chartSuitePath);
		chartSuite.addChartsToSetConfig();
		/*
		csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		csvPreview.readCsvFile(chartSuite.getCsvPath());
		
		*/
		return chartSuite;
	}

	@Override
	public void run() {
		
		configCsvPreview();
		if(GUIFlag.GUI)//is you are using ViewIt with GUI
			GUIFlag.chartSuite.plotChart(csvPreview);
		else
			chartSuite.plotChart(csvPreview);
		
	}
	
	public void configCsvPreview(){
		csvPreview = (ICsvPreview)CsvPreviewFactory
				 .createInstance(ProvidedInterface.ICSVPREVIEW);
		if(GUIFlag.GUI)
			csvPreview.readCsvFile(GUIFlag.csvPathForChart);
		else
			csvPreview.readCsvFile(chartSuite.getCsvPath());
	}
}
