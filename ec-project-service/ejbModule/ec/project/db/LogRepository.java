package ec.project.db;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class LogRepository {

private EntityManager entityManager;
    
    public LogRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Log> findAll() {
        return entityManager.createQuery("from Log").getResultList();
    }
    
    //Also acts as save
    public void createLog(Log log) {
        entityManager.persist(log);
    }
    
    public List<Log> findLogByUser(int userid) {
    	try {
	        return entityManager.createQuery("SELECT m FROM Log m WHERE m.userid = :userid", Log.class)
	        		.setParameter("userid", userid)
	        		.getResultList();
    	} catch (NoResultException e) {
    		return null;
    	}
    }
    
    public List<Log> findLogByDate(Date date) {
    	try {
	        return entityManager.createQuery("SELECT m FROM Log m WHERE m.date = :date", Log.class)
	        		.setParameter("date", date)
	        		.getResultList();
    	} catch (NoResultException e) {
    		return null;
    	}
    }
}
