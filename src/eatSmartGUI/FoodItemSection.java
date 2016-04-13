package eatSmartGUI;
import java.util.ArrayList;

/** creates a fooditem section of the final product vm
 * @author Ramya Bangaluru Gopalakrishna
 */
public class FoodItemSection implements ItemSectionInterface{
	Item item;
	ArrayList<Integer> vmItemList;
	ArrayList<Item> vmItemsList = new ArrayList<Item>();
	VMItemDaoImpl vmItemDAO;
	
	public ArrayList<Item> fetchFoodItemsList(String vmId){
		vmItemDAO = new VMItemDaoImpl();
		
		vmItemList = null;
		
		//creates an instance of item manager and item builder
		// and registers all the items objects in a map. whenever an
		//item instance is required, a clone of the item from map 
		//is returned.
		ItemManager itemManager = new ItemManager();
		ItemBuilder itemBuilder = new ItemBuilder(itemManager);
		
		//To create all the item objects of the database
		itemBuilder.loadItems();
		
		//To get the item id's based on a particular VM ID
		vmItemList = vmItemDAO.findByVMId(vmId);
		
		//To iterate through the item list and to get the clones
		//of the appropriate items from the prototype's Item Manager
		for(int i = 0; i < vmItemList.size(); ++i){
			item = itemManager.getItem(vmItemList.get(i));
			vmItemsList.add(item);
		}
		System.out.println(vmItemsList);
		return vmItemsList;
		
	}
	
	public ArrayList<Integer> getItemIdList(String vmId){
		vmItemList = null;
		//To get the item id's based on a particular VM ID
		vmItemList = vmItemDAO.findByVMId(vmId);
		return vmItemList;
	}
	
	public ArrayList<Integer> getActiveItemIdList(String vmId){
		vmItemList = null;
		//To get only the Active status item id's based on a particular VM ID 
		vmItemList = vmItemDAO.findActiveItemsByVMId(vmId);
		return vmItemList;
	}
	
}