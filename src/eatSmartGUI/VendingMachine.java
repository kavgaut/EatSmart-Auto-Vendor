package eatSmartGUI;
import java.util.List;


public class VendingMachine implements SmartAutoVendor  {
	private String VMId;
	//private List<Item> items = new ArrayList<Item>();
	private String OpId;
	private double vmRevenue;
	private String vmStatus; 
	private int vmSaleQty;
	
	public VendingMachine(String VMId){
		this.VMId = VMId;
		
	}
	
	@Override
	public void addVendingMachine(SmartAutoVendor VM) {
		// This is a leaf class w.r.t SmartAutoVendor, so this method is not applicable here	
	}

	@Override
	public void removeVendingMachine(String VMId) {
		// This is a leaf class w.r.t SmartAutoVendor, so this method is not applicable here
		
	}

	@Override
	public List<SmartAutoVendor> getChildren() {
		// This is a leaf class w.r.t SmartAutoVendor, so this method is not applicable here
		return null;
	}

	@Override
	public double calculateRevenue() {
		// This method should give a call to the database
		//database will fetch direct summed up revenue for that machine id and store it in revenue here
		return vmRevenue;
	}

	@Override
	public void showAllContents() {
		System.out.println("Vending machine with ID as " + VMId + " is " + vmStatus + "\n");
	}
	
	@Override
	public void setVMInactive(){
		this.vmStatus = "Inactive";
	}

	@Override
	public String getVendorID() {
		return VMId;
	}

	@Override
	public void setRevenue(double revenue) {
		this.vmRevenue = revenue;
	}

	@Override
	public void setStatus(String vmStatus) {
		this.vmStatus = vmStatus;
	}

	@Override
	public void setVMSaleQty(int vmSaleQty) {
		this.vmSaleQty = vmSaleQty;
		
	}
	
}
