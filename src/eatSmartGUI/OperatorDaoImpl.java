package eatSmartGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperatorDaoImpl implements OperatorDao{
	public static DBUtil dc;
	
	public OperatorDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	@Override
	public boolean authenticateOperator(int key) {
		boolean opValid = false;
		Connection connection = null;
		String opQry = "";
		PreparedStatement opQryPrepSt = null;
		ResultSet opQryResultSet = null;
		try{
		connection = dc.getDBConnection();
		opQry = "select count(distinct(I.OPID)) cnt from VMOPERATOR O , VM_ITEM I where I.OPID = O.OPID and O.OPKEY = ? ";
		opQryPrepSt = connection.prepareStatement(opQry);
		opQryPrepSt.setInt(1, key);
		opQryResultSet = opQryPrepSt.executeQuery();
			if(opQryResultSet.next()){
				int count = opQryResultSet.getInt("cnt");
				if(count == 1){
					opValid = true;
				}
			}
		}catch(Exception e){
			e.getMessage();
		}
		return opValid;
	}

}
