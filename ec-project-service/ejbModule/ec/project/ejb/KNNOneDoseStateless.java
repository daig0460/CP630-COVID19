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
public class KNNOneDoseStateless implements WekaPredictionStatelessLocal {

	private String modelPath = "KNN\\K_Nearest_Neighbor_One_Dose_Vaccinated.model";
	private String arffDataSet = "KNN_One_Dose_Vaccinated_Prediction.arff";
	private int predictionIndex = 3;
	Classifier cls;
    /**
     * Default constructor. 
     */
    public KNNOneDoseStateless() {
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
	
	public String parsePredictionData(String date, String phu, String age) {
		StringJoiner joiner = new StringJoiner(",");
		String phu_mod = "'"+phu+"'";
		String age_mod = "'"+age+"'";
		joiner.add(date);
		joiner.add(phu_mod);
		joiner.add(age_mod);
		joiner.add("?");
		return joiner.toString();
	}

}
