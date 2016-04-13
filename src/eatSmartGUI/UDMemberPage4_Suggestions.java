package eatSmartGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class UDMemberPage4_Suggestions extends JPanel implements PanelColleague{

	private MainPageGUI mainFrame;
	private JLabel lblValidation;
	private JButton btnSearch;
	private JTextField calorie_low_txt;
	private JTextField calorie_high_txt;
	private boolean isItemSelected;

	private JPanel itemSuggestionPanel;
	private JList<Integer> itemSuggestionBox;
	//private String[] listData;
	private List<Integer> itemList;
	JCheckBox chckbxGlutenFree;
	JCheckBox chckbxLowFat;
	JCheckBox chckbxLowSugar;
	boolean isItemInVM = false;

	public UDMemberPage4_Suggestions(MainPageGUI mainFrame /*, some arraylist zoom to be passed here from main page */) {

		isItemSelected = false;
		this.mainFrame = mainFrame;

		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);

		// Create the Panel to hold the JList
		itemSuggestionPanel = new JPanel();
		itemSuggestionPanel.setBorder(new LineBorder(Color.black, 1));
		itemSuggestionPanel.setLayout(new BorderLayout(0, 0));

		// Now create the List and scrollpane and add them to the panel
		// Hardcoded values for now - BUT THIS zoom LIST MUST BE INITIALIZED IN
		// CONSTRUCTOR WITH ITEM CODES
		// FROM DB THAT MATCH ONLY LOW-SUGAR, LOW-FAT AND BETWEEN CALORIE RANGE
		// 100 AND 200
		itemList = new ArrayList<Integer>();
		
		JScrollPane scrollPane = new JScrollPane();
		itemSuggestionPanel.add(scrollPane, BorderLayout.CENTER);

		itemSuggestionBox = new JList<>((new AbstractListModel<Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6403997043580880045L;
			Integer[] values = itemList.toArray(new Integer[itemList.size()]);

			public int getSize() {
				return values.length;
			}

			public Integer getElementAt(int index) {
				return values[index];
			}
		}));
		itemSuggestionBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isItemSelected = true;
				//System.out.println(isItemSelected);
			}
		});
		itemSuggestionBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(itemSuggestionBox);
		// END OF JLIST INITIALIZATION

		JLabel suggestions_title_label = new JLabel(
				"Ask EatSmart for Healthy Suggestions");
		suggestions_title_label.setBorder(BorderFactory.createEtchedBorder(1));
		suggestions_title_label.setBackground(new Color(250, 250, 210));
		suggestions_title_label.setOpaque(true);
		suggestions_title_label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblItemList = new JLabel("Item List");
		lblItemList.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		lblItemList.setBackground(new Color(248, 248, 255));
		lblItemList.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemList.setOpaque(true);

		JLabel lblSelectKeywords = new JLabel("Select Keywords");
		lblSelectKeywords.setBorder(BorderFactory.createLineBorder(Color.black,
				1));
		lblSelectKeywords.setOpaque(true);
		lblSelectKeywords.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectKeywords.setBackground(new Color(248, 248, 255));

		JLabel lblSelectACalorie = new JLabel("Select a Calorie range");
		lblSelectACalorie.setBorder(BorderFactory.createLineBorder(Color.black,
				1));
		lblSelectACalorie.setOpaque(true);
		lblSelectACalorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectACalorie.setBackground(new Color(248, 248, 255));

		JPanel selectionPanel_Suggestions = new JPanel();
		selectionPanel_Suggestions.setBorder(new LineBorder(Color.black, 1));

		JButton btnBuyThisItem = new JButton("Buy This Item");
		btnBuyThisItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// If some item selected in JList is true then
				// pass the item code to method call that will set the
				// "Items selected"\
				// and "Total amount due" labels of the page4Buying page
				// for eg: methodInsideMainGUI(selected Item code){
				// one method call to retrive price of the item code
				// then call createMemberPage4Buying() method and then set the
				// labels there

				/*if (isItemSelected) {
				// call buyPage4 along with checking if the code is present
				// in the VM
				// and setting the item code and cost labels
			}*/
			System.out.println("item index in list-->"+itemSuggestionBox.getSelectedIndex());
			if(itemSuggestionBox.getSelectedIndex() != -1){
				//System.out.println(popular_item_list.getSelectedValue());
				for(int i=0; i<mainFrame.vMItemList.size();i++){
					int itemcode = mainFrame.vMItemList.get(i);
					if(itemcode==itemSuggestionBox.getSelectedValue()){
						isItemInVM = true;
					}
				}
				if(isItemInVM){
					if(mainFrame.userMemberClick){
						sendPage(mainFrame.createMemberPage4Buying(itemSuggestionBox.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0));
						//mainFrame.createMemberPage4Buying(mainFrame.getPage4Suggestions(),itemSuggestionBox.getSelectedValue().toString(),0.0,new ArrayList<Integer>(),0.0);
					}
					else{
						sendPage(mainFrame.createNonMemberPage4_Buying(itemSuggestionBox.getSelectedValue().toString()));
						//mainFrame.createNonMemberPage4_Buying(mainFrame.getPage4Suggestions(), itemSuggestionBox.getSelectedValue().toString());
					}
				}else{
					System.out.println("Item not a part of this VM.");
					lblValidation.setText("Selected item is not part of this VM");
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
		
		lblValidation = new JLabel("   ");
		lblValidation.setForeground(Color.RED);
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));

		calorie_low_txt = new JTextField("");
		calorie_low_txt.setColumns(10);

		calorie_high_txt = new JTextField("");
		calorie_high_txt.setColumns(10);

		JLabel lblLow = new JLabel("Low: ");

		JLabel lblHigh = new JLabel("High: ");

		btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setUpSuggestionList();
			}
		});
		btnSearch.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		btnSearch.setForeground(new Color(0, 0, 255));
		btnSearch.setOpaque(true);
		btnSearch.setBackground(new Color(100, 149, 237));

		JButton btnGoBackTo = new JButton("Go Back to Main Menu");
		btnGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4Suggestions());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		btnGoBackTo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		chckbxGlutenFree = new JCheckBox("Gluten Free");
		chckbxGlutenFree.setSelected(false);

		chckbxLowFat = new JCheckBox("Low Fat");
		chckbxLowFat.setSelected(false);

		chckbxLowSugar = new JCheckBox("Low Sugar");
		chckbxLowSugar.setSelected(false);

		GroupLayout gl_selectionPanel_Suggestions = new GroupLayout(
				selectionPanel_Suggestions);
		gl_selectionPanel_Suggestions
				.setHorizontalGroup(gl_selectionPanel_Suggestions
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_selectionPanel_Suggestions
										.createSequentialGroup()
										.addGroup(
												gl_selectionPanel_Suggestions
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_selectionPanel_Suggestions
																		.createSequentialGroup()
																		.addGap(18)
																		.addGroup(
																				gl_selectionPanel_Suggestions
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								Alignment.TRAILING,
																								gl_selectionPanel_Suggestions
																										.createSequentialGroup()
																										.addGroup(
																												gl_selectionPanel_Suggestions
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																chckbxGlutenFree)
																														.addComponent(
																																chckbxLowFat))
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												55,
																												Short.MAX_VALUE)
																										.addGroup(
																												gl_selectionPanel_Suggestions
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																lblLow)
																														.addComponent(
																																lblHigh,
																																GroupLayout.PREFERRED_SIZE,
																																43,
																																GroupLayout.PREFERRED_SIZE))
																										.addGap(18)
																										.addGroup(
																												gl_selectionPanel_Suggestions
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																calorie_high_txt,
																																GroupLayout.PREFERRED_SIZE,
																																56,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																calorie_low_txt,
																																GroupLayout.PREFERRED_SIZE,
																																56,
																																GroupLayout.PREFERRED_SIZE)))
																						.addGroup(
																								gl_selectionPanel_Suggestions
																										.createSequentialGroup()
																										.addComponent(
																												chckbxLowSugar)
																										.addPreferredGap(
																												ComponentPlacement.RELATED))))
														.addGroup(
																gl_selectionPanel_Suggestions
																		.createSequentialGroup()
																		.addContainerGap(
																				233,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnSearch)))
										.addGap(17))
						.addGroup(
								Alignment.LEADING,
								gl_selectionPanel_Suggestions
										.createSequentialGroup()
										.addContainerGap(152, Short.MAX_VALUE)
										.addComponent(btnGoBackTo)));
		gl_selectionPanel_Suggestions
				.setVerticalGroup(gl_selectionPanel_Suggestions
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_selectionPanel_Suggestions
										.createSequentialGroup()
										.addGap(30)
										.addGroup(
												gl_selectionPanel_Suggestions
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																calorie_low_txt,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblLow)
														.addComponent(
																chckbxGlutenFree))
										.addGroup(
												gl_selectionPanel_Suggestions
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_selectionPanel_Suggestions
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_selectionPanel_Suggestions
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								calorie_high_txt,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblHigh))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				62,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnSearch)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnGoBackTo))
														.addGroup(
																gl_selectionPanel_Suggestions
																		.createSequentialGroup()
																		.addGap(22)
																		.addComponent(
																				chckbxLowFat)
																		.addGap(27)
																		.addComponent(
																				chckbxLowSugar)))
										.addContainerGap()));
		selectionPanel_Suggestions.setLayout(gl_selectionPanel_Suggestions);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(88)
					.addComponent(suggestions_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(lblItemList, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(lblSelectKeywords, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblSelectACalorie, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(itemSuggestionPanel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuyThisItem))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblValidation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(selectionPanel_Suggestions, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(suggestions_title_label)
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblItemList)
						.addComponent(lblSelectKeywords)
						.addComponent(lblSelectACalorie))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(itemSuggestionPanel, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addComponent(btnBuyThisItem))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(selectionPanel_Suggestions, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblValidation)))
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
	
	public void setUpSuggestionList(){
		itemList.clear();
		String gluten = "N";
		String lowFat = "N";
		String lowSugar= "N";
		int low = 0;
		int high = 0;
		if(chckbxGlutenFree.isSelected()){
			gluten = "Y";
		}
		if(chckbxLowFat.isSelected()){
			lowFat = "Y";
		}
		if(chckbxLowSugar.isSelected()){
			lowSugar = "Y";
		}
		if (calorie_low_txt.getText().trim().length()>0)
			low = Integer.parseInt(calorie_low_txt.getText());
		if (calorie_high_txt.getText().trim().length()>0)
			high = Integer.parseInt(calorie_high_txt.getText());
		ItemDaoImpl dao = new ItemDaoImpl();
		List<Integer> newItemList = new ArrayList<Integer>();
		if(chckbxGlutenFree.isSelected() || chckbxLowFat.isSelected() || chckbxLowSugar.isSelected() || calorie_low_txt.getText().trim().length()>0 || calorie_high_txt.getText().trim().length()>0 ){
			newItemList = dao.findItemByParams(gluten, lowFat, lowSugar, low, high);
			itemList = newItemList;
		}
		System.out.println("sze-->"+itemList.size());
		itemSuggestionBox.setModel(new AbstractListModel<Integer>() {

			Integer[] values = itemList.toArray(new Integer[itemList.size()]);

			public int getSize() {
				return values.length;
			}

			public Integer getElementAt(int index) {
				return values[index];
			}
		});
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
