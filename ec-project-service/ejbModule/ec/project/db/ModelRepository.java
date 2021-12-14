package ec.project.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ModelRepository {

private EntityManager entityManager;
    
    public ModelRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Model> findAll() {
        return entityManager.createQuery("from Model").getResultList();
    }
    
    //Also acts as save
    public void createModel(Model model) {
        entityManager.persist(model);
    }
    
    //Get all info
    public Model findModel(String modelName) {
    	Model foundModel;
    	try {
	        foundModel =  entityManager.createQuery("SELECT m FROM Model m WHERE m.modelname = :name", Model.class)
	        		.setParameter("name", modelName)
	        		.getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
        return foundModel;
    }

}
