package ec.ml.scala;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

// import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
// import org.apache.spark.mllib.regression.LabeledPoint;
// import org.apache.spark.mllib.util.MLUtils;
// import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;

public class create_linear_regression_model {
	public static void main(String[] args) throws Exception {
		
		SparkSession session = SparkSession.builder()
				.appName("Linear Regression")
				.config("key", "value")
				.master("local")
				.getOrCreate();
		
		//@SuppressWarnings("deprecation")
		SQLContext sqlContext = session.sqlContext(); //new org.apache.spark.sql.SparkSession(session.sparkContext()); //.SQLContext(session.sparkContext());
		
		// ========================= REGULAR ROUTE ================================
		Dataset<Row> data = sqlContext.read()
				.format("libsvm")
				.load("src/main/resources/data/vaccines.txt");
		
		LinearRegression lr = new LinearRegression()
				  .setMaxIter(10)
				  .setRegParam(0.3)
				  .setElasticNetParam(0.8);
		
		// Fit the model.
		LinearRegressionModel lrModel = lr.fit(data);
		
		// Save the decision tree model to file
		 try {
	         FileOutputStream fileOut = new FileOutputStream(Paths.get("src/main/resources/model/linear_regression_model.bin").toString());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(lrModel);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in src/main/resources/model/linear_regression_model.bin");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		 
		 // ========================= END OF REGULAR ROUTE ================================
		 
		 // ========================= DISTRIBUTED APPROACH ROUTE ================================
		 
		 // JavaRDD<LabeledPoint> dataDist = MLUtils
		 //		 .loadLibSVMFile(session.sparkContext(), "data/vaccines.text")
		 //		 .toJavaRDD();
		 
		 // Split initial RDD into two (training data - 60%, testing data - 40%)
		 //JavaRDD<LabeledPoint>[] splits = dataDist.randomSplit(new double[] {0.7, 0.3});
		 //JavaRDD<LabeledPoint> trainingSet = splits[0].cache();
		 //JavaRDD<LabeledPoint> testingSet = splits[1];
		 
		 // RDD<String> test = session.sparkContext().textFile("data/vaccines.text", 5);
		 // LinearRegressionModel model = new LinearRegressionModel().trainingSummary();
		 
		 // ========================= END OF DISTRIBUTED APPROACH ROUTE ================================
		 
		 session.close();
		 session.stop();
	}
}
