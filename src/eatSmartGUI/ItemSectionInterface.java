package eatSmartGUI;
import java.util.ArrayList;
import java.util.List;

/** An interface to create an item section in the vm
 * @author Ramya Bangaluru Gopalakrishna
 */
public interface ItemSectionInterface{
	public ArrayList<Item> fetchFoodItemsList(String vmId);

	public ArrayList<Integer> getItemIdList(String vMID);

	public List<Integer> getActiveItemIdList(String vmId);
}