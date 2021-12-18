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
public class RFPHUStateless implements RFPHUStatelessLocal {
	
	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;
	
    private WekaRepository wekarep;
    private int predictionIndex = 1;
	
    Classifier cls;
	
    /**
     * Default constructor. 
     */
    public RFPHUStateless() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public String parsePredictionData(String date, String active_cases, String resolved_cases, String deaths) {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(date);
		joiner.add("?");
		joiner.add(active_cases);
		joiner.add(resolved_cases);
		joiner.add(deaths);
		return joiner.toString();
	}
	
	@Override
	public String predict(File predictionArff) {
		double value = -1;
		try {
			//Load the weka model
			cls = loadModel();
			
			//Load the arff file
			Instances predictionDataSet = loadDataSet(predictionArff);
			predictionDataSet.setClassIndex(predictionIndex);
			
			//Make the prediction
			Instance predictionDataInstance = predictionDataSet.lastInstance();
			value = cls.classifyInstance(predictionDataInstance);
			
			//Format output (non numeric result)
			predictionDataInstance.setClassValue(value);
			String beginning = predictionDataInstance.toString().substring(predictionDataInstance.toString().indexOf(",") + 1); //After first comma

			return beginning.substring(1, beginning.indexOf(",") - 1); //After first quote to before first comma
			
			//Format output
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Unable to make prediction, please try again.";
		}
	}
	
	private Classifier loadModel() throws Exception {
		//Pull database model
		wekarep = new WekaRepository(entityManager);
		Weka sqlModel = wekarep.findModel("RF_PHU");
		if (sqlModel == null) {
			throw new Exception("Invalid model name, please try again.");
		}
		
		byte[] buf = sqlModel.getModel();   
	    ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
	    //System.out.println("Loaded Model!");
	    return (Classifier) objectIn.readObject();
	}
	
	private Instances loadDataSet(File arffDataSet) throws Exception {
		return DataSource.read(new FileInputStream(arffDataSet));
	}

//	private File prepDataSet(Weka sqlModel, String data) throws IOException, ClassNotFoundException {
//		System.out.println("Prepping .arff file...");
//		byte[] buf = sqlModel.getArff();
//		ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
//		File initialArff = (File) objectIn.readObject();
//		System.out.println(initialArff.getPath());
//		FileWriter writer = new FileWriter(initialArff, true);
//		writer.write(data);
//		writer.flush();
//		writer.close();
//		return initialArff;
//
////		String newFileName = this.arffDataSet.substring(0, this.arffDataSet.lastIndexOf("."));
////		File tempArff = File.createTempFile(newFileName + "_temp", ".arff", new File("resources/"));
////		tempArff.deleteOnExit();
////		File mainArff = new File(data_dir + this.arffDataSet);
////		Files.copy(mainArff.toPath(), tempArff.toPath(), REPLACE_EXISTING);
//
//	}

}
