package ec.ml.weka;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Random_Tree_PHU_Prediction {

	public static void main(String[] args) throws Exception {
		
		// Initialize a Random Forest instance for prediction of the PHU based on provided active, resolved, and death case counts and date
		RandomForest rf = (RandomForest) weka.core.SerializationHelper.read("src/main/resources/models/RandomForest/Random_Forest_PHU.model");
		
		// Prepare prediction instance input and specify the class (attribute to predict) - PHU
		Instances predictionDataSet = DataSource.read("src/main/resources/prediction/Random_Forest_PHU_Prediction.arff");
		predictionDataSet.setClassIndex(1);
		
		// label instances
	    for (int i = 0; i < predictionDataSet.numInstances(); i++) {
	      double clsLabel = rf.classifyInstance(predictionDataSet.instance(i));
	      predictionDataSet.instance(i).setClassValue(clsLabel);
	    }
		
	    // Output prediction
		System.out.println(predictionDataSet.toString());
	}

}
