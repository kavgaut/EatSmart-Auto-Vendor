package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDMemberPage4_Buying extends JPanel implements PanelColleague{
	private JTextField item_entry_txt;
	private MainPageGUI mainFrame;
	List<String> itemList = null; 
	List<Integer> itemCodeIntList  = null;
	List<Double> priceList  = null;
	HashMap<Integer,Double> priceMap  = null;
	double cardBalance = 0;
	double cardBal;
	int newPoints =0;
	double amtToBeDebited = 0;
	private JLabel item_code_validation_label;
	private JButton btnReloadCard;
	private boolean reloadFlag;
	private double itemPrice;
	private JLabel itemSelectedList_label;
	private JLabel total_amount_due_label;
	private JLabel card_balance_label;
	private JLabel lblCardBal;
	private double existingCardBal;
	public VMItemDaoImpl vmdao;
	public int rackQtny;
	
	ItemDaoImpl d = new ItemDaoImpl();
	CardDaoImpl dao = new CardDaoImpl();
	StringBuffer sb;
	TextFieldValidator textValidate = new TextFieldValidator();
	
	public UDMemberPage4_Buying(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		itemList = new ArrayList<String>();
		itemCodeIntList  = new ArrayList<Integer>();
		priceList  = new ArrayList<Double>();
		priceMap  = new HashMap<Integer,Double>();
		reloadFlag = false;
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel lblEnterItemCode = new JLabel("Enter Item Code");
		
		item_entry_txt = new JTextField();
		item_entry_txt.setColumns(10);
		
		item_code_validation_label = new JLabel("");
		item_code_validation_label.setForeground(Color.RED);
		item_code_validation_label.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		JLabel lblItemsSelected = new JLabel("Item(s) selected:  ");
		
		JLabel lblTotalAmountDue = new JLabel("Total Amount due: ");
		
		itemSelectedList_label = new JLabel("None");
		itemSelectedList_label.setBackground(Color.WHITE);
		itemSelectedList_label.setOpaque(true);
		
		total_amount_due_label = new JLabel("$ 0");
		total_amount_due_label.setBackground(Color.WHITE);
		total_amount_due_label.setOpaque(true);
		
		JButton btnCancel = new JButton("Cancel Transaction");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.goBackToMainPage(mainFrame.getPage4Buying());
			}
		});
		btnCancel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//in addition to going to page 5 - this should also do other operations 
				//from DAO to print balance and the item list on dispense slot
				
				if (!itemCodeIntList.isEmpty()){
					if(cardBal-itemPrice >= 0){
						
						updateVMItemInfo(mainFrame.getCUrrentVM().getVMId());
						updateCardInfo(((Member)mainFrame.memberType).getCard().getCardId(),priceList);
						insertCardHistory(((Member)mainFrame.memberType).getCard().getCardId(),itemCodeIntList);
						//mainFrame.createMemberPage5Complete(amtToBeDebited, cardBalance, newPoints, itemCodeIntList);
						sendPage(mainFrame.createMemberPage5Complete(amtToBeDebited, cardBalance, newPoints, itemCodeIntList));
					}
					else{
						item_code_validation_label.setText("Insufficient Balance. Please reload your card.");
						reloadFlag = true;
						btnReloadCard.setVisible(true);
					}	
				}
				else{
					item_code_validation_label.setText("Please enter an item to checkout");
				}
			}
		});
		btnCheckOut.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel item_entry_next_button = new JLabel("");
		item_entry_next_button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		item_entry_next_button.setIcon(new ImageIcon("/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/nextButton.jpg"));
		
		//Rearrange this code in another class or a method
		System.out.println("Item List Size-->"+itemList.size());
		
		
		item_entry_next_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				itemPrice = 0;
				processing();
			}
			
		});
		
		
		JLabel lbltoBuyMore = new JLabel("<html>To buy more items, keep entering item below</html>");
		lbltoBuyMore.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblRefreshSelection = new JLabel("Clear selection");
		lblRefreshSelection.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblRefreshSelection.addMouseListener(new MouseAdapter() {                                                                   
			@Override
			public void mouseClicked(MouseEvent e) {
				itemSelectedList_label.setText("");
				total_amount_due_label.setText("");
				itemList.clear();
				itemCodeIntList.clear();
				priceList.clear();
			}
		});
		lblRefreshSelection.setFont(new Font("Lucida Fax", Font.ITALIC, 13));
		lblRefreshSelection.setForeground(new Color(0, 0, 255));
		lblRefreshSelection.setBackground(new Color(253, 245, 230));
		lblRefreshSelection.setOpaque(true);
		
		JButton btnAddItemsTo = new JButton("Add Items to My Favorites");
		btnAddItemsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddItemsTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//This should add the displayed/selected items to the CardFavorites table against the card number 
				//if the item is not already there
				Collection<Integer> newItemCodeList = new ArrayList<Integer>();
				Collection<Integer> currentItemCodeListInDb = new ArrayList<Integer>();
				CardItemFavorites cardItemFav = new CardItemFavorites();
				int cardId = ((Member)mainFrame.memberType).getCard().getCardId();
				cardItemFav.setcardId(cardId);
				CardItemFavoritesDaoImpl dao = new CardItemFavoritesDaoImpl();
				currentItemCodeListInDb = dao.findById(cardId).getItemCode();
				newItemCodeList.addAll(itemCodeIntList);
				newItemCodeList.removeAll(currentItemCodeListInDb);
				if(newItemCodeList.size()>0){
					for(int itemId :newItemCodeList){
						dao.insertItemFavorites(cardId, itemId);
					}
				}
			}
		});
		btnAddItemsTo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel label = new JLabel("Buying an Item");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnReloadCard = new JButton("Reload Card");
		btnReloadCard.setVisible(false);
		
		btnReloadCard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (reloadFlag) {
					//mainFrame.createMemberCardReload(cardBal, itemPrice, itemCodeIntList);
					sendPage(mainFrame.createMemberCardReload(cardBal, itemPrice, itemCodeIntList));
				}
			}
		});
		btnReloadCard.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		lblCardBal = new JLabel("CardBalance:");
		
		card_balance_label = new JLabel((String) null);
		card_balance_label.setOpaque(true);
		card_balance_label.setBackground(Color.WHITE);
		
		Card card = dao.findById(((Member)mainFrame.memberType).getCard().getCardId());
		existingCardBal = card.getCardBalance();
		card_balance_label.setText(Double.toString(existingCardBal));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalAmountDue)
								.addComponent(lblItemsSelected)
								.addComponent(lblEnterItemCode))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(itemSelectedList_label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(item_entry_txt, Alignment.TRAILING))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(item_entry_next_button))
											.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addGap(9)
														.addComponent(btnCheckOut))
													.addComponent(btnReloadCard))))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblRefreshSelection))))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(total_amount_due_label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
							.addGap(62))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbltoBuyMore, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(79, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(item_code_validation_label, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
								.addComponent(btnAddItemsTo))))
					.addGap(36))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(348, Short.MAX_VALUE)
					.addComponent(lblCardBal, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(card_balance_label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(74))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCardBal)
						.addComponent(card_balance_label, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(label)
					.addGap(18)
					.addComponent(lbltoBuyMore)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEnterItemCode)
							.addComponent(item_entry_txt, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(item_entry_next_button))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemsSelected)
						.addComponent(itemSelectedList_label, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRefreshSelection))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTotalAmountDue)
								.addComponent(total_amount_due_label))
							.addGap(34)
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAddItemsTo))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCheckOut)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnReloadCard)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(item_code_validation_label, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
	}
	
	public void updateVMItemInfo(String newVmId){
		vmdao = new VMItemDaoImpl();
		//VMItem vItem = vmdao.getVMItem(newVmId);
		rackQtny = 0;
		int saleQtny = 0;
		double revenue = 0;
		double price =0;
		//HashMap<Integer,Integer> rackQtyMap =  vItem.getRackQty();
		for(int i=0;i<itemCodeIntList.size();i++){
			VMItem vItem = vmdao.getVMItem(newVmId);
			HashMap<Integer,Integer> rackQtyMap =  vItem.getRackQty();
			rackQtny = rackQtyMap.get(itemCodeIntList.get(i));
			if(rackQtny>0)
				rackQtny--;
			vmdao.updateRackQty(itemCodeIntList.get(i), newVmId, rackQtny);
		}
		//HashMap<Integer,Integer> saleQtyMap =  vItem.getSaleQty();
		for(int i=0;i<itemCodeIntList.size();i++){
			VMItem vItem = vmdao.getVMItem(newVmId);
			HashMap<Integer,Integer> saleQtyMap =  vItem.getSaleQty();
			saleQtny = saleQtyMap.get(itemCodeIntList.get(i));
			saleQtny++;
			vmdao.updateSaleQty(itemCodeIntList.get(i), newVmId, saleQtny);
		}
		//HashMap<Integer,Double> revenueMap =  vItem.getRevenue();
		for(int i=0;i<itemCodeIntList.size();i++){
			VMItem vItem = vmdao.getVMItem(newVmId);
			HashMap<Integer,Double> revenueMap =  vItem.getRevenue();
			revenue = revenueMap.get(itemCodeIntList.get(i));
			price = priceMap.get(itemCodeIntList.get(i));
			revenue += price;
			vmdao.updateRevenue(itemCodeIntList.get(i), newVmId, revenue);
		}
	}
	
	public void updateCardInfo(int newCardId, List<Double> newPriceList){
		CardDaoImpl dao = new CardDaoImpl();
		int prevPoints = 0;
		
		for(int i=0; i<newPriceList.size(); i++){
			amtToBeDebited = amtToBeDebited + newPriceList.get(i);
		}
		Card card = dao.findById(newCardId); 
		cardBalance = card.getCardBalance();
		prevPoints = card.getCardPoints();
		newPoints = (int) Math.floor(amtToBeDebited);
		newPoints += prevPoints;
		cardBalance = cardBalance - amtToBeDebited;
		dao.updateCardBalance(newCardId, cardBalance);
		dao.updateCardPoints(newCardId, newPoints);
	}
	
	public void insertCardHistory(int newCardId, List<Integer> newItemList ){
		CardItemHistory crdHistory = new CardItemHistory();
		crdHistory.setCardId(newCardId);
		crdHistory.setItemCode(newItemList);
		CardItemHistoryDaoImpl dao = new CardItemHistoryDaoImpl();
		dao.insertCard(crdHistory);
	}
	
	public void processing(){
		int itemId = 0;
		sb = new StringBuffer();
		Card card = dao.findById(((Member)mainFrame.memberType).getCard().getCardId());
		cardBal = card.getCardBalance();
		boolean isVmList = false;
		String itemCode = null;
		vmdao = new VMItemDaoImpl();
		int rackQtyHere = 0;
		
	if (item_entry_txt.getText().trim().length()>0)
		itemCode = item_entry_txt.getText();
	
	else item_code_validation_label.setText("Please enter Item code to buy");
		
	if(textValidate.validateNumberField(itemCode)){
		itemId = Integer.parseInt(itemCode);
		item_code_validation_label.setText("");
		Item i = d.findById(itemId);
		
		if(i!=null && (i.getItemCode()==itemId) && (item_entry_txt.getText().trim().length()>0)){
			for(int j=0; j<mainFrame.vMItemList.size();j++){
				if(itemId==mainFrame.vMItemList.get(j)){
					isVmList =  true;
				}
			}
			if(isVmList){
				rackQtyHere = vmdao.getItemRackQtyById(mainFrame.VMId, itemId);
				if(rackQtyHere>0){
					itemList.add(item_entry_txt.getText());
					itemCodeIntList.add(itemId);
					System.out.println("Item code correct.");
					item_entry_txt.setText("");
					priceList.add(i.getItemPrice());
					priceMap.put(itemId, i.getItemPrice());
				}
				else item_code_validation_label.setText("This item is currently out of stock in this VM, please select another item");
			}
			else{
				System.out.println("Item code incorrect.");
				item_code_validation_label.setText("Please enter the correct Item code. Item " + itemId + " is not present in this vm");
				item_entry_txt.setText("");
			}
			
		}else {
			System.out.println("Please enter the correct Item code");
			item_code_validation_label.setText("Please enter the correct Item code");
			item_entry_txt.setText("");
		}
		for(int j=0;j<itemCodeIntList.size();j++){
			sb.append(itemCodeIntList.get(j)+",");
		}
		for(int j=0;j<priceList.size();j++){
			itemPrice += priceList.get(j);
		}
		System.out.println("price-->"+itemPrice);
		//cardBal -= itemPrice;
		itemSelectedList_label.setText(sb.toString());
		total_amount_due_label.setText(Double.toString(itemPrice));
		System.out.println("Item List Size-->"+itemList.size());
		
		sb.delete(0, sb.length());
	}
	else{
		System.out.println("cannot process request");
		item_code_validation_label.setText("Please enter the correct Item code");
	}
		
	}
	
	public void setLabels(double totalAmtDue,
			List<Integer> itemCodeList, double newBal) {
		//total_amount_due_label.setText(Double.toString(totalAmtDue));
		sb = new StringBuffer();
		itemPrice = totalAmtDue;
		itemCodeIntList = itemCodeList;
		int itemId = 0;
		for(int j=0;j<itemCodeIntList.size();j++){
			itemId = itemCodeIntList.get(j);
			Item i = d.findById(itemId);
			if(i!=null){
				priceList.add(i.getItemPrice());
				priceMap.put(itemId, i.getItemPrice());
			}
		}
		for(int j=0;j<itemCodeIntList.size();j++){
			sb.append(itemCodeIntList.get(j)+",");
		}
//		for(int j=0;j<priceList.size();j++){
//			itemPrice += priceList.get(j);
//		}
		itemSelectedList_label.setText(sb.toString());
		total_amount_due_label.setText(Double.toString(itemPrice));
		cardBal = newBal;
		System.out.println("Item List Size-->"+itemList.size());
		//processing();
		
	}
	
	public void setLabels(String itemCode){
		item_entry_txt.setText(itemCode);
	}
	
	public void sendPage(JPanel newPage){
		mainFrame.replacePage(this,newPage);
	}

	@Override
	public void setLabels(double amoutDue, double balance, int points,
			List<Integer> itemCodeList) {
		// No Op method
		
	}

	@Override
	public void setLabel(double CurrentBalance) {
		// No Op method
		
	}

	@Override
	public void storeValues(double totalAmtDue, List<Integer> itemList) {
		// No Op method
		
	}

	@Override
	public void setLabels(double newBal, int pointsRem) {
		// No Op method
		
	}
	
}
