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
public class LRResolvedStateless implements WekaPredictionStatelessLocal {

	private String modelPath = "LinearRegression\\Linear_Regression_Resolved_Cases.model";
	private String arffDataSet = "Resolved_Cases_Prediction.arff";
	private int predictionIndex = 2;
	Classifier cls;
    /**
     * Default constructor. 
     */
    public LRResolvedStateless() {
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
	
	public String parsePredictionData(String phu, String active_cases, String deaths) {
		StringJoiner joiner = new StringJoiner(",");
		String phu_mod = "'"+phu+"'";
		joiner.add(phu_mod);
		joiner.add(active_cases);
		joiner.add("?");
		joiner.add(deaths);
		return joiner.toString();
	}

}
