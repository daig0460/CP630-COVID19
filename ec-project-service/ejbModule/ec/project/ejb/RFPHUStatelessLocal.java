package ec.project.ejb;

import javax.ejb.Local;

@Local
public interface RFPHUStatelessLocal {
	
	String parsePredictionData(String date, String active_cases, String resolved_cases, String deaths);
	String predict(String predictionData);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
