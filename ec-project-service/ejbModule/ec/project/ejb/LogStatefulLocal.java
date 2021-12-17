package ec.project.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.project.db.Log;

@Local
public interface LogStatefulLocal {

	public void createUser(Log newlog);
	public List<Log> findLogbyDate(Date date);
	public List<Log> findLogbyUser(int userid);
	public List<Log> getAllLogs();
	
}
