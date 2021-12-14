package ec.project.db;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import java.util.List;

public class UserRepository {
	
    private EntityManager entityManager;
    
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public void createUser(User user) {
        entityManager.persist(user);
    }
    
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public User findByID(int id) {
    	User user = entityManager.createQuery("SELECT u FROM User u WHERE u.userid = :Userid",  User.class).setParameter("Userid", id).getSingleResult();
        return user != null ? user : null;
    }
    
    public User findByName(String name) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :name", User.class)
                .setParameter("name", name)
                .getResultList();
        if (users.size() == 0) return null;
        else return users.get(0);
    }
    
    public User authorize(String name, String pass) {
    	User user;
    	try {
	        user = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :name AND u.password = :pass", User.class)
	                .setParameter("name", name).setParameter("pass", pass)
	                .getSingleResult();
    	} catch (NoResultException e) {
    		return null;
    	}
        return user;
    }
}
