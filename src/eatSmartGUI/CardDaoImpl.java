package eatSmartGUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Card Dao implementation class that connects to the database and
 * provides implementation to insert, update and find records of a
 * card table in the database
 * @author Ramya Bangaluru Gopalakrishna
 */
public class CardDaoImpl implements CardDao{
	public static DBUtil dc;
	
	/** Constructor which gets an instance of a 
	 * DatabaseConnection class which is a singleton
	 */
	public CardDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	/** insert method to add a new card entry into the database
	 */
	public void insertCard(double balance, int points, MemberType m){
		Connection connection = null;
		PreparedStatement ps = null;
		PreparedStatement pst = null;
		ResultSet seqRs = null;
		long genCardId = 0L;
		int card = 0;
		try{
			connection = dc.getDBConnection(); 
			String sqlIdentifier = "select card_seq.NEXTVAL from dual";
			pst = connection.prepareStatement(sqlIdentifier);
			seqRs = pst.executeQuery();
			if(seqRs.next())
				genCardId = seqRs.getLong(1);
			String query = "Insert into Card values(?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			card = (int) genCardId;
			ps.setInt(1, card);
			ps.setDouble(2, balance);
			ps.setInt(3, points);
			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			ps.executeUpdate();
			System.out.println("card " + card + " inserted");
			m.getCard().setCardId(card);
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
	/** Update method to update the balance information
	 * of the card in the database
	 */
	public void updateCardBalance(int cardId, double cardBalance){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = dc.getDBConnection();
			String query = "update Card set balance = '" + cardBalance + "' where cardId = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cardId);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
	/** Update method to update the points information
	 * of the card in the database
	 */
	public void updateCardPoints(int cardId, double cardPoints){
		Connection connection = null;
		PreparedStatement ps = null;
		try{
			connection = dc.getDBConnection();
			String query = "update Card set points = '" + cardPoints + "' where cardId = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cardId);
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(null, null, ps, connection);
		}
	}
	
	/** method to find the card information based on a card Id
	 * @return 
	 */
	public Card findById(int cardId){
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Card cardObj = new Card();
		try{
			
			connection = dc.getDBConnection();
			String query = "select * from card where cardId = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, cardId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				cardObj.setCardId(rs.getInt("CARDID"));
				System.out.println(cardObj.getCardId());
				cardObj.setCardPoints(rs.getInt("POINTS"));
				System.out.println(cardObj.getCardPoints());
				cardObj.setCardBalance(rs.getDouble("BALANCE"));
				System.out.println(cardObj.getCardBalance());
				cardObj.setCardPurchaseDate(rs.getDate("CardPurchaseDate"));
				System.out.println(cardObj.getCardPurchaseDate());
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.closeConnection(rs, null, ps, connection);
		}
		
		return cardObj;
	}
}