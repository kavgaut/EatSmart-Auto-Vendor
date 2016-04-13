package eatSmartGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** The following class implementation 
 * having two methods getDBConnection(), which returns Connection object 
 * closeConnection(....), which close all the objects.
 * This class is implemented as a singletom
 * @author Swathi Damera and Ramya Bangaluru Gopalakrishna
 *
 */
public class DBUtil{
	private static DBUtil database_Instance = new DBUtil();
	public Connection conn;
	
	public static DBUtil getInstance(){
		return database_Instance;
	}
	
	private DBUtil(){
	}
	
	/*The following method getConnection() definition defines 
	 * which takes the values like Driver class,URL,UserName,Password
	 *and gives the Connection with the Database Server
	 */
	public Connection getDBConnection() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			System.out.println("where is your jdbc driver");
			e.printStackTrace();
		}
		System.out.println("jdbc driver registered"); 
		
		this.conn = null;
	  	try {
	 		//this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "system", "Swathi.2506");
	 		this.conn  = DriverManager.getConnection(
		    	     "jdbc:oracle:thin:@//localhost:1521/orcl", "hr", "hr");
	 		if(this.conn != null){
	 				System.out.println("You made it, take control of your database now");
	 		}
	 		else{
	 			System.out.println("Failed to make a connection");
	 		}
	 	}catch(SQLException sqlException){
	 		System.out.println("SQLException:" + sqlException.getMessage());
		  	System.out.println("SQLState:" + sqlException.getSQLState());
		  	System.out.println("VendorError:" + sqlException.getErrorCode());
	 	}
	    return this.conn;
	 }
	
	/* The following method closeConnection(....)definition takes the 
	 * parameters like ResultSet, PreparedStatement, Statement, Connection objects
	 * and close all the objects(means nullifying the objects)
	 */

	public static void closeConnection(ResultSet objResultSet,Statement objStatement,PreparedStatement objPrepareStatement,
				    Connection objConnection){
		
			if(objResultSet != null){
				try{
				objResultSet.close();
				//System.out.println("ResultSet object is closed");
				}catch(SQLException se){
					System.out.println("ResultSet not closed");
				}
			}
			if(objPrepareStatement != null){
				try{
				objPrepareStatement.close();
				//System.out.println("PreparedStatement  object is closed");
				}catch(SQLException sePrepareStatement){
					System.out.println("PreparedStatement not closed");
				}
			}
			if(objStatement != null){
				try{
				objStatement.close();
				//System.out.println("Statement  object is closed");
				}catch(SQLException seStatement){
					System.out.println("Statement not closed");
				}
			}
			try {
				if (!objConnection.isClosed()) {
					try{
							objConnection.close();
					//System.out.println("Connection object is closed");
					}catch(SQLException seCon){
						System.out.println("Connection not closed");
					}
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
}