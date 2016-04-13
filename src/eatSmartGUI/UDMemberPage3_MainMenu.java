package eatSmartGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

public class UDMemberPage3_MainMenu extends JPanel implements PanelColleague{
	private MainPageGUI mainFrame;
	
	public UDMemberPage3_MainMenu(MainPageGUI mainFrame) {
		
		this.mainFrame = mainFrame;
		
		JLabel lblHelloMember = new JLabel("Hello EatSmart User");
		lblHelloMember.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblHelloMember.setBackground(Color.WHITE);
		lblHelloMember.setOpaque(true);
		lblHelloMember.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		lblHelloMember.setHorizontalAlignment(SwingConstants.CENTER);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		JLabel lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setForeground(Color.BLACK);
		lblMainMenu.setFont(new Font("Nanum Gothic", Font.BOLD | Font.ITALIC, 14));
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setBackground(Color.LIGHT_GRAY);
		lblMainMenu.setOpaque(true);
		lblMainMenu.setBorder(new LineBorder(Color.black,1));
		
		JPanel menuList_panel = new JPanel();
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(110)
							.addComponent(lblHelloMember, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(menuList_panel, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMainMenu, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(lblHelloMember)
					.addGap(18)
					.addComponent(lblMainMenu)
					.addGap(18)
					.addComponent(menuList_panel, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		
		JRadioButton rdbtnBuyItem = new JRadioButton("Buy Item");
		
		JRadioButton rdbtnFindInfo = new JRadioButton("Find Calorie and Ingredient information");
		rdbtnFindInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMemberPage4Info(mainFrame.getMemberMainMenu());
				sendPage(mainFrame.createMemberPage4Info());
				//sendPage(mainFrame.getPage4Info());
			}
		});
		
		JRadioButton rdbtnAskSuggestions = new JRadioButton("Ask EatSmart for suggestions");
		rdbtnAskSuggestions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMemberPage4_Suggestions(mainFrame.getMemberMainMenu());
				sendPage(mainFrame.createMemberPage4_Suggestions());
			}
		});
		
		JRadioButton rdbtnMostPopular = new JRadioButton("EatSmart's most popular items");
		rdbtnMostPopular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMemberPage4_Popular(mainFrame.getMemberMainMenu());
				sendPage(mainFrame.createMemberPage4_Popular());
			}
		});
		
		JRadioButton rdbtnMyHistory = new JRadioButton("My History of purchase");
		rdbtnMyHistory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMemberPage4_History();
				sendPage(mainFrame.createMemberPage4_History());
			}
		});
		
		JRadioButton rdbtnMyFavorites = new JRadioButton("My Favorites");
		rdbtnMyFavorites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendPage(mainFrame.createMemberPage4_Favorites());
			}
		});
		
		JRadioButton rdbtnRedeemPoints = new JRadioButton("Redeem EatSmart points");
		rdbtnRedeemPoints.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendPage(mainFrame.createMemberPage4_Redeem());
				//mainFrame.createMemberPage4_Redeem();
			}
		});
		
		rdbtnBuyItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//have a method in mainpage class that creates an instance of next page (page4) and replaces mainmenu with page 4
				//have an instance of main page here and call the above method here
 				//mainFrame.createMemberPage4Buying(mainFrame.getMemberMainMenu(), "", 0, new ArrayList<Integer>(),0);
				sendPage(mainFrame.createMemberPage4Buying("", 0, new ArrayList<Integer>(),0));
			}
		});
		
		GroupLayout gl_menuList_panel = new GroupLayout(menuList_panel);
		gl_menuList_panel.setHorizontalGroup(
			gl_menuList_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuList_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_menuList_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnRedeemPoints)
						.addComponent(rdbtnMyFavorites)
						.addComponent(rdbtnMyHistory)
						.addComponent(rdbtnMostPopular)
						.addComponent(rdbtnAskSuggestions)
						.addComponent(rdbtnFindInfo)
						.addComponent(rdbtnBuyItem))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		gl_menuList_panel.setVerticalGroup(
			gl_menuList_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuList_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnBuyItem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnFindInfo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnAskSuggestions)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnMostPopular)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnMyHistory)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnMyFavorites)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnRedeemPoints)
					.addContainerGap(11, Short.MAX_VALUE))
		);
		menuList_panel.setLayout(gl_menuList_panel);
		setLayout(groupLayout);
	}
	
	public void sendPage(JPanel newPage){
		mainFrame.replacePage(this,newPage);
	}

	@Override
	public void setLabels(double amoutDue, double balance, int points,
			List<Integer> itemCodeList) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
		//except calling the next colleague to replace it when necessary
		
	}

	@Override
	public void setLabel(double CurrentBalance) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
				//except calling the next colleague to replace it when necessary
				
	}

	@Override
	public void storeValues(double totalAmtDue, List<Integer> itemList) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
				//except calling the next colleague to replace it when necessary
				
		
	}

	@Override
	public void setLabels(double newBal, int pointsRem) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
				//except calling the next colleague to replace it when necessary
						
	}

	@Override
	public void setLabels(String itemCode) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
				//except calling the next colleague to replace it when necessary
						
	}

	@Override
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		// No OP method - not applicable for this class as the class is not passing any other information to its colleagues 
		//except calling the next colleague to replace it when necessary
						
	}

	
}
