package eatSmartGUI;

public interface CashDao {
	public double getCashDetails(String VMID);
	public void insertCash(String vmID, double amount);

}
