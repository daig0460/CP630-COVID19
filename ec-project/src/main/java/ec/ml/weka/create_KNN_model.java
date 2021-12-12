package ec.ml.weka;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.classifiers.lazy.IBk;
import weka.core.Instances;

public class create_KNN_model {

	public static void main(String[] args) throws Exception {
		
		BufferedReader dataFile = readDataFile("vaccines.txt");
		
		Instances instances = new Instances(dataFile);
		
		IBk ibk = new IBk(5);
		ibk.buildClassifier(instances);
	}
	
	public static BufferedReader readDataFile(String fileName) {
		BufferedReader inputReader = null;
		
		try {
			inputReader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + fileName);
		}
		
		return inputReader;
	}
}
