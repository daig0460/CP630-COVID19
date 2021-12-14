package ec.project.ejb;

import javax.ejb.Local;
import weka.classifiers.Classifier;

@Local
public interface PredictionModelStatelessLocal {
	
	static String model_dir = "src\\main\\resources\\models\\";
	
	String predict(String predictionData);
	
	static Classifier loadModel(String modelPath) throws Exception {
		return (Classifier) weka.core.SerializationHelper.read(model_dir + modelPath);
	}

}
