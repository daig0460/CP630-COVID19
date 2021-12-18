package ec.project.ejb;

import java.util.List;

import javax.ejb.Local;

import ec.project.db.Weka;

@Local
public interface WekaStatefulLocal {

	public Weka findModelbyName(String modelName);
	public List<Weka> getAllModels();
	
}
