package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
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

public class UDNonMemberPage4_Buying extends JPanel implements PanelColleague{
	private JTextField item_entry_txt;
	private MainPageGUI mainFrame;
	List<String> itemList = null; 
	List<Integer> itemCodeIntList  = null;
	List<Double> priceList  = null;
	HashMap<Integer,Double> priceMap  = null;
	double itemPrice = 0;
	private JLabel item_code_validation_label;
	StringBuffer sb = null;
	JLabel itemSelectedList_label;
	JLabel total_amount_due_label;
	TextFieldValidator textValidate = new TextFieldValidator();
	public VMItemDaoImpl vmdao;
	
	public UDNonMemberPage4_Buying(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		itemList = new ArrayList<String>();
		itemCodeIntList  = new ArrayList<Integer>();
		priceList  = new ArrayList<Double>();
		priceMap  = new HashMap<Integer,Double>();
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel lblEnterItemCode = new JLabel("Enter Item Code");
		
		item_entry_txt = new JTextField();
		item_entry_txt.setColumns(10);
		
		item_code_validation_label = new JLabel("");
		item_code_validation_label.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		item_code_validation_label.setForeground(Color.RED);
		
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
				mainFrame.goBackToMainPage(mainFrame.getNMPage4Buying());
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
					updateVMItemInfo(mainFrame.getCUrrentVM().getVMId());
					//mainFrame.createNonMemberPage5_checkOut(itemPrice, itemCodeIntList);
					sendPage(mainFrame.createNonMemberPage5_checkOut(itemPrice, itemCodeIntList));
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
				itemPrice = 0;
			}
		});
		lblRefreshSelection.setFont(new Font("Lucida Fax", Font.ITALIC, 13));
		lblRefreshSelection.setForeground(new Color(0, 0, 255));
		lblRefreshSelection.setBackground(new Color(253, 245, 230));
		lblRefreshSelection.setOpaque(true);
		
		JLabel title_label = new JLabel("Buying an Item");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
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
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(total_amount_due_label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(item_entry_txt, 0, 0, Short.MAX_VALUE)
										.addComponent(itemSelectedList_label, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRefreshSelection)
										.addComponent(item_entry_next_button)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lbltoBuyMore, GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addComponent(title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(item_code_validation_label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCancel)
							.addPreferredGap(ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
							.addComponent(btnCheckOut)))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(title_label)
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
						.addComponent(itemSelectedList_label)
						.addComponent(lblRefreshSelection))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalAmountDue)
						.addComponent(total_amount_due_label))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnCheckOut))
					.addGap(18)
					.addComponent(item_code_validation_label, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	public void updateVMItemInfo(String newVmId){
		vmdao = new VMItemDaoImpl();
		//VMItem vItem = vmdao.getVMItem(newVmId);
		int rackQtny = 0;
		int saleQtny = 0;
		double revenue = 0;
		double price =0;
		//HashMap<Integer,Integer> rackQtyMap =  vItem.getRackQty();
		for(int i=0;i<itemCodeIntList.size();i++){
			VMItem vItem = vmdao.getVMItem(newVmId);
			HashMap<Integer,Integer> rackQtyMap =  vItem.getRackQty();
			rackQtny = rackQtyMap.get(itemCodeIntList.get(i));
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
	
	public void processing(){
		ItemDaoImpl d = new ItemDaoImpl();
		int itemId = 0;
		sb = new StringBuffer();
		String itemCode = null;
		boolean isVmList = false;
		vmdao = new VMItemDaoImpl();
		int rackQtyHere = 0;
		
		if (item_entry_txt.getText().trim().length()>0)
			itemCode = item_entry_txt.getText();
		else item_code_validation_label.setText("Please enter Item code to buy");
		
		if(textValidate.validateNumberField(itemCode)){
			itemId = Integer.parseInt(itemCode);
			Item i = d.findById(itemId);
			
			item_code_validation_label.setText("");
			
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
					item_code_validation_label.setText("please senter a valid item code. Item " + itemId + " is not present in this vm");
					item_entry_txt.setText("");
				}
			}else {
				System.out.println("Please enter the correct Item code");
				item_code_validation_label.setText("Please enter the correct Item code");
				item_entry_txt.setText("");
			}
			for(int j=0;j<itemList.size();j++){
				sb.append(itemList.get(j)+",");
			}
			for(int j=0;j<priceList.size();j++){
				itemPrice += priceList.get(j);
			}
			System.out.println("price-->"+itemPrice);
			itemSelectedList_label.setText(sb.toString());
			total_amount_due_label.setText(Double.toString(itemPrice));
			System.out.println("Item List Size-->"+itemList.size());
			sb.delete(0, sb.length());
		}
		else{
			item_code_validation_label.setText("Please enter the correct Item code");
		}
		
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
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		// TODO Auto-generated method stub
		
	}
}
