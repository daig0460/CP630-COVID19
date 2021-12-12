package ec.ml.scala;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
// import java.util.HashMap;
// import java.util.Map;

// import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
import org.apache.spark.ml.feature.IndexToString;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.StringIndexerModel;
import org.apache.spark.ml.feature.VectorIndexer;
import org.apache.spark.ml.feature.VectorIndexerModel;
// import org.apache.spark.mllib.regression.LabeledPoint;
// import org.apache.spark.mllib.tree.DecisionTree;
// import org.apache.spark.mllib.tree.model.DecisionTreeModel;
// import org.apache.spark.mllib.util.MLUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

public class create_decision_tree_model {
	public static void main(String[] args) throws IOException {
		
		// ========================= REGULAR ROUTE ================================
		SparkSession session = SparkSession.builder()
				.appName("DecisionTree")
				.config("key", "value")
				.master("local")
				.getOrCreate();
		  
		//@SuppressWarnings("deprecation")
		SQLContext sqlContext = session.sqlContext(); //new org.apache.spark.sql.SQLContext(session.sparkContext());
		
		// Load the data stored in LIBSVM format as a DataFrame.
		Dataset<Row> data = sqlContext.read()
				.format("libsvm")
				.load("src/main/resources/data/vaccines.txt");
		  
		// Index labels, adding metadata to the label column.
		// Fit on whole dataset to include all labels in index.
		StringIndexerModel indexer = new StringIndexer()
				.setInputCol("label")
				.setOutputCol("indexedLabel")
				.fit(data);
		  
		// Automatically identify categorical features, and index them.
		VectorIndexerModel featureIndexer = new VectorIndexer()
				.setInputCol("features")
				.setOutputCol("indexedFeatures")
				.setMaxCategories(4)
				.fit(data);
		  
		// Split the data into training and test sets (30% held out for testing)
		Dataset<Row>[] sets = data.randomSplit(new double[] {0.7, 0.3});
		  
		// Train a DecisionTree model.
		DecisionTreeClassifier dt = new DecisionTreeClassifier()
				.setLabelCol("indexedLabel")
				.setFeaturesCol("indexedFeatures");
		  
		// Convert indexed labels back to original labels.
		@SuppressWarnings("deprecation")
		IndexToString labelConverter = new IndexToString()
				.setInputCol("prediction")
				.setOutputCol("predictedLabel")
				.setLabels(indexer.labels());
		  
		// Chain indexers and tree in a Pipeline
		Pipeline pipeline = new Pipeline()
				.setStages(new PipelineStage[] {indexer, featureIndexer, dt, labelConverter});
		  
		// Train model. This also runs the indexers.
		PipelineModel model = pipeline.fit(sets[0]);
		
		// Save the decision tree model to file
		sets[1].write().mode(SaveMode.Overwrite)
			.format("parquet")
			.save("C:/enterprise/tmp/data/trainingSet.parquet");
		
		 try {
	         FileOutputStream fileOut = new FileOutputStream(Paths.get("src/main/resources/model/decision_tree_model.bin").toString());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(model);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in src/main/resources/model/decision_tree_model.bin");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		 
		 // ========================= END OF REGULAR ROUTE ================================
		 
		 // ========================= DISTRIBUTED APPROACH ROUTE ================================
		 
		 // String dataPath = "src/main/resources/data/vaccines.txt";
		 
		 // JavaRDD<LabeledPoint> dataDist = MLUtils
		 //		 .loadLibSVMFile(session.sparkContext(), dataPath)
		 //		 .toJavaRDD();
		 
		 // Split the data into training and test sets (70% - training, 30% - testing)
		 // JavaRDD<LabeledPoint>[] splits = dataDist.randomSplit(new double[] {0.7, 0.3});
		 
		 // Split the data into training and test sets (30% for testing)
		 // JavaRDD<LabeledPoint> trainingData = splits[0];
		 // JavaRDD<LabeledPoint> testingData = splits[1];
		 
		 // Set parameter.
		 // Empty categoricalFeaturesIndo indicates all features are continuous
		 // Map<Integer, Integer> categoricationFeaturesInfo = new HashMap<Integer, Integer>();
		 // String impurity = "variance";
		 // int maxDepth = 5;
		 // int maxBins = 32;
		 
		 // DecisionTreeModel modelDist = DecisionTree.trainRegressor(trainingData, categoricationFeaturesInfo, impurity, maxDepth, maxBins);
		 
		 // ========================= END OF DISTRIBUTED APPROACH ROUTE ================================
		 
		 session.close();
		 session.stop();
	}
}
