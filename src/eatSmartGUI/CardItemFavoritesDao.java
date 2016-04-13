package eatSmartGUI;

/** Dao interface that defines the methods to insert and to 
 * find records of a user's item favorites with specific card Id
 * @author Ramya Bangaluru Gopalakrishna
 */
public interface CardItemFavoritesDao{
	public void insertItemFavorites(int cardId, int ItemCode);
	public CardItemFavorites findById(int cardId);
}