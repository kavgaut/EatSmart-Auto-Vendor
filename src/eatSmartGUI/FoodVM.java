package eatSmartGUI;

/** The Final concrete vm product food vm which will have references 
 * to all different slots created in the vm
 * @author Ramya Bangaluru Gopalakrishna
 * @author kavyagautam
 */
public class FoodVM extends VM {
	
	protected String vmId;
	private CashCoinSlotInterface cashCoinSlot;
	private CardSlotInterface cardSlot;
	private OperatorDisplayInterface opDisplay;
	private ProductDispenseInterface prodDispense;
	private CashCoinDispenseInterface cashCoinDispense;
	private ItemSectionInterface foodItemSection;
	private UserDisplayInterface userDisplay;
	
	public FoodVM(String vmId){
		this.vmId = vmId;
	}
	
	public void setCashCoinSlot(CashCoinSlotInterface ccs){
		this.cashCoinSlot = ccs;
	}
	
	public void setCardSlot(CardSlotInterface cs){
		this.cardSlot = cs;
	}
	
	public void setOperatorDisplay(OperatorDisplayInterface od){
		this.opDisplay = od;
	}
	
	public void setProductDispense(ProductDispenseInterface pd){
		this.prodDispense = pd;
	}
	
	public void setCashCoinDispense(CashCoinDispenseInterface ccd){
		this.cashCoinDispense = ccd;
	}
	
	public void setFoodItemSection(ItemSectionInterface fis){
		this.foodItemSection = fis;
	}
	
	public void setUserDisplay(UserDisplayInterface ud){
		this.userDisplay = ud;
	}
	
	public CashCoinSlotInterface getCashCoinSlot(){
		return cashCoinSlot;
	}
	
	public CardSlotInterface getCardSlot(){
		return cardSlot;
	}
	
	public OperatorDisplayInterface getOperatorDisplay(){
		return opDisplay;
	}
	
	public ProductDispenseInterface getProductDispense(){
		return prodDispense;
	}
	
	public CashCoinDispenseInterface getCashCoinDispense(){
		return cashCoinDispense;
	}
	
	public ItemSectionInterface getFoodItemSection(){
		return foodItemSection;
	}
	
	public UserDisplayInterface getUserDisplay(){
		return userDisplay;
	}

	@Override
	public String getVMId() {
		
		return vmId;
	}
	
	
	
}