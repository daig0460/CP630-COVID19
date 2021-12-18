package ec.project.ejb;

import java.io.File;

import javax.ejb.Local;

@Local
public interface KNNOneDoseStatelessLocal {
	
	String parsePredictionData(String date, String phu, String age);
	String predict(File predictionArff);
//	Classifier loadModel() throws Exception;
//	File copyDataSet(String data) throws IOException;
//	Instances loadDataSet(File dataSet) throws Exception;

}
