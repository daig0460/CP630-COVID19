package ec.project.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ModelRepository {

private EntityManager entityManager;
    
    public ModelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<PredictionModel> findAll() {
        return entityManager.createQuery("from PredictionModel").getResultList();
    }
    
    //Also acts as save
    public void createModel(PredictionModel model) {
        entityManager.persist(model);
    }
    
    //Get all info
    public PredictionModel findModel(String modelName) {
    	PredictionModel foundModel;
    	try {
	        foundModel =  entityManager.createQuery("SELECT m FROM PredictionModel m WHERE m.modelname = :name", PredictionModel.class)
	        		.setParameter("name", modelName)
	        		.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
        return foundModel;
    }

}
