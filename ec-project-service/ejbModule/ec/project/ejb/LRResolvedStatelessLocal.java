package ec.project.ejb;

import javax.ejb.Local;

@Local
public interface LRResolvedStatelessLocal {
	
	String parsePredictionData(String phu, String active_cases, String deaths);
	String predict(String predictionData);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
