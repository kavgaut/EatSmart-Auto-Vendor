package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class UDMemberPage4_Popular extends JPanel implements PanelColleague{
	/**
	 * 
	 */
	private JLabel lblValidation;
	private static final long serialVersionUID = 1L;
	private MainPageGUI mainFrame;
	private JList<Integer> popular_item_list;
	private boolean isItemSelected;
	private List<Integer> itemCodes;
	VMItemDaoImpl vmDao =  null;
	public boolean isItemInVM = false;
	public UDMemberPage4_Popular(MainPageGUI mainFrame){
		
		/////CODE TO GET THE MOST POPULAR ITEMS from the DAO SND LOAD JLIST AS SOON AS THIS PAGE IS LOADED - SHOULD BE HERE
		/*
		 * 
		 */
		//Create the JLIST
		popular_item_list = new JList<>();
		itemCodes = new ArrayList<Integer>();
		vmDao = new VMItemDaoImpl();
		popular_item_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//I dont think this is enuf, just clicking is not enuf, some row must be selected- CHECK THIS
				isItemSelected = true;
				//System.out.println(isItemSelected);
			}
		});
		popular_item_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Initialization
		isItemSelected = false;
		this.mainFrame = mainFrame;
		
		//Initialize the popular list from DB and the DefaultListModel
		itemCodes = vmDao.getPopularVMItemIds();
		System.out.println(itemCodes.size());
		DefaultListModel<Integer> dlm = new DefaultListModel<>();
		for (int i =0; i<itemCodes.size(); i++){
			dlm.addElement(itemCodes.get(i));
		}
		popular_item_list.setModel(dlm);
		//End of populating JList
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		JLabel popular_title_label = new JLabel("EatSmart's Most Popular Items");
		popular_title_label.setBorder(BorderFactory.createEtchedBorder(1));
		popular_title_label.setBackground(Color.WHITE);
		popular_title_label.setOpaque(true);
		popular_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblPopularList = new JLabel("Popular Item List");
		lblPopularList.setBorder(BorderFactory.createLineBorder(Color.black,1));
		lblPopularList.setBackground(Color.WHITE);
		lblPopularList.setHorizontalAlignment(SwingConstants.CENTER);
		lblPopularList.setOpaque(true);
		
		JButton btnBuyThisItem = new JButton("Buy This Item");
		btnBuyThisItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/*if (isItemSelected){
				//code to check and redirect to buy page
			}*/
				System.out.println("item index in list-->"+popular_item_list.getSelectedIndex());
				if(popular_item_list.getSelectedIndex() != -1){
					//System.out.println(popular_item_list.getSelectedValue());
					for(int i=0; i<mainFrame.vMItemList.size();i++){
						int itemcode = mainFrame.vMItemList.get(i);
						if(itemcode==popular_item_list.getSelectedValue()){
							isItemInVM = true;
						}
					}
					if(isItemInVM){
						if(mainFrame.userMemberClick){
							sendPage(mainFrame.createMemberPage4Buying(popular_item_list.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0));
							//mainFrame.createMemberPage4Buying(mainFrame.getPage4Popular(),popular_item_list.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0);
						}
						else{
							//mainFrame.createNonMemberPage4_Buying(mainFrame.getPage4Popular(), popular_item_list.getSelectedValue().toString());
							sendPage(mainFrame.createNonMemberPage4_Buying(popular_item_list.getSelectedValue().toString()));
						}
					}else{
						System.out.println("Item not a part of this VM.");
						lblValidation.setText("Selected item is not part of this VM");
					}
					
				}//add validation text in else
				else lblValidation.setText("Select an item to buy");
			}
		});
		btnBuyThisItem.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JButton btnGoBackTo = new JButton("Go Back to Main Menu");
		btnGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4Popular());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		
		lblValidation = new JLabel("  ");
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblValidation.setForeground(Color.RED);

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(popular_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(popular_item_list, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBuyThisItem)
								.addComponent(btnGoBackTo)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblPopularList, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblValidation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(popular_title_label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPopularList)
							.addGap(18)
							.addComponent(popular_item_list, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(53)
							.addComponent(btnBuyThisItem)
							.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
							.addComponent(btnGoBackTo)
							.addGap(15)))
					.addGap(31)
					.addComponent(lblValidation)
					.addGap(24))
		);
		
		setLayout(groupLayout);
		
	}
	
	public void sendPage(JPanel newPage){
		mainFrame.replacePage(this,newPage);
	}

	@Override
	public void setLabels(double amoutDue, double balance, int points,
			List<Integer> itemCodeList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabel(double CurrentBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeValues(double totalAmtDue, List<Integer> itemList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(double newBal, int pointsRem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(String itemCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		// TODO Auto-generated method stub
		
	}
	
}
