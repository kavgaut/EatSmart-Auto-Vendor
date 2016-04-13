package eatSmartGUI;
import java.util.List;


public interface SmartAutoVendor {
	public void addVendingMachine(SmartAutoVendor VM);
	public void removeVendingMachine(String VMId); //just set the status inactive
	public List<SmartAutoVendor> getChildren();
	public double calculateRevenue();
	public void showAllContents();
	public void setStatus(String vmStatus);
	public void setVMInactive();
	public String getVendorID();
	public void setRevenue(double vmRevenue);
	public void setVMSaleQty(int vmSaleQty);
}
