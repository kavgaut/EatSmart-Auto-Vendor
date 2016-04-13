package eatSmartGUI;

/** An abstract class that provides setters and getters for all 
 * the sections built in the vm product.
 * @author Ramya Bangaluru Gopalakrishna
 */
/** An abstract class that provides setters and getters for all 
 * the sections built in the vm product.
 * @author Ramya Bangaluru Gopalakrishna
 */
public abstract class VM{
		
	public abstract String getVMId();
	
	public abstract void setCashCoinSlot(CashCoinSlotInterface ccs);
	
	public abstract void setCardSlot(CardSlotInterface cs);
	
	public abstract void setOperatorDisplay(OperatorDisplayInterface od);
	
	public abstract void setProductDispense(ProductDispenseInterface pd);
	
	public abstract void setCashCoinDispense(CashCoinDispenseInterface ccd);
	
	public abstract void setFoodItemSection(ItemSectionInterface fis);
	
	public abstract void setUserDisplay(UserDisplayInterface ud);
	
	public abstract CashCoinSlotInterface getCashCoinSlot();
	
	public abstract CardSlotInterface getCardSlot();
	
	public abstract OperatorDisplayInterface getOperatorDisplay();
	
	public abstract ProductDispenseInterface getProductDispense();
	
	public abstract CashCoinDispenseInterface getCashCoinDispense();
	
	public abstract ItemSectionInterface getFoodItemSection();
	
	public abstract UserDisplayInterface getUserDisplay();
}