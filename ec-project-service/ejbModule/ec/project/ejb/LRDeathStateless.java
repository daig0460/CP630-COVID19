package ec.project.ejb;

import javax.ejb.Stateless;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
/**
 * Session Bean implementation class PredictionModelStateless
 */
@Stateless
public class LRDeathStateless implements PredictionModelStatelessLocal {

	private String modelPath = "LinearRegression\\Linear_Regression_Death.model";
	Classifier cls;
    /**
     * Default constructor. 
     */
    public LRDeathStateless() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String predict(String predictionData) {
		double value = -1;
		try {
			//Load the model
			cls = PredictionModelStatelessLocal.loadModel(modelPath);
			
			//Load the arff file? TODO
			Instances predicationDataSet = null;
			
			//Parse the input data for prediction TODO
			String formatedData = parsePredictionData(predictionData); 
			
			//Make the prediction
			Instance predicationDataInstance = predicationDataSet.lastInstance();
			value = cls.classifyInstance(predicationDataInstance);
			
			//Format output
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Unable to make prediction, please try again.";
		}
		return String.valueOf(value);
	}
	
	private String parsePredictionData(String predictionData) {
		return null;
	}

}
