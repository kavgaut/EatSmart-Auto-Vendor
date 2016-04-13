package eatSmartGUI;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class MonitoringStation implements SmartAutoVendor {
	private String msId;
	private String adminId;
	private List<SmartAutoVendor> machines;
	private double totalMSRevenue;
	private double totalMSSaleQty;
	
	public MonitoringStation(String msId){
		this.msId = msId;
		//this.adminId = adminId;
		machines = new CopyOnWriteArrayList<>();
	}
	
	@Override
	public void addVendingMachine(SmartAutoVendor VM) {
		machines.add(VM);
		
	}

	@Override
	public void removeVendingMachine(String VMid) {
		Iterator<SmartAutoVendor> VMIterator = getChildren().iterator();
        while (VMIterator.hasNext()) {
        	SmartAutoVendor VM = VMIterator.next();
            if (VM.getVendorID().equals(VMid)) {
                VM.setVMInactive();
            }
            if (VM.getChildren() != null) {
                VM.removeVendingMachine(VMid);
            }
            
        }
	}

	@Override
	public List<SmartAutoVendor> getChildren() {
		return machines;
	}

	@Override
	public double calculateRevenue() {
		totalMSRevenue = 0.0;
		Iterator<SmartAutoVendor> VMIterator = getChildren().iterator();
        while (VMIterator.hasNext()) {
        	SmartAutoVendor VM = VMIterator.next();
        	totalMSRevenue += VM.calculateRevenue();
        }
        return totalMSRevenue;
	}

	@Override
	public void showAllContents() {
		System.out.println("---------------------------------");
        System.out.println("Vendor ID: " + getVendorID());
        System.out.println("---------------------------------");
        //System.out.println("\n------------Inside " +getResourceID() + "-----------");

        Iterator<SmartAutoVendor> VMIterator = machines.iterator();
        while (VMIterator.hasNext()) {
        	SmartAutoVendor VM = VMIterator.next();
            VM.showAllContents();
        }
	}

	@Override
	public void setVMInactive() {
		// This method is applicable to a leaf class and hence need not be defined here
		
	}

	@Override
	public String getVendorID() {
		return msId;
	}

	@Override
	public void setRevenue(double revenue) {
		// This method is applicable to a leaf class and hence need not be defined here
		
	}

	@Override
	public void setStatus(String vmStatus) {
		// This method is applicable to a leaf class and hence need not be defined here
		
	}

	@Override
	public void setVMSaleQty(int vmSaleQty) {
		// This method is applicable to a leaf class and hence need not be defined here
		
	}

}
