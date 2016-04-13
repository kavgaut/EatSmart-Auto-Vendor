package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDMemberPage4_Redeem extends JPanel implements PanelColleague{
	private MainPageGUI mainFrame;
	private JPanel redeem_display_panel;
	private JLabel show_equivalent_label;
	private JLabel show_currentBal_label;
	private JLabel show_bonusPoints_label;
	private JLabel show_eligibility_label;
	
	private int cardId;
	private Double currBal;
	private String eligibility;
	private int points;
	private int equivalent;
	private double newBalance;
	private int pointsRemaining;
	private boolean isEligible;
	private JLabel item_code_validation_label;
	
	private CardDaoImpl dao = new CardDaoImpl();
	private Card card =null;
	
	public UDMemberPage4_Redeem(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		this.setSize(526, 337);
		//THE CONSTRUCTOR SHOULD CALL DAO AND FETCH DETAILS LIKE CURR BAL, POINTS SO FAR
		//CALCULATE THE EQUVALENT $ VALUE, AND ELIGIBILITY
		//SET ALL THESE TO THE LABELS IN THE PANEL in the constructor - HERE
		
		//first initialize from dao
		card = dao.findById(((Member)mainFrame.memberType).getCard().getCardId());
		cardId = card.getCardId();
		currBal = card.getCardBalance();
		points = card.getCardPoints();
		
		if (points/100 >= 1)
		{
			eligibility = "Eligible";
			isEligible = true;
			equivalent = (int)(points/100) * 5;
		}
		else 
			{
			eligibility = (100 - points) + " more points needed";
			equivalent = 0;
			isEligible = false;
			}
		
		//next set the labels after initializing them
		show_currentBal_label = new JLabel("  ");
		show_bonusPoints_label = new JLabel("  ");
		show_eligibility_label = new JLabel("  ");
		show_equivalent_label = new JLabel("  ");
		
		show_currentBal_label.setText(Double.toString(currBal));
		show_bonusPoints_label.setText(Integer.toString(points));
		show_eligibility_label.setText(eligibility);
		show_equivalent_label.setText(Double.toString(equivalent));
		
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel redeem_here_label = new JLabel("Redeem your points here");
		redeem_here_label.setOpaque(true);
		redeem_here_label.setBackground(SystemColor.scrollbar);
		redeem_here_label.setHorizontalAlignment(SwingConstants.CENTER);
		redeem_here_label.setBorder(new LineBorder(Color.black, 1));
		
		redeem_display_panel = new JPanel();
		redeem_display_panel.setBackground(SystemColor.windowBorder);
		redeem_display_panel.setVisible(true);
		redeem_display_panel.setOpaque(true);
		
		JLabel redeem_title_label = new JLabel("Hello EatSmart Previliged User");
		redeem_title_label.setBorder(new LineBorder(Color.black, 1));
		redeem_title_label.setBackground(Color.WHITE);
		redeem_title_label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		redeem_title_label.setOpaque(true);
		redeem_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		item_code_validation_label = new JLabel("    ");
		item_code_validation_label.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		item_code_validation_label.setForeground(Color.RED);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(redeem_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(redeem_here_label, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(item_code_validation_label, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(redeem_display_panel, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(redeem_title_label)
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(redeem_here_label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(item_code_validation_label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(redeem_display_panel, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblCurrentBal = new JLabel("Current Balance: ");
		lblCurrentBal.setOpaque(true);
		
		show_currentBal_label.setBackground(Color.WHITE);
		show_currentBal_label.setOpaque(true);
		
		JLabel lblBonusPoints = new JLabel("Total Bonus Points collected");
		lblBonusPoints.setOpaque(true);
		
		
		show_bonusPoints_label.setOpaque(true);
		show_bonusPoints_label.setBackground(Color.WHITE);
		
		JLabel lblEligibility = new JLabel("Eligibility:");
		lblEligibility.setOpaque(true);
		
		
		show_eligibility_label.setOpaque(true);
		show_eligibility_label.setBackground(Color.WHITE);
		
		JLabel lblEquivalent = new JLabel(" $ Equivalent");
		lblEquivalent.setOpaque(true);
		
		
		show_equivalent_label.setOpaque(true);
		show_equivalent_label.setBackground(Color.WHITE);
		
		JButton btnRedeemNow = new JButton("Redeem Now");
		btnRedeemNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//call to DAO and update the new balance, new points remaining in the database from parameters: 
				//AND WILL LEAD TO NEXT PAGE IF ELIGIBLE, IF INELIGIBLE IT WILL DISPLAY ERROR MESSAGE (set text to redeem_validation_label
				if(isEligible){
					newBalance = currBal + equivalent;
					pointsRemaining = points%100;
					//mainFrame.createMemberPage5_Redeem(newBalance, pointsRemaining);
					sendPage(mainFrame.createMemberPage5_Redeem(newBalance, pointsRemaining));

					dao.updateCardBalance(cardId, newBalance);
					dao.updateCardPoints(cardId, pointsRemaining);
				}
				else item_code_validation_label.setText("You can redeem when you reach 100 points");
				
			}
		});
		btnRedeemNow.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JButton btnGoBackTo = new JButton("Go Back to Main Menu");
		btnGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4Redeem());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		btnGoBackTo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel redeem_validation_label = new JLabel("   ");
		GroupLayout gl_redeem_display_panel = new GroupLayout(redeem_display_panel);
		gl_redeem_display_panel.setHorizontalGroup(
			gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_redeem_display_panel.createSequentialGroup()
					.addGap(58)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(redeem_validation_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCurrentBal, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBonusPoints)
								.addComponent(lblEligibility)
								.addComponent(lblEquivalent)
								.addComponent(btnRedeemNow))
							.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_redeem_display_panel.createSequentialGroup()
									.addGap(52)
									.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(show_bonusPoints_label, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(show_currentBal_label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
											.addComponent(show_equivalent_label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
										.addComponent(show_eligibility_label, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_redeem_display_panel.createSequentialGroup()
									.addGap(12)
									.addComponent(btnGoBackTo)))
							.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_redeem_display_panel.setVerticalGroup(
			gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_redeem_display_panel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentBal)
						.addComponent(show_currentBal_label))
					.addGap(18)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBonusPoints)
						.addComponent(show_bonusPoints_label))
					.addGap(18)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEligibility)
						.addComponent(show_eligibility_label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEquivalent)
						.addComponent(show_equivalent_label))
					.addGap(26)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRedeemNow)
						.addComponent(btnGoBackTo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(redeem_validation_label)
					.addContainerGap(9, Short.MAX_VALUE))
		);
		redeem_display_panel.setLayout(gl_redeem_display_panel);
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
