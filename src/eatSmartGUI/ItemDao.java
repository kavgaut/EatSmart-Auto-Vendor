package eatSmartGUI;

import java.util.List;

/** Dao interface that defines the methods to insert, update and to 
 * find records of an item with specific item Id
 * @author Ramya Bangaluru Gopalakrishna
 */
public interface ItemDao{
	public void insertItem(String itemName, String itemType, int calorieValue, String ingredientInfo, 
			String sugarContent, String carbContent, String cholestrolContent, String fatContent, String ProteinContent,
			String glutenFree, String lowFat, String lowSugar, double itemPrice);
	public void updatedItemNutritionalInfo(Item itemObj);
	public void updateItemPrice(int itemCode, double itemPrice);
	public Item findById(int itemId, Item itemobj);
	public Item findById(int itemId);
	public List<Integer> findItemByParams(String gluten, String lowFat, String lowSugar, int low, int high);
	public List<Integer> getAllItemCodes();
}