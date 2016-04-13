package eatSmartGUI;

import java.util.HashMap;
import java.util.List;

public class VMItem {
	
	private String vmID;
	private String vm_status;
	private List<Integer> itemId;
	private HashMap<Integer,String> item_status;
	private HashMap<Integer,Integer> rackQty;
	private String opID;
	private String msID;
	private HashMap<Integer,Integer> saleQty;
	private HashMap<Integer,Double> revenueMap;
	public String getVmID() {
		return vmID;
	}
	public void setVmID(String vmID) {
		this.vmID = vmID;
	}
	public String getVm_status() {
		return vm_status;
	}
	public void setVm_status(String vm_status) {
		this.vm_status = vm_status;
	}
	public List<Integer> getItemId() {
		return itemId;
	}
	public void setItemId(List<Integer> itemId) {
		this.itemId = itemId;
	}
	public HashMap<Integer,String> getItem_status() {
		return item_status;
	}
	public void setItem_status(HashMap<Integer,String> itemStatusMap) {
		this.item_status = itemStatusMap;
	}
	public HashMap<Integer,Integer> getRackQty() {
		return rackQty;
	}
	public void setRackQty(HashMap<Integer,Integer> rackQty) {
		this.rackQty = rackQty;
	}
	public String getOpID() {
		return opID;
	}
	public void setOpID(String opID) {
		this.opID = opID;
	}
	public String getMsID() {
		return msID;
	}
	public void setMsID(String msID) {
		this.msID = msID;
	}
	public HashMap<Integer,Integer> getSaleQty() {
		return saleQty;
	}
	public void setSaleQty(HashMap<Integer,Integer> saleQty) {
		this.saleQty = saleQty;
	}
	public HashMap<Integer,Double> getRevenue() {
		return revenueMap;
	}
	public void setRevenue(HashMap<Integer,Double> revenue) {
		revenueMap = revenue;
	}
	
	
}
