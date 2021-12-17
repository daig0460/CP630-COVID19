package ec.project.ejb;

import java.io.File;
import java.util.StringJoiner;

import javax.ejb.Stateless;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
/**
 * Session Bean implementation class PredictionModelStateless
 */
@Stateless
public class RFPHUStateless implements WekaPredictionStatelessLocal {

	private String modelPath = "RandomForest\\Random_Forest_PHU.model";
	private String arffDataSet = "Random_Forest_PHU_Prediction.arff";
	private int predictionIndex = 1;
	Classifier cls;
    /**
     * Default constructor. 
     */
    public RFPHUStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String predict(String predictionData) {
		double value = -1;
		try {
			//Load the model
			cls = WekaPredictionStatelessLocal.loadModel(modelPath);
			
			//Create temp .arff file with user's input for prediction
			File tempArff = WekaPredictionStatelessLocal.copyDataSet(arffDataSet, predictionData);
			
			//Load the arff file
			Instances predicationDataSet = WekaPredictionStatelessLocal.loadDataSet(tempArff);
			predicationDataSet.setClassIndex(predictionIndex);
			
			//Make the prediction
			Instance predicationDataInstance = predicationDataSet.instance(predictionIndex);
			value = cls.classifyInstance(predicationDataInstance);
			
			//Format output
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Unable to make prediction, please try again.";
		}
		return String.valueOf(value);
	}
	
	public String parsePredictionData(String date, String active_cases, String resolved_cases, String deaths) {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(date);
		joiner.add("?");
		joiner.add(active_cases);
		joiner.add(resolved_cases);
		joiner.add(deaths);
		return joiner.toString();
	}

}
