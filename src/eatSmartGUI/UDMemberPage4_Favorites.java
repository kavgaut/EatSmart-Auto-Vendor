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

public class UDMemberPage4_Favorites extends JPanel implements PanelColleague{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainPageGUI mainFrame;
	private JList<Integer> favorites_list;
	private boolean isItemSelected;
	boolean isItemInVM = false;
	private JLabel lblValidation;

	
	public UDMemberPage4_Favorites(MainPageGUI mainFrame){
		
		/////CODE TO GET THE MOST POPULAR ITEMS from the DAO SND LOAD JLIST AS SOON AS THIS PAGE IS LOADED - SHOULD BE HERE
		/*
		 * 
		 */
		CardItemFavoritesDaoImpl dao = new CardItemFavoritesDaoImpl();
		List<Integer> itemList = dao.findById(((Member)mainFrame.memberType).getCard().getCardId()).getItemCode();
		
		//Create the JLIST
		favorites_list = new JList<>();
		favorites_list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//I dont think this is enuf, just clicking is not enuf, some row must be selected- CHECK THIS
				isItemSelected = true;
				//System.out.println(isItemSelected);
			}
		});
		favorites_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Initialization
		isItemSelected = false;
		this.mainFrame = mainFrame;
		
		//Initialize the popular list from DB and the DefaultListModel
		
		DefaultListModel<Integer> dlm = new DefaultListModel<>();
		
		for (int i =0; i<itemList.size(); i++){
			dlm.addElement(itemList.get(i));
		}
		favorites_list.setModel(dlm);
		//End of populating JList
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		JLabel favorites_title_label = new JLabel("My Favorites\n");
		favorites_title_label.setBorder(BorderFactory.createEtchedBorder(1));
		favorites_title_label.setBackground(Color.WHITE);
		favorites_title_label.setOpaque(true);
		favorites_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnBuyThisItem = new JButton("Buy This Item");
		btnBuyThisItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				if (isItemSelected){
//					//code to check and redirect to buy page
//				}
				System.out.println("item index in list-->"+favorites_list.getSelectedIndex());
				if(favorites_list.getSelectedIndex() != -1){
					//System.out.println(popular_item_list.getSelectedValue());
					for(int i=0; i<mainFrame.vMItemList.size();i++){
						int itemcode = mainFrame.vMItemList.get(i);
						if(itemcode==favorites_list.getSelectedValue()){
							isItemInVM = true;
						}
					}
					if(isItemInVM){
						sendPage(mainFrame.createMemberPage4Buying(favorites_list.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0));
						//mainFrame.createMemberPage4Buying(mainFrame.getPage4Favorites(),favorites_list.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0);
					}else{
						System.out.println("Item not a part of this VM.");
						lblValidation.setText("Selected Item is not part of this VM");
					}
					
				}//add validation text in else
				else lblValidation.setText("Please select an item to buy");
			}
		});
		btnBuyThisItem.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JButton btnGoBackTo = new JButton("Go Back to Main Menu");
		btnGoBackTo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4Favorites());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		
		lblValidation = new JLabel(" ");
		lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblValidation.setForeground(Color.RED);

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(favorites_list, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(btnGoBackTo))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(37)
									.addComponent(btnBuyThisItem))))
						.addComponent(favorites_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 475, GroupLayout.PREFERRED_SIZE))
					.addGap(22))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(101)
							.addComponent(btnBuyThisItem)
							.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
							.addComponent(btnGoBackTo)
							.addGap(20))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(favorites_title_label)
							.addGap(29)
							.addComponent(favorites_list, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
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
