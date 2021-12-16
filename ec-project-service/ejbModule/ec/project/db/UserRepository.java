package ec.project.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;

public class UserRepository {
	
    private EntityManager entityManager;
    
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void createUser(AppUsers user) {
        entityManager.persist(user);
    }
    
    public List<AppUsers> findAll() {
        return entityManager.createQuery("from Appusers").getResultList();
    }

    public AppUsers findByID(int id) {
    	AppUsers user = entityManager.createQuery("SELECT u FROM Appusers u WHERE u.userid = :Userid",  AppUsers.class).setParameter("Userid", id).getSingleResult();
        return user != null ? user : null;
    }
    
    public AppUsers findByName(String name) {
        List<AppUsers> users = entityManager.createQuery("SELECT u FROM Appusers u WHERE u.username = :name", AppUsers.class)
                .setParameter("name", name)
                .getResultList();
        if (users.size() == 0) return null;
        else return users.get(0);
    }
    
    public AppUsers authorize(String name, String pass) {
    	AppUsers user;
    	try {
	        user = entityManager.createQuery("SELECT u FROM Appusers u WHERE u.username = :name AND u.password = :pass", AppUsers.class)
	                .setParameter("name", name).setParameter("pass", pass)
	                .getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
        return user;
    }
}
