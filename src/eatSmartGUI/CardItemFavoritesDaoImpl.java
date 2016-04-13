package eatSmartGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** user's Item favorites Dao implementation class that connects to the database and
 * provides implementation to insert, update and find records of a
 * Card_item_favorites table in the database
 * @author Ramya Bangaluru Gopalakrishna
 */

public class CardItemFavoritesDaoImpl implements CardItemFavoritesDao{
	DBUtil dc;
	
	/** Constructor which gets an instance of a 
	 * DatabaseConnection class which is a singleton
	 */
	public CardItemFavoritesDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	/** insert method to add a new user item favorites entry into the database
	 */
	public void insertItemFavorites(int cardId, int itemCode){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = dc.getDBConnection();
			String query = "insert into card_item_favorites values(?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cardId);
			ps.setInt(2, itemCode);
			ps.executeUpdate();
			System.out.println("inserted");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
	/** method to find the user item favorites information based on a card Id
	 */
	public CardItemFavorites findById(int cardId){
		CardItemFavorites userfav = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			userfav = new CardItemFavorites();
			connection = dc.getDBConnection();
			List<Integer> itemFavoritesList = new ArrayList<Integer>();
			String query = "select item_Code from card_item_favorites where cardId = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cardId);
			rs = ps.executeQuery();
			while(rs.next()){
				itemFavoritesList.add(rs.getInt("item_Code"));
			}
			System.out.println(itemFavoritesList);
			userfav.setItemCode(itemFavoritesList);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		return userfav;
	 }
}