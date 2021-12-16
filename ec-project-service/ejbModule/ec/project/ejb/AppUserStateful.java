package ec.project.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.project.db.AppUsers;
import ec.project.db.UserRepository;

/**
 * Session Bean implementation class StatsEcuserStateful
 */
@Stateful
@LocalBean
public class AppUserStateful implements AppUserStatefulLocal {
	
	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;

    private UserRepository userrep;

    /**
     * Default constructor. 
     */
    public AppUserStateful() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public AppUsers validate(String username, String pass) {
        userrep = new UserRepository(entityManager);
        return userrep.authorize(username, pass);
	}

	@Override
	public List<AppUsers> getAllUsers() {
		userrep = new UserRepository(entityManager);
        return userrep.findAll();
	}

	@Override
	public AppUsers findUserByID(int userid) {
		userrep = new UserRepository(entityManager);
        return userrep.findByID(userid);
	}

	@Override
	public AppUsers findUserByName(String username) {
		userrep = new UserRepository(entityManager);
        return userrep.findByName(username);
	}

}
