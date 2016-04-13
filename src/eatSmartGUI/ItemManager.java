package eatSmartGUI;
import java.util.HashMap;

public class ItemManager{
	
	HashMap<Integer, Item> itemMap = new HashMap<Integer, Item>();
	
	public void registerItem(Item item){
		itemMap.put(item.getItemCode(), item);
	}
		
	public Item getItem(int id){
		Item item = itemMap.get(id);
		return (Item) item.clone();
	}
}
