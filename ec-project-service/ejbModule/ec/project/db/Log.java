package ec.project.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="log")
public class Log {
	@Id
    @GeneratedValue
    private int logid;
    
    private int userid;
    
    @Column
    private Date date;
    private String loginfo;
    
    public Log() {
	}
    
    public Log(int userid, Date date, String loginfo) {
        this.userid = userid;
        this.date = date;
        this.loginfo = loginfo;
    }
    public Integer getId() { 
    	return logid; 
    }
    
    public int getUserid() { 
    	return userid; 
    }
    
    public void setUserid(int newid) {  
    	this.userid = newid; 
    }
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getLoginfo() {
		return loginfo;
	}
	
	public void setLoginfo(String loginfo) {
		this.loginfo = loginfo;
	}

}