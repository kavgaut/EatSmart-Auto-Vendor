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

public class UDMemberPage5_Redeem extends JPanel implements PanelColleague{
	private MainPageGUI mainFrame;
	private JPanel redeem_display_panel;
	private double newBalance;
	private double pointsRemaining;
	private JLabel show_remainingBonusPoints_label;
	private JLabel show_newBal_label;
	
	public UDMemberPage5_Redeem(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		//THE CONSTRUCTOR SHOULD CALL DAO AND FETCH DETAILS LIKE CURR BAL, POINTS SO FAR
		//CALCULATE THE EQUVALENT $ VALUE, AND ELIGIBILITY
		//SET ALL THESE TO THE LABELS IN THE PANEL
		this.setSize(526, 337);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(520, 330);
		JLabel redeem_here_label = new JLabel("Redeeming your points will add the $ equivalent to your card balance");
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
		
		JLabel item_code_validation_label = new JLabel("    ");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(redeem_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(redeem_display_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(redeem_here_label, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(item_code_validation_label, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(redeem_title_label)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(item_code_validation_label)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(redeem_here_label, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(redeem_display_panel, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		JLabel lblNewBal = new JLabel("New Balance: ");
		lblNewBal.setOpaque(true);
		
		show_newBal_label = new JLabel("  ");
		show_newBal_label.setBackground(Color.WHITE);
		show_newBal_label.setOpaque(true);
		
		JLabel lblBonusPoints = new JLabel("Bonus Points remaining");
		lblBonusPoints.setOpaque(true);
		
		show_remainingBonusPoints_label = new JLabel("  ");
		show_remainingBonusPoints_label.setOpaque(true);
		show_remainingBonusPoints_label.setBackground(Color.WHITE);
		
		JButton btnGoBackTo = new JButton("Go Back to Main Menu");
		btnGoBackTo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage5Redeem());
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
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addGap(113)
							.addComponent(btnGoBackTo))
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addGap(64)
							.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_redeem_display_panel.createSequentialGroup()
									.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewBal, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblBonusPoints))
									.addGap(55)
									.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(show_remainingBonusPoints_label, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
										.addComponent(show_newBal_label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
								.addComponent(redeem_validation_label, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_redeem_display_panel.setVerticalGroup(
			gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_redeem_display_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_redeem_display_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addComponent(show_newBal_label)
							.addGap(18)
							.addComponent(show_remainingBonusPoints_label))
						.addGroup(gl_redeem_display_panel.createSequentialGroup()
							.addComponent(lblNewBal)
							.addGap(18)
							.addComponent(lblBonusPoints)))
					.addGap(49)
					.addComponent(btnGoBackTo)
					.addGap(18)
					.addComponent(redeem_validation_label))
		);
		redeem_display_panel.setLayout(gl_redeem_display_panel);
		setLayout(groupLayout);
		
	}
	public void setLabels(double newBal, int pointsRem){
		show_newBal_label.setText(Double.toString(newBal));
		show_remainingBonusPoints_label.setText(Integer.toString(pointsRem));
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
	public void setLabels(String itemCode) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		// TODO Auto-generated method stub
		
	}
	
}
