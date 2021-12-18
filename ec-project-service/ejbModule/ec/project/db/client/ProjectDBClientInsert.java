package ec.project.db.client;

import java.sql.*;
import java.io.*;

public class ProjectDBClientInsert {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "";

	public static void write(Object obj, PreparedStatement ps, int parameterIndex) throws SQLException, IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.close();
		ps.setBytes(parameterIndex, baos.toByteArray());
	}

	public static Object read(ResultSet rs, String columnname)
			throws SQLException, IOException, ClassNotFoundException {
		byte[] buffer = rs.getBytes(columnname);
		if (buffer != null) {
			ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buffer));
			return objectIn.readObject();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {

		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		String sql;

		try {

			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			statement = connection.createStatement();

            sql = "INSERT INTO appusers (username, password, isadmin) VALUES ('admin', '1234', 1)";
            statement.execute(sql);
            
            sql = "INSERT INTO appusers (username, password) VALUES ('general', '1234')";
            statement.execute(sql);
            
            sql = "INSERT INTO predictionmodel (modelname, isviewable) VALUES ('KNN_One_Dose', 0)";
            statement.execute(sql);
            
            sql = "INSERT INTO predictionmodel (modelname, isviewable) VALUES ('KNN_Fully_Vaccinated', 1)";
            statement.execute(sql);
            
            sql = "INSERT INTO predictionmodel (modelname, isviewable) VALUES ('LN_Death', 0)";
            statement.execute(sql);
            
            sql = "INSERT INTO predictionmodel (modelname, isviewable) VALUES ('LN_Resolved', 0)";
            statement.execute(sql);
            
            sql = "INSERT INTO predictionmodel (modelname, isviewable) VALUES ('RF_PHU', 0)";
            statement.execute(sql);
            
//            sql = "INSERT INTO weka (modelname, model, arff) VALUES ('RF_PHU', ?, ?)";
//			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql);
//	
//			//load model
//			Classifier cls = (Classifier) weka.core.SerializationHelper.read("src\\main\\resources\\resources\\models\\RandomForest\\Random_Forest_PHU.model");
//			File mainArff = new File("src\\main\\resources\\resources\\prediction\\Random_Forest_PHU_Prediction.arff");
//
//			ByteArrayOutputStream bos_model = new ByteArrayOutputStream();
//			ByteArrayOutputStream bos_arff = new ByteArrayOutputStream();
//			ObjectOutputStream modelOut = new ObjectOutputStream(bos_model);
//			ObjectOutputStream arffOut = new ObjectOutputStream(bos_arff);
//			
//			modelOut.writeObject(cls);
//			arffOut.writeObject(mainArff);
//			
//		    byte[] buf = bos_model.toByteArray();
//		    byte[] arffbuf = bos_arff.toByteArray();
//			// set input parameters
//			ps.setObject(1, buf);
//			ps.setObject(2, arffbuf);
//			
//			ps.executeUpdate(); //executes the SQP statement
//
//			ps.close();
            System.out.println("Successfully inserted into model!");

//			statement = connection.createStatement();
//			sql = "SELECT * FROM weka WHERE modelname = 'RF_PHU'";
//            rs = statement.executeQuery(sql);
//			
//            if (rs != null) {
//            	
//                while (rs.next()) {
//
//                    byte[] buftest = rs.getBytes("arff");
//                    if (buf != null) {
//                    	ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(buftest));
//                    	File arffFile = (File) objectIn.readObject();
//                    	Instances tester = DataSource.read(new FileInputStream(arffFile));
//                    	System.out.println(tester.toString());
//                    }
//                }
//            }
//
//			rs.close();
//			statement.close();

//			statement = connection.createStatement();
//			rs = statement.executeQuery("SELECT object FROM model");
//			
//			while (rs.next()) {
//				System.out.println(read(rs, "object").toString());
//			}
//
//			rs.close();
//			statement.close();

		} catch (SQLException e) { // Handle errors for JDBC
			e.printStackTrace();
		} catch (Exception e) { // Handle errors for Class.forName
			e.printStackTrace();
		} finally { // finally block used to close resources
			try {
				if (statement != null) statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) connection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}