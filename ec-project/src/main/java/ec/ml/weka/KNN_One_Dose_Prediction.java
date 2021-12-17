package ec.ml.weka;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.StringJoiner;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import static java.nio.file.StandardCopyOption.*;

public class KNN_One_Dose_Prediction {

	public static void main(String[] args) throws Exception {
		
		// Initialize a K-Nearest Neighbor classifier instance for prediction of the percentage of single dose vaccinated populations
		Classifier cls = (Classifier) weka.core.SerializationHelper.read("src/main/resources/models/KNN/K_Nearest_Neighbor_One_Dose_Vaccinated.model");
	
		File userArff = File.createTempFile("KNN_One_dose_Vaccinated_Prediction" + "_temp", ".arff", new File("src/main/resources/"));
		File mainArff = new File("src/main/resources/prediction/KNN_One_dose_Vaccinated_Prediction.arff");
		Files.copy(mainArff.toPath(), userArff.toPath(), REPLACE_EXISTING);

		FileWriter writer = new FileWriter(userArff, true);
		StringJoiner joiner = new StringJoiner(",");
		joiner.add("2021-12-12");
		joiner.add("'TORONTO'");
		joiner.add("'12-17yrs'");
		joiner.add("?");

		writer.write(joiner.toString());
		writer.flush();
		writer.close();
		// Prepare prediction instance input and specify the class (attribute to predict) - single dose percentage
		Instances predictionDataSet = DataSource.read(new FileInputStream(userArff));
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
		userArff.deleteOnExit();
	}

}
