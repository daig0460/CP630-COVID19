package ec.project.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.project.db.Log;
import ec.project.db.LogRepository;

/**
 * Session Bean implementation class StatsEcuserStateful
 */
@Stateful
@LocalBean
public class LogStateful implements LogStatefulLocal {
	
	@PersistenceContext(unitName="primary")
    private EntityManager entityManager;

    private LogRepository logrep;

    /**
     * Default constructor. 
     */
    public LogStateful() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createUser(Log newlog) {
		logrep = new LogRepository(entityManager);
		logrep.createLog(newlog);
	}

	@Override
	public List<Log> findLogbyDate(Date date) {
		logrep = new LogRepository(entityManager);
        return logrep.findLogByDate(date);
	}

	@Override
	public List<Log> findLogbyUser(int userid) {
		logrep = new LogRepository(entityManager);
        return logrep.findLogByUser(userid);
	}

	@Override
	public List<Log> getAllLogs() {
		logrep = new LogRepository(entityManager);
        return logrep.findAll();
	}

}
