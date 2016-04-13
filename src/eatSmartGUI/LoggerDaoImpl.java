package eatSmartGUI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoggerDaoImpl implements LoggerDao{
	
	DBUtil dc;
	
	public LoggerDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	public void insertUserlogInfo(String userId){
		Connection connection = null;
		PreparedStatement ps = null;
		Date loggedDate = new java.sql.Date(new java.util.Date().getTime());
		try{
			String query = "insert into loguserinfo values(?, ?)";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, userId);
			ps.setDate(2, loggedDate);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null,  null, ps, connection);
		}
	}
}