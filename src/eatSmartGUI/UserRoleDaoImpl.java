package eatSmartGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRoleDaoImpl implements UserRoleDao{
	DBUtil dc;
	public static boolean loginResultFlag = false;
	public UserRoleDaoImpl(){
		dc = DBUtil.getInstance();
	}
	public ArrayList<String> getUserInfoById(String userId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> userInfoList = null;
		String usrId = null;
		String usrPwd = null;
		String usrRole = null;
		String usrName = null;
		try{
			userInfoList = new ArrayList<String>();
			String query = "select userId, userPassword, userrole, userName from userrole where userId = ?";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while(rs.next()){
				loginResultFlag = true;
				usrId = rs.getString("userId");
				usrPwd = rs.getString("userPassword");
				usrRole = rs.getString("userrole");
				usrName = rs.getString("userName");
			}
			userInfoList.add(usrId);
			userInfoList.add(usrPwd);
			userInfoList.add(usrRole);
			userInfoList.add(usrName);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		
		return userInfoList;
	}
}