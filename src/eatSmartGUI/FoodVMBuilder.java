package eatSmartGUI;
/** A class which instantiates a product class FoodVm which is a
 * type of vm that is built. it provides implementations to
 * builditemsection which builds an appropriate fooditem section
 * and provides implementation to the getVM method which returns 
 * the built vm(foodVM)
 * @author Ramya
 *
 */
public class FoodVMBuilder extends VMBuilder{
	
	public FoodVMBuilder(String vmId){
		this.vm = new FoodVM(vmId);
	}
	
	public void buildItemSection(){
		ItemSectionInterface fis = new FoodItemSection();
		fis.fetchFoodItemsList(this.vm.getVMId());
		vm.setFoodItemSection(fis);
	}
	
	public VM getVM(){
		return this.vm;
	}
}