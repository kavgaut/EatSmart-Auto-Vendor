package eatSmartGUI;

import java.util.HashMap;
import java.util.List;



public class CardItemHistory {
	int cardId;
	List<Integer> itemCode;
	HashMap<Integer, java.sql.Date> itemPurchaseDate;
	HashMap<Integer, String> itemPurchaseStringDate;
	HashMap<Integer, List<String>> itemPurchaseListDate;
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public List<Integer> getItemCode() {
		return itemCode;
	}
	public void setItemCode(List<Integer> itemCode) {
		this.itemCode = itemCode;
	}
	public HashMap<Integer, java.sql.Date> getItemPurchaseDate() {
		return itemPurchaseDate;
	}
	public void setItemPurchaseDate(HashMap<Integer, java.sql.Date> dateMap) {
		this.itemPurchaseDate = dateMap;
	}
	public HashMap<Integer, String> getItemPurchaseStringDate() {
		return itemPurchaseStringDate;
	}
	public void setItemPurchaseStringDate(
			HashMap<Integer, String> itemPurchaseStringDate) {
		this.itemPurchaseStringDate = itemPurchaseStringDate;
	}
	public HashMap<Integer, List<String>> getItemPurchaseListDate() {
		return itemPurchaseListDate;
	}
	public void setItemPurchaseListDate(
			HashMap<Integer, List<String>> itemPurchaseListDate) {
		this.itemPurchaseListDate = itemPurchaseListDate;
	}
}
