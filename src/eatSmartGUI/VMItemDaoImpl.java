package eatSmartGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VMItemDaoImpl implements VMItemDAO{
	
	public static DBUtil dc;
	private String[][] data;
	
	public VMItemDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	@Override
	public List<VMItem> getAllVMItem() {
		VMItem vmItem = null;
		List<VMItem> vmItemList = null;
		
		return vmItemList;
	}

	@Override
	public VMItem getVMItem(String newvmItem) {
		Connection connection = null;
		VMItem vmItem = null;
		PreparedStatement vmItemPrepSt = null;
		ResultSet vmItemResultSet = null;
		String vmID;
		String vm_status;
		PreparedStatement vItemListPrepSt = null;
		ResultSet vItemListResultSet = null;
		List<Integer> itemIdList = new ArrayList<Integer>();
		HashMap<Integer,String> itemStatusMap = new HashMap<Integer,String>();
		HashMap<Integer,Integer> rackQtyMap = new HashMap<Integer,Integer>();
		String opID;
		String msID;
		HashMap<Integer,Integer> saleQtyMap = new HashMap<Integer,Integer>();
		HashMap<Integer,Double> revenueMap = new HashMap<Integer,Double>();
		try{
		connection = dc.getDBConnection();
		String vItemQry = "select vmID , vm_status, itemId, opID, msID from VM_Item where vmID = ? and item_status = ? ";
		vmItem = new VMItem();
		vmItemPrepSt = connection.prepareStatement(vItemQry);
		vmItemPrepSt.setString(1, newvmItem);
		vmItemPrepSt.setString(2, "Active");
		vmItemResultSet = vmItemPrepSt.executeQuery();
		while(vmItemResultSet.next()){
			vmID = vmItemResultSet.getString(1);
			vmItem.setVmID(vmID);
			vm_status = vmItemResultSet.getString(2);
			itemIdList.add(vmItemResultSet.getInt(3));
			vmItem.setVm_status(vm_status);
			opID = vmItemResultSet.getString(4);
			vmItem.setOpID(opID);
			msID = vmItemResultSet.getString(5);
			vmItem.setMsID(msID);
		}
		vmItem.setItemId(itemIdList);
		String vItemListInfoQry = "select  itemId, item_status, rackQty, saleQty, Revenue from VM_Item where vmID = ? and item_status = ? ";
		vItemListPrepSt = connection.prepareStatement(vItemListInfoQry);
		vItemListPrepSt.setString(1, newvmItem);
		vItemListPrepSt.setString(2, "Active");
		vItemListResultSet = vItemListPrepSt.executeQuery();
		while(vItemListResultSet.next()){
			itemStatusMap.put(vItemListResultSet.getInt(1), vItemListResultSet.getString(2));
			rackQtyMap.put(vItemListResultSet.getInt(1), vItemListResultSet.getInt(3));
			saleQtyMap.put(vItemListResultSet.getInt(1), vItemListResultSet.getInt(4));
			revenueMap.put(vItemListResultSet.getInt(1), vItemListResultSet.getDouble(5));
		}
		vmItem.setItem_status(itemStatusMap);
		vmItem.setRackQty(rackQtyMap);
		vmItem.setSaleQty(saleQtyMap);
		vmItem.setRevenue(revenueMap);
		}catch(SQLException e){
			e.getMessage();
		}
		return vmItem;
	}

	@Override
	public void deleteVMItem(int itemId, String vmId) {
		Connection connection = null;
		PreparedStatement vmPrepSt = null;
		try{
		connection = dc.getDBConnection();
		String vmQry = "update VM_Item set  item_status  = ?  where itemId = ? and vmID  = ? ";
		vmPrepSt = connection.prepareStatement(vmQry);
		vmPrepSt.setString(1, "InActive");
		vmPrepSt.setInt(2, itemId);
		vmPrepSt.setString(3, vmId);
		vmPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void updateRackQty(int itemId, String vmId, int rackQty) {
		Connection connection = null;
		PreparedStatement vmPrepSt = null;
		try{
		connection = dc.getDBConnection();
		String vmQry = "update VM_Item set  rackQty  = ?  where itemId = ? and vmID  = ? ";
		vmPrepSt = connection.prepareStatement(vmQry);
		vmPrepSt.setInt(1, rackQty);
		vmPrepSt.setInt(2, itemId);
		vmPrepSt.setString(3, vmId);
		vmPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void updateSaleQty(int itemId, String vmId,int saleQty) {
		Connection connection = null;
		PreparedStatement vmPrepSt = null;
		try{
		connection = dc.getDBConnection();
		String vmQry = "update VM_Item set  saleQty  = ?  where itemId = ? and vmID  = ? ";
		vmPrepSt = connection.prepareStatement(vmQry);
		vmPrepSt.setInt(1, saleQty);
		vmPrepSt.setInt(2, itemId);
		vmPrepSt.setString(3, vmId);
		vmPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void updateRevenue(int itemId, String vmId,double revenue) {
		Connection connection = null;
		PreparedStatement vmPrepSt = null;
		try{
		connection = dc.getDBConnection();
		String vmQry = "update VM_Item set  Revenue  = ?  where itemId = ? and vmID  = ? ";
		vmPrepSt = connection.prepareStatement(vmQry);
		vmPrepSt.setDouble(1, revenue);
		vmPrepSt.setInt(2, itemId);
		vmPrepSt.setString(3, vmId);
		vmPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}		
	}

	@Override
	public void insertVMItem(VMItem vmItem) {
		Connection connection = null;
		PreparedStatement vmItemPrepSt = null;
		try{
		connection = dc.getDBConnection();
		String insertQry = "insert into VM_Item (vmID , vm_status, itemId, opID, msID, item_status, rackQty, saleQty, Revenue) values (?,?,?,?,?,?,?,?,?)";
		vmItemPrepSt = connection.prepareStatement(insertQry);
		for(int i=0;i<vmItem.getItemId().size();i++){
			vmItemPrepSt.setString(1, vmItem.getVmID());
			vmItemPrepSt.setString(2, vmItem.getVm_status());
			vmItemPrepSt.setInt(3, vmItem.getItemId().get(i));
			vmItemPrepSt.setString(4, vmItem.getOpID());
			vmItemPrepSt.setString(5, vmItem.getMsID());
			vmItemPrepSt.setString(6, vmItem.getItem_status().get(vmItem.getItemId().get(i)));
			vmItemPrepSt.setInt(7, vmItem.getRackQty().get(vmItem.getItemId().get(i)));
			if(vmItem.getSaleQty().get(vmItem.getItemId().get(i))!=null){
			vmItemPrepSt.setInt(8, vmItem.getSaleQty().get(vmItem.getItemId().get(i)));
			}else{
				vmItemPrepSt.setInt(8, 0);
			}
			if(vmItem.getRevenue().get(vmItem.getItemId().get(i)) !=null){
			vmItemPrepSt.setDouble(9, vmItem.getRevenue().get(vmItem.getItemId().get(i)));
			}else{
				vmItemPrepSt.setDouble(9, 0.0);
			}
			vmItemPrepSt.executeUpdate();
		}
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public List<Integer> getPopularVMItemIds() {
		Connection connection = null;
		List<Integer> itemList = null;
		String vItemQuery = "";
		PreparedStatement vmItemPrepSt = null;
		ResultSet vmItemResultSet = null;
		try{
		connection = dc.getDBConnection();
		itemList = new ArrayList<Integer>();
		vItemQuery = "select * from (select * from  (select ITEMID, SUM(NVL(SALEQTY,0)) sale from VM_ITEM  group by ITEMID  order by sale desc) itemInfo where itemInfo.sale > 0) where rownum<=10";
		vmItemPrepSt = connection.prepareStatement(vItemQuery);
		vmItemResultSet = vmItemPrepSt.executeQuery();
		while(vmItemResultSet.next()){
			itemList.add(vmItemResultSet.getInt(1));
		}
		}catch(Exception e){
			e.getMessage();
		}
		return itemList;
	}
	
	public ArrayList<Integer> findByVMId(String vmId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> itemIdList = null;
		try{
			itemIdList = new ArrayList<Integer>();
			String query = "select ItemId from VM_Item where vmId = ?";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, vmId);
			rs = ps.executeQuery();
			while(rs.next()){
				itemIdList.add(rs.getInt("itemId"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemIdList;
	}
	
	public ArrayList<Integer> findActiveItemsByVMId(String vmId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> itemIdList = null;
		try{
			itemIdList = new ArrayList<Integer>();
			String query = "select ItemId from VM_Item where vmId = ? and Item_status = 'Active'";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, vmId);
			rs = ps.executeQuery();
			while(rs.next()){
				itemIdList.add(rs.getInt("itemId"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemIdList;
	}
	
	public int getVMSaleQtyById(String vmId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//ArrayList<Integer> itemIdList = null;
		int vmWiseSaleQty = 0;
		try{
			//itemIdList = new ArrayList<Integer>();
			String query = "select sale from (select vmid, SUM(NVL(SALEQTY,0)) sale from VM_Item group by vmid order by sale desc) where vmid = ?";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, vmId);
			rs = ps.executeQuery();
			while(rs.next()){
				vmWiseSaleQty = rs.getInt("sale");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return vmWiseSaleQty;
	}
	
	public double getVMRevenueById(String vmId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		double vmWiseRevenue = 0.0;
		try{
			String query = "select revenue from (select vmid, SUM(NVL(revenue,0)) revenue from VM_Item group by vmid order by revenue desc) where vmid = ?";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, vmId);
			rs = ps.executeQuery();
			while(rs.next()){
				vmWiseRevenue = rs.getDouble("revenue");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return vmWiseRevenue;
	}
	
	public String getVMStatusById(String vmId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String vmStatus = "";
		try{
			String query = "select distinct vm_status from vm_item where vmid = ?";
			connection = dc.getDBConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, vmId);
			rs = ps.executeQuery();
			while(rs.next()){
				vmStatus = rs.getString("vm_status");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return vmStatus;
	}
	
	public String[][] getPopularTable() {
		Connection connection = null;
		String vItemQuery = "";
		PreparedStatement vmItemPrepSt = null;
		ResultSet vmItemResultSet = null;
		try{
		connection = dc.getDBConnection();
		vItemQuery = "select item.ITEM_code, item.item_name, iteminfo.sale from item inner join "
				+ "(select ITEMID, SUM(NVL(SALEQTY,0)) sale from VM_ITEM  group by ITEMID  order by sale desc) itemInfo "
				+ "on iteminfo.itemid = item.item_code where itemInfo.sale > 0 order by iteminfo.sale desc ";
		vmItemPrepSt = connection.prepareStatement(vItemQuery);
		vmItemResultSet = vmItemPrepSt.executeQuery();
		processTableData(vmItemResultSet);
		}catch(Exception e){
			e.getMessage();
		}
		return data;
	}
	
	public String[][] getMonitorTable(String sessionUserID){
		Connection connection = null;
		String monitorQuery = "";
		PreparedStatement vmItemPrepSt = null;
		ResultSet monitorResultSet = null;
		try{
		connection = dc.getDBConnection();
		monitorQuery = "select  distinct(V.VMID), V.OPID, V.MSID, V.vm_status, M.msLocation from MONITORINGSTATION M , VM_ITEM V where  V.MSID = M.MSID and M.MSUSERID = ?";
		vmItemPrepSt = connection.prepareStatement(monitorQuery);
		vmItemPrepSt.setString(1, sessionUserID);
		monitorResultSet = vmItemPrepSt.executeQuery();
		processTableData(monitorResultSet);
		}catch(Exception e){
			e.getMessage();
		}
		return data;
	}
	
	@Override
	public void updateOldItemNewItem(int oldItem, int newItem, String vmItem) {
		Connection connection = null;
		PreparedStatement vmPrepSt = null;
		PreparedStatement vmItemPrepSt = null;
		ResultSet vmItemResultSet = null;
		String opId = "";
		String msId = "";
		VMItem vmItemObj = null;
		HashMap<Integer, Integer> rackMap = new HashMap<Integer, Integer>();
		HashMap<Integer, String> itemStatusMap = new HashMap<Integer, String>();
		List<Integer> itemId = new ArrayList<Integer>();
		HashMap<Integer, Integer> saleQty = new HashMap<Integer, Integer>();
		HashMap<Integer, Double> revenue = new HashMap<Integer, Double>();
		try{
		connection = dc.getDBConnection();
		vmItemObj = new VMItem();
		String vmQry = "update VM_Item set  item_status  = ?  where ITEMID  = ? and vmID =?";
		vmPrepSt = connection.prepareStatement(vmQry);
		vmPrepSt.setString(1, "InActive");
		vmPrepSt.setInt(2, oldItem);
		vmPrepSt.setString(3, vmItem);
		vmPrepSt.executeUpdate();
		
		String opMSQry = "select OPID, MSID from VM_ITEM where VMID = ? and ITEMID = ? ";
		vmItemPrepSt = connection.prepareStatement(opMSQry);
		vmItemPrepSt.setString(1, vmItem);
		vmItemPrepSt.setInt(2, oldItem );
		vmItemResultSet = vmItemPrepSt.executeQuery();
		while(vmItemResultSet.next()){
			opId = vmItemResultSet.getString(1);
			msId = vmItemResultSet.getString(2);
		}
		vmItemObj.setVmID(vmItem);
		vmItemObj.setVm_status("Active");
		vmItemObj.setOpID(opId);
		vmItemObj.setMsID(msId);
		rackMap.put(newItem, 10);
		vmItemObj.setRackQty(rackMap);
		itemStatusMap.put(newItem, "Active");
		vmItemObj.setItem_status(itemStatusMap);
		itemId.add(newItem);
		vmItemObj.setItemId(itemId);
		vmItemObj.setSaleQty(saleQty);
		vmItemObj.setRevenue(revenue);
		insertVMItem(vmItemObj);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	//This method processes the resultset
		public void processTableData(ResultSet rslSt){
			try{
				
				ResultSetMetaData metaData = rslSt.getMetaData();
				int columns = metaData.getColumnCount();
				ArrayList<String[]> rows = new ArrayList<>();
				while(rslSt.next()){
					String[] currentRow = new String[columns];
					for(int i = 0; i < columns; i++){
						currentRow[i] = rslSt.getString(i+1);
					}
					rows.add(currentRow);
				}
				
				data = new String[rows.size()][columns];
				
				data = rows.toArray(data);
			}
			
			catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		
		public HashMap<String, Integer> getAllVMSaleQuantity(){
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			HashMap<String, Integer> vmSaleQtyMap= null;
			try{
				vmSaleQtyMap = new HashMap<String, Integer>();
				String query = "select VMId, sum(saleQty) from vm_item group by VMId";
				connection = dc.getDBConnection();
				ps = connection.prepareStatement(query);
				rs = ps.executeQuery();
				while(rs.next()){
					vmSaleQtyMap.put(rs.getString(1), rs.getInt(2));
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBUtil.closeConnection(rs, null, ps, connection);
			}
			return vmSaleQtyMap;
		}
		
		public int getItemRackQtyById(String vmId, int itemId){
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			//ArrayList<Integer> itemIdList = null;
			int vmWiseItemRackQty = 0;
			try{
				//itemIdList = new ArrayList<Integer>();
				String query = "select RACKQTY from VM_ITEM where VMID = ? and ITEMID = ?";
				connection = dc.getDBConnection();
				ps = connection.prepareStatement(query);
				ps.setString(1, vmId);
				ps.setInt(2, itemId);
				rs = ps.executeQuery();
				while(rs.next()){
					vmWiseItemRackQty = rs.getInt("RACKQTY");
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				DBUtil.closeConnection(rs, null, ps, connection);
			}
			return vmWiseItemRackQty;
		}
		
				
}
