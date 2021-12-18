package ec.project.ejb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.StringJoiner;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.project.db.Weka;
import ec.project.db.WekaRepository;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
/**
 * Session Bean implementation class PredictionModelStateless
 */
@Stateless
public class KNNFullyStateless implements KNNFullyStatelessLocal {

	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;
	
    private WekaRepository wekarep;
	private int predictionIndex = 3;
	Classifier cls;
    /**
     * Default constructor. 
     */
    public KNNFullyStateless() {
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
	public String predict(File predictionArff) {
		double value = -1;
		try {
			//Load the model
			cls = loadModel();
			
			//Create temp .arff file with user's input for prediction
			//File tempArff = copyDataSet(predictionData);
			
			//Load the arff file
			Instances predictionDataSet = loadDataSet(predictionArff);
			predictionDataSet.setClassIndex(predictionIndex);
			
			//Make the prediction
			Instance predictionDataInstance = predictionDataSet.lastInstance();
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
		//Pull database model
		wekarep = new WekaRepository(entityManager);
		Weka sqlModel = wekarep.findModel("KNN_Fully_Vaccinated");
		if (sqlModel == null) {
			throw new Exception("Invalid model name, please try again.");
		}
		
		byte[] buf = sqlModel.getModel();   
	    ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
	    //System.out.println("Loaded Model!");
	    return (Classifier) objectIn.readObject();
	}

	private Instances loadDataSet(File dataSet) throws Exception {
		return DataSource.read(new FileInputStream(dataSet));
	}

}
