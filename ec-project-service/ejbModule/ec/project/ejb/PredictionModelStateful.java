package ec.project.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.project.db.ModelRepository;
import ec.project.db.PredictionModel;

/**
 * Session Bean implementation class StatsEcuserStateful
 */
@Stateful
@LocalBean
public class PredictionModelStateful implements PredictionModelStatefulLocal {
	
	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;

    private ModelRepository modelrep;

    /**
     * Default constructor. 
     */
    public PredictionModelStateful() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void saveModel(PredictionModel model) {
		modelrep = new ModelRepository(entityManager);
        modelrep.createModel(model);
	}

	@Override
	public PredictionModel findModelbyName(String modelName) {
		modelrep = new ModelRepository(entityManager);
        return modelrep.findModel(modelName);
	}

	@Override
	public List<PredictionModel> getAllModels() {
		modelrep = new ModelRepository(entityManager);
        return modelrep.findAll();
	}

}
