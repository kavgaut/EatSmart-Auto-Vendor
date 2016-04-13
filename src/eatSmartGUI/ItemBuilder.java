package eatSmartGUI;
import java.util.ArrayList;

public class ItemBuilder {
	
	ItemManager itemMang;
	public ArrayList<Integer> perishableItemList;
	public ArrayList<Integer> packagedItemList;
	public ArrayList<Integer> drinksItemList;
	
	public ItemBuilder(ItemManager itemManager){
		itemMang = itemManager;
	}
	
	public void loadItems() {
		
		ItemDaoImpl dao = new ItemDaoImpl();
		Item perishableItems, packagedItems, drinkItems;
		
		this.perishableItemList = dao.findByItemType("Perishable");
		System.out.println(this.perishableItemList);
		for(int i = 0; i < perishableItemList.size(); ++i){
			perishableItems = new PerishableFoods();
			perishableItems = dao.findById(perishableItemList.get(i), perishableItems);
			itemMang.registerItem(perishableItems);
		}
		
		this.packagedItemList = dao.findByItemType("Packaged");	
		for(int i = 0; i < packagedItemList.size(); ++i){
			packagedItems = new PackagedFood();
			packagedItems = dao.findById(packagedItemList.get(i), packagedItems);
			itemMang.registerItem(packagedItems);
		}
		
		this.drinksItemList = dao.findByItemType("Drinks");	
		for(int i = 0; i < drinksItemList.size(); ++i){
			drinkItems = new Drinks();
			drinkItems = dao.findById(drinksItemList.get(i), drinkItems);
			itemMang.registerItem(drinkItems);
		}
	}
}
