package ec.project.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="weka")
public class Weka {
	
	@Id
	@GeneratedValue
	private int id;
	   
	@Column(unique = true)
	private String modelname;
	   
	@Column
	private byte[] model;
	private byte[] arff;
	
	public Weka() {
	}
	
	public Weka(String modelName, byte[] model, byte[] arff) {
	
		this.modelname = modelName;
	    this.model = model;
	    this.arff = arff;
	}
	
	public int getId() {
		return id;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public byte[] getModel() {
		return model;
	}

	public void setModel(byte[] model) {
		this.model = model;
	}

	public byte[] getArff() {
		return arff;
	}

	public void setArff(byte[] arff) {
		this.arff = arff;
	}
}
