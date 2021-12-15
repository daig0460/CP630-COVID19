package ec.ml.weka;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Linear_Regression_Model_Resolved_Cases_Prediction {

	public static void main(String[] args) throws Exception {
		
		// Initialize a Linear Regression instance for prediction of the number of resolved cases
		LinearRegression cls = (LinearRegression) weka.core.SerializationHelper.read("src/main/resources/models/LinearRegression/Linear_Regression_Resolved_Cases.model");
		
		// Prepare prediction instance input and specify the class (attribute to predict) - resolved case count
		Instances predictionDataSet = DataSource.read("src/main/resources/prediction/Resolved_Cases_Prediction.arff");
		predictionDataSet.setClassIndex(2);	
		
		//Instance predicationDataInstance = predictionDataSet.lastInstance();
		//double value = cls.classifyInstance(predicationDataInstance);
		//System.out.println(value);
		
		// label instances
	    for (int i = 0; i < predictionDataSet.numInstances(); i++) {
	      double clsLabel = cls.classifyInstance(predictionDataSet.instance(i));
	      predictionDataSet.instance(i).setClassValue((int)clsLabel);
	    }
	    
	    // Output prediction
	    System.out.println(predictionDataSet.toString());
	}

}
