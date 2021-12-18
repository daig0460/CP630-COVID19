package ec.project.ejb;

import java.io.File;

import javax.ejb.Local;

@Local
public interface LRResolvedStatelessLocal {
	
	String parsePredictionData(String phu, String active_cases, String deaths);
	String predict(File predictionArff);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
