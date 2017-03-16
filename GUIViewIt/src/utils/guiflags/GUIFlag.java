package utils.guiflags;

import br.ufop.Main;
import br.ufop.chartgenerator.model.ChartSuite;

public class GUIFlag {
	public static boolean GUI;
	
	public static String csvPathForChart;
	
	public static ChartSuite chartSuite;
	
	public Main main;
	
	public GUIFlag(boolean GUI, String csvPath, ChartSuite chartSuite){
		this.GUI = GUI;
		csvPathForChart = csvPath;
		this.chartSuite = chartSuite; 
	}
}
