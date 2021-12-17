package ec.project.ejb;

import javax.ejb.Local;

@Local
public interface KNNFullyStatelessLocal {
	
	String parsePredictionData(String date, String phu, String age);
	String predict(String predictionData);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
