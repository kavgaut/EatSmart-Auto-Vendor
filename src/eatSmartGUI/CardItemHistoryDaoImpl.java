package eatSmartGUI;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardItemHistoryDaoImpl implements CardItemHistoryDao{
	public static DBUtil dc;
	
	public CardItemHistoryDaoImpl(){
		dc = DBUtil.getInstance();
	}
	
	@Override
	public List<CardItemHistory> getAllCardIds() {
		List<CardItemHistory> cardItemHistoryList = null;
		return cardItemHistoryList;
	}

	@Override
	public CardItemHistory getCardId(int cardId) {
		Connection connection = null;
		CardItemHistory cardItemHistory = null;
		PreparedStatement cardItemHistoryPrepSt = null;
		ResultSet cardItemHistoryResultSet = null;
		PreparedStatement itemDateHistoryPrepSt = null; 
		ResultSet itemDateHistoryResultSet = null;
		int cardItemHistoryId; 
		List<Integer> itemCodeList = new ArrayList<Integer>();
		HashMap<Integer,Date> dateMap = new HashMap<Integer,Date>();
		HashMap<Integer,String> dateStringMap = new HashMap<Integer,String>();
		HashMap<Integer,List<String>> dateItemMap = new HashMap<Integer,List<String>>();
		try{
		connection = dc.getDBConnection(); 
		//String cardItemQry = "select distinct(ITEM_CODE), CARDID, ItemPurchaseDate from CARD_ITEM_HISTORY where CARDID = ?";
		String cardItemQry = "select distinct(ITEM_CODE) from CARD_ITEM_HISTORY where CARDID = ?";
		cardItemHistory = new CardItemHistory();
		cardItemHistoryPrepSt = connection.prepareStatement(cardItemQry);
		cardItemHistoryPrepSt.setInt(1, cardId);
		cardItemHistoryResultSet = cardItemHistoryPrepSt.executeQuery();
		while(cardItemHistoryResultSet.next()){
			itemCodeList.add(cardItemHistoryResultSet.getInt(1));
			//cardItemHistoryId = cardItemHistoryResultSet.getInt(2);
			//cardItemHistory.setCardId(cardItemHistoryId);
			//dateMap.put(cardItemHistoryId, cardItemHistoryResultSet.getTimestamp(3));
			cardItemHistory.setCardId(cardId);
		}
		cardItemHistory.setItemCode(itemCodeList);
		cardItemHistory.setItemPurchaseDate(dateMap);
		/*String itemDateQry = "select item_code , listagg(itempurchasedate, ',') within group(order by item_code) AS emp from CARD_ITEM_HISTORY group by item_code ";
		itemDateHistoryPrepSt = connection.prepareStatement(itemDateQry);
		itemDateHistoryResultSet = itemDateHistoryPrepSt.executeQuery();
		while(itemDateHistoryResultSet.next()){
			int itemDateId = itemDateHistoryResultSet.getInt(1);
			dateStringMap.put(itemDateId, itemDateHistoryResultSet.getString(2));
		}
		cardItemHistory.setItemPurchaseStringDate(dateStringMap);*/
		String itemDateQry = "select itempurchasedate from CARD_ITEM_HISTORY where CARDID = ? and ITEM_CODE = ?";
		List<String> dateList = null;
		for(int i=0;i<itemCodeList.size();i++){
			dateList = new ArrayList<String>();
			itemDateHistoryPrepSt = connection.prepareStatement(itemDateQry);
			itemDateHistoryPrepSt.setInt(1, cardId);
			itemDateHistoryPrepSt.setInt(2, itemCodeList.get(i));
			itemDateHistoryResultSet = itemDateHistoryPrepSt.executeQuery();
			while(itemDateHistoryResultSet.next()){
				dateList.add(itemDateHistoryResultSet.getTimestamp(1).toString());
			}
			dateItemMap.put(itemCodeList.get(i), dateList);
		}
		//cardItemHistory.setItemPurchaseStringDate(dateStringMap);
		cardItemHistory.setItemPurchaseListDate(dateItemMap);
		}catch(SQLException e){
			e.getMessage();
		}
		return cardItemHistory;
	}

	@Override
	public void updateCard(CardItemHistory cardInfo) {
		Connection connection = null;
		PreparedStatement cardItemHistoryPrepSt = null;
		try{
		connection = dc.getDBConnection(); 
		String cardItemQry = "update CARD_ITEM_HISTORY set  ITEM_CODE   = ? , ItemPurchaseDate = ?   where CARDID  = ?";
		cardItemHistoryPrepSt = connection.prepareStatement(cardItemQry);
		for(int i =0; i<cardInfo.getItemCode().size(); i++){
			cardItemHistoryPrepSt.setInt(1, cardInfo.getItemCode().get(i));
			cardItemHistoryPrepSt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
		}
		cardItemHistoryPrepSt.setInt(3, cardInfo.getCardId());
		cardItemHistoryPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void deleteCard(CardItemHistory cardInfo) {
		Connection connection = null;
		PreparedStatement cardItemHistoryPrepSt = null;
		try{
		connection = dc.getDBConnection(); 
		String cardItemQry = "delete from CARD_ITEM_HISTORY where CARDID  = ?";
		cardItemHistoryPrepSt = connection.prepareStatement(cardItemQry);
		cardItemHistoryPrepSt.setInt(1, cardInfo.getCardId());
		cardItemHistoryPrepSt.executeUpdate();
		}catch(SQLException e){
			e.getMessage();
		}
	}

	@Override
	public void insertCard(CardItemHistory cardInfo) {
		Connection connection = null;
		PreparedStatement cardItemHistoryPrepSt = null;
		Date d = new java.sql.Date(new java.util.Date().getTime());
		try{
		connection = dc.getDBConnection(); 
		String cardItemQry = "insert into CARD_ITEM_HISTORY(CARDID,ITEM_CODE, ItemPurchaseDate ) values (?,?,?)";
		cardItemHistoryPrepSt = connection.prepareStatement(cardItemQry);
		for(int i =0; i<cardInfo.getItemCode().size(); i++) {
		cardItemHistoryPrepSt.setInt(1, cardInfo.getCardId());
		cardItemHistoryPrepSt.setInt(2, cardInfo.getItemCode().get(i));
		cardItemHistoryPrepSt.setDate(3, d);
		cardItemHistoryPrepSt.executeUpdate();
		}
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
}
