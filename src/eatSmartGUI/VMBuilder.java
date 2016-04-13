package eatSmartGUI;
/** An abstract class that provides implementation to the build methods
 * which build all the sections of a vm. The methods, getVM and 
 * buildItenSection are abstract which are implemented by the sub classes
 * @author Ramya Bangaluru Gopalakrishna
 */
public abstract class VMBuilder{
	protected VM vm;
	
	public void buildCardSlot(){
		CardSlot cs = new CardSlot();
		vm.setCardSlot(cs);
	}
	
	public void buildCashSlot(){
		CashCoinSlot cas = new CashCoinSlot();
		vm.setCashCoinSlot(cas);
	}
	
	public void buildProductDispense(){
		ProductDispense pd = new ProductDispense();
		vm.setProductDispense(pd);
	}
	
	public void buildUserDisplay(){
		UserDisplay ud = new UserDisplay();
		vm.setUserDisplay(ud);
	}
	
	public void buildCashCoinDispense(){
		CashCoinDispense cd = new CashCoinDispense();
		vm.setCashCoinDispense(cd);
	}
	
	public void buildOperatorDisplay(){
		OperatorDisplay od = new OperatorDisplay();
		vm.setOperatorDisplay(od);
	}
	
	public abstract void buildItemSection();
	public abstract VM getVM();
}