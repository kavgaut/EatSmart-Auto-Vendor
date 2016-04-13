package eatSmartGUI;

import java.util.List;

import javax.swing.JPanel;

public interface PanelColleague {
	
	public void sendPage(JPanel newPage);
	public void setLabels(double amoutDue, double balance, int points, List<Integer> itemCodeList);
	public void setLabel(double CurrentBalance);
	public void storeValues(double totalAmtDue, List<Integer> itemList);
	public void setLabels(double newBal, int pointsRem);
	public void setLabels(String itemCode);
	public void setLabels(double totalAmtDue,
			List<Integer> itemCodeList, double newBal);
	

}
