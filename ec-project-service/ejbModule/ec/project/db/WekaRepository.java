package ec.project.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class WekaRepository {

private EntityManager entityManager;
    
    public WekaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Weka> findAll() {
        return entityManager.createQuery("from Weka").getResultList();
    }
    
    public void createModel(Weka model) {
        entityManager.persist(model);
    }
    
    public void saveModel(Weka model) {
        entityManager.merge(model);
    }
    
    //Get all info
    public Weka findModel(String modelName) {
    	Weka foundModel;
    	try {
	        foundModel = entityManager.createQuery("SELECT m FROM Weka m WHERE m.modelname = :name", Weka.class)
	        		.setParameter("name", modelName)
	        		.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
        return foundModel;
    }

}
