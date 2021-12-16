package ec.project.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="appusers")
public class AppUsers {
	@Id
    @GeneratedValue
    private int userid;
    
    @Column(unique = true)
    private String username;
    
    @Column
    private String password;
    
    @Column(columnDefinition = "boolean default false")
    private boolean isadmin;
    
    public AppUsers() {
	}
    
    public AppUsers(String name, String pass, boolean admin) {
        this.username = name;
        this.password = pass;
        this.isadmin = admin;
    }
    
    public Integer getId() { 
    	return userid; 
    }

    public String getUsername() { 
    	return username; 
    }
    
    public void setUsername(String name) {  
    	this.username = name; 
    }
    
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getIsadmin() {
		return isadmin;
	}
	
	public void setIsadmin(boolean admin) {
		this.isadmin = admin;
	}
	
    @Override
    public String toString() {
        return "User {" + "id= " + userid + ", name= " + username + ", isAdmin= " + isadmin + '\'' + '}';
    }
}