package eatSmartGUI;

import java.util.Date;

/** creates a class card which contains the card id,
 * card balance, card points and the purchased date information
 * This class is used for a particular payment mode
 * @author Ramya Bangaluru Gopalakrishna
 */
public class Card{
	private int cardId;
	private double cardBalance;
	private int points;
	private Date cardPurchaseDate;
	
	public Card(){
		this.cardId = 0;
		this.cardBalance = 0.0;
		this.points = 0;
	}
	
	/** To set the card Id
	 * @param cId: card Id
	 */
	public void setCardId(int cId){
		this.cardId = cId;
	}
	
	/** To get the card Id
	 * @return: card Id
	 */
	public int getCardId(){
		return cardId;
	}
	
	/** To set the card balance
	 * @param cBalance: card balance
	 */
	public void setCardBalance(double cBalance){
		this.cardBalance = cBalance;
	}
	
	/** To get the card balance
	 * @return: card balance
	 */
	public double getCardBalance(){
		return cardBalance;
	}
	
	/** To set the points on the card
	 * @param cPoints: pointed added to the card
	 */
	public void setCardPoints(int cPoints){
		this.points = cPoints;
	}
	
	/** To get the points added to the card
	 * @return: card points
	 */
	public int getCardPoints(){
		return points;
	}
	
	/** To set the purchase date of the card
	 * @param cPurchaseDate: card purchased date
	 */
	public void setCardPurchaseDate(Date cPurchaseDate){
		this.cardPurchaseDate = cPurchaseDate;
	}
	
	/** to get the card's purchased date
	 * @return: card purchased date
	 */
	public Date getCardPurchaseDate(){
		return cardPurchaseDate;
	}
}