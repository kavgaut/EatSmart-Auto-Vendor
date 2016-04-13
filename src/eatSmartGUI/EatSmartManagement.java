package eatSmartGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EatSmartManagement {
	
	ArrayList<SmartAutoVendor> monitoringStations;
	public double totalRevenue;
	public SmartAutoVendor vm1;
	public SmartAutoVendor vm2;
	public SmartAutoVendor vm3;
	public SmartAutoVendor vm4;
	public SmartAutoVendor ms1;
	public SmartAutoVendor ms2;
	VMItemDaoImpl dao;
	
	public EatSmartManagement(){
		dao = new VMItemDaoImpl();
		monitoringStations = new ArrayList<> ();
		
		vm1 = new VendingMachine("VM1");
		vm2 = new VendingMachine("VM2");
		vm3 = new VendingMachine("VM3");
		vm4 = new VendingMachine("VM4");
		
		
		/*
		 * Code here to fetch VM wise revenue, vm wise sale qty and vm status and other vm wise params from DB, then set as below
		 */

		vm1.setVMSaleQty(dao.getVMSaleQtyById("VM1"));
		vm2.setVMSaleQty(dao.getVMSaleQtyById("VM2"));
		vm3.setVMSaleQty(dao.getVMSaleQtyById("VM3"));
		vm4.setVMSaleQty(dao.getVMSaleQtyById("VM4"));
	
		
		vm1.setStatus(dao.getVMStatusById("VM1"));
		vm2.setStatus(dao.getVMStatusById("VM2"));
		vm3.setStatus(dao.getVMStatusById("VM3"));
		vm4.setStatus(dao.getVMStatusById("VM4"));
		
		vm1.setRevenue(dao.getVMRevenueById("VM1"));
		vm2.setRevenue(dao.getVMRevenueById("VM2"));
		vm3.setRevenue(dao.getVMRevenueById("VM3"));
		vm4.setRevenue(dao.getVMRevenueById("VM4"));
		
		ms1 = new MonitoringStation("MS1");
		ms2 = new MonitoringStation("MS2");
		
		ms1.addVendingMachine(vm1);
		ms1.addVendingMachine(vm2);
		ms2.addVendingMachine(vm3);
		ms2.addVendingMachine(vm4);
		
		System.out.println("The total revenue of MS1 is "+ ms1.calculateRevenue());
		System.out.println("The total revenue of MS2 is "+ ms2.calculateRevenue());
		
		//ms1.removeVendingMachine("VM2");

		
		totalRevenue = ms1.calculateRevenue() + ms2.calculateRevenue();
		
		ms1.showAllContents();
		ms2.showAllContents();
		
		//Add the created Monitoring stations to a list
		monitoringStations.add(ms1);
		monitoringStations.add(ms2);
		
		//then calculate total revenue too with the method as below
	}
	
	public double getTotalRevenue(){
		
		return totalRevenue;
	}
	
	public ArrayList<SmartAutoVendor> getMSList(){
		return monitoringStations;
	}
	
	public List<SmartAutoVendor> getVMList(SmartAutoVendor msObj){
		return msObj.getChildren();
		
	}
	
	public double getVMorMSRevenue(SmartAutoVendor vmORmsObj){
		return vmORmsObj.calculateRevenue();
	}
	
	public double calculateOverAllRevenue() {
		double totalOverAllRevenue = 0.0;
		Iterator<SmartAutoVendor> MSIterator = monitoringStations.iterator();
        while (MSIterator.hasNext()) {
        	SmartAutoVendor MS = MSIterator.next();
        	totalOverAllRevenue += MS.calculateRevenue();
        }
        return totalOverAllRevenue;
	}
	
}
