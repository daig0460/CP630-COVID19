package ec.ml.weka;

import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class KNN_One_Dose_Prediction {

	public static void main(String[] args) throws Exception {
		
		// Initialize a K-Nearest Neighbor classifier instance for prediction of the percentage of single dose vaccinated populations
		IBk cls = (IBk) weka.core.SerializationHelper.read("src/main/resources/models/KNN/K_Nearest_Neighbor_One_Dose_Vaccinated.model");
		
		// Prepare prediction instance input and specify the class (attribute to predict) - single dose percentage
		Instances predictionDataSet = DataSource.read("src/main/resources/prediction/KNN_One_dose_Vaccinated_Prediction.arff");
		predictionDataSet.setClassIndex(3);	
		
		//double[] value = cls.distributionForInstance(predictionDataSet.firstInstance());
		//System.out.println(value[0]);
		
		// label instances
	    for (int i = 0; i < predictionDataSet.numInstances(); i++) {
	      double clsLabel = cls.classifyInstance(predictionDataSet.instance(i));
	      predictionDataSet.instance(i).setClassValue(clsLabel);
	    }
	    
	    // Output prediction
		System.out.println(predictionDataSet.toString());
	}

}
