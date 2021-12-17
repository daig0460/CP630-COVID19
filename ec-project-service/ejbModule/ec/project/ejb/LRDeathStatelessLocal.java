package ec.project.ejb;

import javax.ejb.Local;

@Local
public interface LRDeathStatelessLocal {
	
	String parsePredictionData(String phu, String active_cases, String resolved_cases);
	String predict(String predictionData);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
