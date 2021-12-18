package ec.project.db.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectDBClientCreate {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	static final String USER = "root";
	static final String PASS = "";
	
    
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Statement statement = null;
        
        String sql;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

//            sql = "DROP TABLE predictionmodel";
//            statement.execute(sql);
            
//            sql = "DROP TABLE usertable";
//            statement.execute(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS appusers ("
            		+ "userid INT AUTO_INCREMENT PRIMARY KEY, "
            		+ "username VARCHAR(255) NOT NULL UNIQUE, "
            		+ "password VARCHAR(255) NOT NULL, "
            		+ "isadmin BOOLEAN NOT NULL DEFAULT 0)";
            statement.execute(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS predictionmodel ("
            		+ "modelid INT AUTO_INCREMENT PRIMARY KEY, "
            		+ "modelname VARCHAR(255) NOT NULL UNIQUE, "
            		+ "isviewable BOOLEAN NOT NULL)";            
            statement.execute(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS log ("
            		+ "logid INT AUTO_INCREMENT PRIMARY KEY, "
            		+ "userid INT NOT NULL, "
            		+ "date TIMESTAMP, "
            		+ "loginfo VARCHAR(255) NOT NULL, " 
            		+ "FOREIGN KEY (userid) REFERENCES appusers(userid))";            
            statement.execute(sql);
            
            sql = "CREATE TABLE IF NOT EXISTS weka ("
            		+ "id INT AUTO_INCREMENT PRIMARY KEY, "
            		+ "modelname VARCHAR(255) NOT NULL UNIQUE, "
            		+ "model LONGBLOB NOT NULL,"
            		+ "arff MEDIUMBLOB NOT NULL)";            
            statement.execute(sql);

            System.out.println("Successfully created 4 tables!");
        } catch (SQLException e) { // Handle errors for JDBC
            e.printStackTrace();
        } catch (Exception e) {   // Handle errors for Class.forName
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