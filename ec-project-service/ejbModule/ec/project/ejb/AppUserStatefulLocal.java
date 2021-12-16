package ec.project.ejb;

import java.util.List;

import javax.ejb.Local;

import ec.project.db.AppUsers;

@Local
public interface AppUserStatefulLocal {

	public AppUsers validate(String username, String pass);
	public AppUsers findUserByID(int userid);
	public AppUsers findUserByName(String username);
	public List<AppUsers> getAllUsers();
	
}
