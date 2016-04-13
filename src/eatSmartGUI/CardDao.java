package eatSmartGUI;


/** Dao interface that defines the methods to insert, update and to 
 * find records of a card with specific card Id
 * @author Ramya Bangaluru Gopalakrishna
 */
public interface CardDao{
	public void insertCard(double balance, int points, MemberType m);
	public void updateCardBalance(int cardId, double cardBalance);
	public void updateCardPoints(int cardId, double cardPoints);
	public Card findById(int cardId);
	
}