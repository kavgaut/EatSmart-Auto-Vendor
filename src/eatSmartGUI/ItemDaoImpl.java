package eatSmartGUI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Item Dao implementation class that connects to the database and
 * provides implementation to insert, update and find records of an
 * item table in the database
 * @author Ramya Bangaluru Gopalakrishna
 */
public class ItemDaoImpl implements ItemDao{
	
	public DBUtil dc;
	public int newGeneratedItemCode;
	
	/** Constructor which gets an instance of a 
	 * DatabaseConnection class which is a singleton
	 */
	public ItemDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	/** Update method to update the price information
	 * of an item in the database
	 */
	public void updateItemPrice(int itemCode, double itemPrice){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = dc.getDBConnection();
			String query = "update item set price = ? where item_Code = ?";
			ps = connection.prepareStatement(query);
			ps.setDouble(1, itemPrice);
			ps.setInt(2, itemCode);
			ps.executeUpdate();
			System.out.println("price updated for ItemCode : "+itemCode);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
	/** method to find an item information based on the item code
	 **/
	/** method to find an item information based on the item code
	 **/
	public Item findById(int itemCode, Item itemObj){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			connection = dc.getDBConnection();
			String query = "select * from item where item_code = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, itemCode);
			rs = ps.executeQuery();
			
			while(rs.next()){
				itemObj.setItemCode(rs.getInt("item_code"));
				System.out.println(itemObj.getItemCode());
				itemObj.setItemName(rs.getString("item_name"));
				System.out.println(itemObj.getItemName());
				itemObj.setItemType(rs.getString("item_type"));
				System.out.println(itemObj.getItemType());
				itemObj.setCalValue(rs.getInt("calorie_value"));
				System.out.println(itemObj.getCalValue());
				itemObj.setIngredientInfo(rs.getString("ingredient_info"));
				System.out.println(itemObj.getIngredientInfo());
				itemObj.setSugarValue(rs.getString("sugar"));
				System.out.println(itemObj.getSugarVal());
				itemObj.setCarbohydrateValue(rs.getString("carbohydrate"));
				System.out.println(itemObj.getCarbohydrateValue());
				itemObj.setCholestrolValue(rs.getString("cholesterol"));
				System.out.println(itemObj.getCarbohydrateValue());
				itemObj.setTotalFatValue(rs.getString("total_fat"));
				System.out.println(itemObj.getTotalFatValue());
				itemObj.setProteinValue(rs.getString("protein"));
				System.out.println(itemObj.getProteinValue());
				itemObj.setGlutenFree(rs.getString("gluten_free"));
				System.out.println(itemObj.getGlutenFree());
				itemObj.setLowFat(rs.getString("low_fat"));
				System.out.println(itemObj.getLowFat());
				itemObj.setLowSugar(rs.getString("low_sugar"));
				System.out.println(itemObj.getLowSugar());
				itemObj.setItemPrice(rs.getDouble("price"));
				System.out.println(itemObj.getItemPrice());
				itemObj.setItemCreatedDate(rs.getDate("created_date"));
				System.out.println(itemObj.getItemCreatedDate());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemObj;
	}
	
	/** insert method to add a new item entry into the database
	 */
	public void insertItem(String itemName, String itemType,
			int calorieValue, String ingredientInfo, String sugarContent,
			String carbContent, String cholestrolContent, String fatContent,
			String ProteinContent, String glutenFree, String lowFat,
			String lowSugar, double itemPrice) {
		
			Connection connection = null;
			PreparedStatement ps = null;
			PreparedStatement pst = null;
			ResultSet seqRs = null;
			long genItemId = 0L;
			int itemCode = 0;
		try{
			connection = dc.getDBConnection();
			String sqlIdentifier = "select item_seq.NEXTVAL from dual";
			pst = connection.prepareStatement(sqlIdentifier);
			seqRs = pst.executeQuery();
			if(seqRs.next())
				genItemId = seqRs.getLong(1);
			String query = "insert into item values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			itemCode = (int) genItemId;
			newGeneratedItemCode = itemCode;
			ps.setInt(1, itemCode);
			ps.setString(2, itemName);
			ps.setString(3, itemType);
			ps.setInt(4, calorieValue);
			ps.setString(5, ingredientInfo);
			ps.setString(6, sugarContent);
			ps.setString(7, carbContent);
			ps.setString(8, cholestrolContent);
			ps.setString(9, fatContent);
			ps.setString(10, ProteinContent);
			ps.setString(11, glutenFree);
			ps.setString(12, lowFat);
			ps.setString(13, lowSugar);
			ps.setDouble(14, itemPrice);
			ps.setDate(15, new java.sql.Date(new java.util.Date().getTime()));
			ps.executeUpdate();
			System.out.println("Item inserted using Item Id: "+itemCode);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}

	@Override
	public List<Integer> findItemByParams(String gluten, String lowFat, String lowSugar, int low, int high) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Integer> itemList = null;
		StringBuilder mainQrySb = new StringBuilder();
		StringBuilder whereQrySb = new StringBuilder();
		
		try{
			itemList = new ArrayList<Integer>();
			connection = dc.getDBConnection();
			mainQrySb.append("Select item_code from item ");
			if("Y".equals(gluten)){
				if(!"".equals(whereQrySb.toString())){
					whereQrySb.append(" AND GLUTEN_FREE = '"+gluten+"' ");
				}else{
					whereQrySb.append(" WHERE GLUTEN_FREE = '"+gluten+"' ");
				}
			}
			if("Y".equals(lowFat)){
				if(!"".equals(whereQrySb.toString())){
					whereQrySb.append(" AND LOW_FAT = '"+lowFat+"' ");
				}else{
					whereQrySb.append(" WHERE LOW_FAT = '"+lowFat+"' ");
				}
			}
			if("Y".equals(lowSugar)){
				if(!"".equals(whereQrySb.toString())){
					whereQrySb.append(" AND LOW_SUGAR = '"+lowSugar+"' ");
				}else{
					whereQrySb.append(" WHERE LOW_SUGAR = '"+lowSugar+"' ");
				}
			}
			if(high>0){
				if(!"".equals(whereQrySb.toString())){
					whereQrySb.append(" AND CALORIE_VALUE <= '"+high+"' ");
				}else{
					whereQrySb.append(" WHERE CALORIE_VALUE <= '"+high+"' ");
				}
			}
			if(low>0){
				if(!"".equals(whereQrySb.toString())){
					whereQrySb.append(" AND CALORIE_VALUE >= '"+low+"' ");
				}else{
					whereQrySb.append(" WHERE CALORIE_VALUE >= '"+low+"' ");
				}
			}
			if(!"".equals(whereQrySb.toString())){
				mainQrySb.append(whereQrySb);
			}
			ps = connection.prepareStatement(mainQrySb.toString());
			if(ps!=null){
			rs = ps.executeQuery();
			while(rs.next()){
				itemList.add(rs.getInt("item_code"));
			}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemList;
	}
	
	public ArrayList<Integer> findByItemType(String itemType){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> itemIdList = new ArrayList<Integer>();
		try{
			connection = dc.getDBConnection();
			String query = "select item_code from item where item_type = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, itemType);
			rs = ps.executeQuery();
			while(rs.next()){
				itemIdList.add(rs.getInt("item_code"));
			}
			//itemIdList.clear();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemIdList;
	}
	
	/** method to find an item information based on the item code
	 **/
	/** method to find an item information based on the item code
	 **/
	public Item findById(int itemCode){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int newItemCode = 0;
		String itemName = "";
		String itemType = "";
		int calorie = 0;
		String ingredientInfo = "";
		String sugarVal = "";
		String carb = "";
		String chols = "";
		String totFat = "";
		String protein ="";
		String gluten ="";
		String lowFat = "";
		String lowSugar = "";
		double price = 0;
		Date date= null;
		Item itemObj = null;
		try{
			connection = dc.getDBConnection();
			String query = "select * from item where item_code = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, itemCode);
			rs = ps.executeQuery();
			
			while(rs.next()){
				newItemCode = rs.getInt("item_code");
				System.out.println(newItemCode);
				itemName = rs.getString("item_name");
				System.out.println(itemName);
				itemType = rs.getString("item_type");
				System.out.println(itemType);
				calorie = rs.getInt("calorie_value");
				System.out.println(calorie);
				ingredientInfo = rs.getString("ingredient_info");
				System.out.println(ingredientInfo);
				sugarVal = rs.getString("sugar");
				System.out.println(sugarVal);
				carb = rs.getString("carbohydrate");
				System.out.println(carb);
				chols = rs.getString("cholesterol");
				System.out.println(chols);
				totFat = rs.getString("total_fat");
				System.out.println(totFat);
				protein = rs.getString("protein");
				System.out.println(protein);
				gluten = rs.getString("gluten_free");
				System.out.println(gluten);
				lowFat = rs.getString("low_fat");
				System.out.println(lowFat);
				lowSugar = rs.getString("low_sugar");
				System.out.println(lowSugar);
				price = rs.getDouble("price");
				System.out.println(price);
				date = rs.getDate("created_date");
				System.out.println(date);
				if("Perishable".equals(itemType)){
					itemObj = new PerishableFoods();
				}else if("Drinks".equals(itemType)){
					itemObj = new Drinks();
				}else if("Packaged".equals(itemType)){
					itemObj = new PackagedFood();
				}
				itemObj.setItemCode(newItemCode);
				itemObj.setItemName(itemName);
				itemObj.setItemType(itemType);
				itemObj.setCalValue(calorie);
				itemObj.setIngredientInfo(ingredientInfo);
				itemObj.setSugarValue(sugarVal);
				itemObj.setCarbohydrateValue(carb);
				itemObj.setCholestrolValue(chols);
				itemObj.setTotalFatValue(totFat);
				itemObj.setProteinValue(protein);
				itemObj.setGlutenFree(gluten);
				itemObj.setLowFat(lowFat);
				itemObj.setLowSugar(lowSugar);
				itemObj.setItemPrice(price);
				itemObj.setItemCreatedDate(date);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return itemObj;
	}
	@Override
	public List<Integer> getAllItemCodes() {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query ="";
		List<Integer> itemList = null;
		try{
		connection = dc.getDBConnection();
		itemList = new ArrayList<>();
		query = "select item_code from item";
		ps = connection.prepareStatement(query);
		rs = ps.executeQuery();
		while(rs.next()){
			itemList.add(rs.getInt(1));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return itemList;
	}
	

	@Override
	public void updatedItemNutritionalInfo(Item itemObj) {
		Connection connection = null;
		PreparedStatement ps = null;
		String query = "";
		try{
			connection = dc.getDBConnection();
			query = "update Item set item_name = ? , calorie_value = ? , ingredient_info =?, sugar =?, carbohydrate=?, cholesterol =?, total_fat = ?, protein= ?, gluten_free =?, low_fat = ?, low_sugar =? where item_Code = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, itemObj.getItemName());
			ps.setInt(2, itemObj.getCalValue());
			ps.setString(3, itemObj.getIngredientInfo());
			ps.setString(4, itemObj.getSugarVal());
			ps.setString(5, itemObj.getCarbohydrateValue());
			ps.setString(6, itemObj.getCholestrolValue());
			ps.setString(7, itemObj.getTotalFatValue());
			ps.setString(8, itemObj.getProteinValue());
			ps.setString(9, itemObj.getGlutenFree());
			ps.setString(10, itemObj.getLowFat());
			ps.setString(11, itemObj.getLowSugar());
			ps.setInt(12, itemObj.getItemCode());
			ps.executeUpdate();
			System.out.println("Ingredient Info updated for ItemCode : "+itemObj.getItemCode());
		}catch(SQLException e){
			System.out.println("query-->"+query);
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
}