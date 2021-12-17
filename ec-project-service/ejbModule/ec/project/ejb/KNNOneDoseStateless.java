package ec.project.ejb;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringJoiner;

import javax.ejb.Stateless;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
/**
 * Session Bean implementation class PredictionModelStateless
 */
@Stateless
public class KNNOneDoseStateless implements KNNOneDoseStatelessLocal {

	private String data_dir = "resources\\prediction\\";
	private String modelPath = "resources\\models\\KNN\\K_Nearest_Neighbor_One_Dose_Vaccinated.model";
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
	
	@Override
	public String predict(String predictionData) {
		double value = -1;
		try {
			//Load the model
			cls = loadModel();
			
			//Create temp .arff file with user's input for prediction
			File tempArff = copyDataSet(predictionData);
			
			//Load the arff file
			Instances predictionDataSet = loadDataSet(tempArff);
			predictionDataSet.setClassIndex(predictionIndex);
			
			//Make the prediction
			Instance predictionDataInstance = predictionDataSet.instance(predictionIndex);
			value = cls.classifyInstance(predictionDataInstance);
			
			//Format output
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Unable to make prediction, please try again.";
		}
		return String.valueOf(value);
	}

	private Classifier loadModel() throws Exception {
		return (Classifier) weka.core.SerializationHelper.read(this.modelPath);
	}

	private File copyDataSet(String data) throws IOException {
		String newFileName = this.arffDataSet.substring(0, this.arffDataSet.lastIndexOf("."));
		File tempArff = File.createTempFile(newFileName + "_temp", ".arff", new File("resources/"));
		tempArff.deleteOnExit();
		File mainArff = new File(data_dir + this.arffDataSet);
		Files.copy(mainArff.toPath(), tempArff.toPath(), REPLACE_EXISTING);
		FileWriter writer = new FileWriter(tempArff, true);
		writer.write(data);
		writer.flush();
		writer.close();
		return tempArff;
	}

	private Instances loadDataSet(File dataSet) throws Exception {
		return DataSource.read(new FileInputStream(dataSet));
	}
}
