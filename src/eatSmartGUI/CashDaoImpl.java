package eatSmartGUI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CashDaoImpl implements CashDao{
	public static DBUtil dc;
	
	public CashDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	@Override
	public double getCashDetails(String vmID) {
		Connection connection = null;
		String opQry = "";
		
		java.util.Date myDate;
		
		double amount =0.0;
		PreparedStatement opQryPrepSt = null;
		ResultSet opQryResultSet = null;
		
		/*//Date myDate = new Date();
		java.util.Date myDate = new java.util.Date();
	    System.out.println(myDate);

	    SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MMM-yy");

	    // Format the date to Strings
	    String dmy = dmyFormat.format(myDate);

	    // Results...
	    System.out.println(dmy);*/
		
		try{
		connection = dc.getDBConnection();
		//opQry = "select sum(amount) amt from coin where cast(Item_purchase_date as varchar2(50)) = ? and VMID = ? ";
		opQry = "select sum(amount) amt from coin where TO_CHAR(Item_purchase_date, 'yyyy/mm/dd') = TO_CHAR(SYSDATE, 'yyyy/mm/dd') and VMID = ?";
		//opQry = "select sum(amount) amt from coin where Item_purchase_date = ? and VMID = ? ";
		
		//opQry = "select Item_purchase_date from coin where VMID = ? ";
		
		//opQry = "select sum(amount) amt from coin where VMID = ? ";
		//java.util.Date utilDate = new java.util.Date(); // your util date
		
		opQryPrepSt = connection.prepareStatement(opQry);
		//opQryPrepSt.setDate(1, new java.sql.Date(new java.util.Date().getTime()) );
		
		//opQryPrepSt.setTimestamp(1, new Timestamp(utilDate.getTime()));
		//opQryPrepSt.setString(1, dmy);
		
		opQryPrepSt.setString(1, vmID);
		opQryResultSet = opQryPrepSt.executeQuery();
		
		//System.out.println("AYYAYYO" + new java.sql.Date(new java.util.Date().getTime()) );
			
		if(opQryResultSet.next()){
				amount = opQryResultSet.getDouble("amt");
				//myDate = opQryResultSet.getDate("Item_purchase_date");
				//System.out.println("AYYAYYO" + amount);
			}
		}catch(Exception e){
			e.getMessage();
		}
		
		return amount;
	}

	@Override
	public void insertCash(String vmID, double amount) {
		Connection connection = null;
		String opQry = "";
		PreparedStatement opQryPrepSt = null;
		try{
		connection = dc.getDBConnection();
		opQry = "insert into coin (vmID, amount, item_purchase_date) values (?,?,?)";
		opQryPrepSt = connection.prepareStatement(opQry);
		opQryPrepSt.setString(1, vmID);
		opQryPrepSt.setDouble(2, amount);
		opQryPrepSt.setDate(3, new java.sql.Date(new java.util.Date().getTime()) );
		opQryPrepSt.executeUpdate();	
		}catch(Exception e){
			e.getMessage();
		}
	}
}
