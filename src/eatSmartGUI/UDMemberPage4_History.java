package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class UDMemberPage4_History extends JPanel implements PanelColleague {
	
	private MainPageGUI mainFrame;
	private JTable historyTable;
	private JScrollPane scrollPane;
	private String[][] data;
	private String[] headers;
	private int rowSelected;
	private MyTableModel model;
	private JPanel historyPanel;
	boolean isItemInVM = false;
	private JLabel lblValidation;
	
	public UDMemberPage4_History(MainPageGUI mainFrame) {
		this.mainFrame = mainFrame;
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		//steps to load a the table on page load itself
		headers = new String[]{"Item Code", "Purchase Date"};
		
		historyPanel = new JPanel();
		
		loadTableData(((Member)mainFrame.memberType).getCard().getCardId());
		
		historyPanel.setLayout(new GridLayout(1, 1));
		historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.PAGE_AXIS));
		historyPanel.add(scrollPane);
		historyPanel.repaint();
		historyPanel.revalidate();
		
		//end of steps
		
		JLabel lblEatsmartMemberHistory = new JLabel("EatSmart Member History of purchase");
		lblEatsmartMemberHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatsmartMemberHistory.setBackground(Color.WHITE);
		lblEatsmartMemberHistory.setOpaque(true);
		
		JButton btnBuyItem = new JButton("Buy This Item");
		btnBuyItem.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnBuyItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object selectedObject = null;
				int selectedRowIndex = historyTable.getSelectedRow();
				int selectedColumnIndex = historyTable.getSelectedColumn();
				if(selectedRowIndex!=-1 && selectedColumnIndex!=-1){
					selectedObject = (Object) historyTable.getModel().getValueAt(selectedRowIndex, 0);
					if(selectedObject!=null){
						int selectedItemCode = Integer.parseInt(selectedObject.toString());
						for(int i=0; i<mainFrame.vMItemList.size();i++){
							int itemcode = mainFrame.vMItemList.get(i);
							if(itemcode==selectedItemCode){
								isItemInVM = true;
							}
						}
						if(isItemInVM){
							sendPage(mainFrame.createMemberPage4Buying(selectedObject.toString(),0.0,new ArrayList<Integer>(),0.0));
							//mainFrame.createMemberPage4Buying(mainFrame.getPage4History(),selectedObject.toString(),0.0,new ArrayList<Integer>(),0.0);
						}else{
							System.out.println("Item not a part of this VM.");
							lblValidation.setText("Selected item is not part of this VM");
						}
					}//add validation text in else
					else lblValidation.setText("Please select an item to buy");
				}
				else lblValidation.setText("Please select an item to buy");
				/*Object[] rowData = new Object[historyTable.getColumnCount()];
				for (int i = 0; i < historyTable.getColumnCount(); i++) {
				   rowData[i] = historyTable.getValueAt(0, i);
				}*/
				
			}
		});
		
		JButton btnGoBack = new JButton("Go Back to Main Menu");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4History());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		btnGoBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
	
		JPanel panel = new JPanel();
		add(panel);
		
		lblValidation = new JLabel("  ");
		lblValidation.setForeground(Color.RED);
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(122)
					.addComponent(lblEatsmartMemberHistory, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(historyPanel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBuyItem)
								.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
					.addGap(1))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(lblEatsmartMemberHistory, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(historyPanel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(btnBuyItem)
							.addGap(71)
							.addComponent(btnGoBack)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValidation)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	
	
	public void loadTableData(int newCardId){
		CardItemHistoryDaoImpl dao = new CardItemHistoryDaoImpl();
		CardItemHistory cardItemHistory = dao.getCardId(newCardId);
		HashMap<Integer,List<String>> itemDateStringHashMap = new HashMap<Integer,List<String>>();
		itemDateStringHashMap = cardItemHistory.getItemPurchaseListDate();
		 List<String> dates = new ArrayList<String>();
		int size = 0;
		Set entries = itemDateStringHashMap.entrySet();
		Iterator entriesIterator = entries.iterator();
		int i=0;
		while(entriesIterator.hasNext()){
			Map.Entry mapping = (Map.Entry) entriesIterator.next();
			  String itemCde = mapping.getKey().toString();
			  dates = (List<String>) mapping.getValue();
			  size += dates.size();
		}
		data = new String[size][2];
		Set dateEntries = itemDateStringHashMap.entrySet();
		Iterator dateEntriesIterator = dateEntries.iterator();
		while(dateEntriesIterator.hasNext()){
			Map.Entry mapping = (Map.Entry) dateEntriesIterator.next();
			  String itemCde = mapping.getKey().toString();
			  dates = (List<String>) mapping.getValue();
			  for(String date : dates){
				  data[i][0] = itemCde;
				  data[i][1] = date;
				  i++;
			  }
		}
		System.out.println(data);
		//Load table from here
		model = new MyTableModel(data, headers);
		historyTable = new JTable(model);
		historyTable.setFillsViewportHeight(true);
		historyTable.setRowSelectionAllowed(true);
		historyTable.setSelectionMode(0);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(getX(), getY(), 313, 180);
		scrollPane.setViewportView(historyTable);
		scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
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
