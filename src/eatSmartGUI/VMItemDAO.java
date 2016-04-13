package eatSmartGUI;

import java.util.List;

public interface VMItemDAO {
	public List<VMItem> getAllVMItem();
    public VMItem getVMItem(String vmItem);
    public void insertVMItem(VMItem vmItem);
    public void updateRackQty(int itemId, String vmId, int rackQty);
    public void updateSaleQty(int itemId, String vmId,int saleQty);
    public void updateRevenue(int itemId, String vmId,double revenue);
    public void deleteVMItem(int itemId, String vmId);
    public List<Integer> findByVMId(String vmId);
    public void updateOldItemNewItem(int oldItem, int newItem, String vmItem);
	List<Integer> getPopularVMItemIds();
	public int getVMSaleQtyById(String string);
}

