package ec.project.ejb;

import java.util.List;

import javax.ejb.Local;

import ec.project.db.PredictionModel;

@Local
public interface PredictionModelStatefulLocal {

	public void saveModel(PredictionModel model);
	public PredictionModel findModelbyName(String modelName);
	public List<PredictionModel> getAllModels();
	
}
