package ec.project.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.project.db.Weka;
import ec.project.db.WekaRepository;

/**
 * Session Bean implementation class StatsEcuserStateful
 */
@Stateful
@LocalBean
public class WekaStateful implements WekaStatefulLocal {
	
	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;

    private WekaRepository wekarep;

    /**
     * Default constructor. 
     */
    public WekaStateful() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Weka findModelbyName(String modelName) {
		wekarep = new WekaRepository(entityManager);
        return wekarep.findModel(modelName);
	}

	@Override
	public List<Weka> getAllModels() {
		wekarep = new WekaRepository(entityManager);
        return wekarep.findAll();
	}

}
