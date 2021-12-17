package ec.project.ejb;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.ejb.Local;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

@Local
public interface WekaPredictionStatelessLocal {
	
	static String model_dir = "resources\\models\\";
	static String data_dir = "resources\\prediction\\";
	
	String predict(String predictionData);
	
	static Classifier loadModel(String modelPath) throws Exception {
		return (Classifier) weka.core.SerializationHelper.read(model_dir + modelPath);
	}
	
	static File copyDataSet(String dataSetPath, String data) throws IOException {
		String newFileName = dataSetPath.substring(0, dataSetPath.lastIndexOf("."));
		File tempArff = File.createTempFile(newFileName + "_temp", ".arff", new File("resources/"));
		tempArff.deleteOnExit();
		File mainArff = new File(data_dir + dataSetPath);
		Files.copy(mainArff.toPath(), tempArff.toPath(), REPLACE_EXISTING);
		FileWriter writer = new FileWriter(tempArff, true);
		writer.write(data);
		writer.flush();
		writer.close();
		return tempArff;
	}
	
	static Instances loadDataSet(File dataSet) throws Exception {
		return DataSource.read(new FileInputStream(dataSet));
	}

}
