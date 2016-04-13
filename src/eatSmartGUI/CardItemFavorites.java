package eatSmartGUI;

import java.util.List;


/** creates a class UserItemFavorites which contains the card id
 * and item code. This class is used when a user adds a 
 * particular item into his/her favorites list
 * @author Ramya Bangaluru Gopalakrishna
 */
public class CardItemFavorites{
	private int cardId;
	private List<Integer> itemCode;
	
	/** To set the card id
	 * @param cardId: card Id
	 */
	public void setcardId(int cardId){
		this.cardId = cardId;
	}
	
	/** To get the card Id
	 * @return: card Id
	 */
	public int getCardId(){
		return cardId;
	}
	
	/** To set the item code
	 * @param itemCode: item code
	 */
	public void setItemCode(List<Integer> itemCode){
		this.itemCode = itemCode;
	}
	
	/** to get the item code
	 * @return: item code
	 */
	public List<Integer> getItemCode(){
		return itemCode;
	}
}
