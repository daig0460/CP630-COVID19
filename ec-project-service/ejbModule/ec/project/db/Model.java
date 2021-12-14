package ec.project.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class Model {
	
	@Id
	@GeneratedValue
	private int modelid;
	   
	@Column(unique = true)
	private String modelname;
	   
	@Column
	private boolean isviewable;
	
	public Model() {
	}
	
	public Model(String modelName, boolean isviewable) {
	
		this.modelname = modelName;
	    this.isviewable = isviewable;
	}
	
	public int getId() {
		return modelid;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String name) {
		this.modelname = name;
	}

	public boolean getIsviewable() {
		return isviewable;
	}

	public void setIsviewable(boolean isviewable) {
		this.isviewable = isviewable;
	}

}
