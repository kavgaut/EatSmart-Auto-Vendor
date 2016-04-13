package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
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

public class UDNonMemberPage2 extends JPanel implements PanelColleague {
	
	private MainPageGUI mainFrame;
	
	public UDNonMemberPage2(MainPageGUI mainFrame) {
		
		this.mainFrame = mainFrame;
		
		JLabel lblWelcome = new JLabel("Welcome to EatSmart Vending Machine\n");
		lblWelcome.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblWelcome.setBackground(new Color(173, 216, 230));
		lblWelcome.setOpaque(true);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		/* This label is set empty here, so that after the card number entry is authenticated, if entry is wrong, this will display error message, if success, nothing
		 * cos, next page will be the member main menu*/
		JLabel swipe_auth_message = new JLabel("");
		
		JPanel confirmationPanel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(113, Short.MAX_VALUE)
					.addComponent(lblWelcome, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
					.addGap(96))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(confirmationPanel, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
						.addComponent(swipe_auth_message, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblWelcome)
					.addGap(18)
					.addComponent(confirmationPanel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(swipe_auth_message)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		JLabel lblEatsmartMembership = new JLabel("EatSmart Membership");
		lblEatsmartMembership.setBackground(new Color(255, 245, 238));
		lblEatsmartMembership.setOpaque(true);
		
		JLabel lblMessage = new JLabel("<html> Our EatSmart Members enjoy a lot of previliges <br/>        Membership and Card cost is 0, Minimum Balance is $10 <br/>        Do you want to buy a card now?</html>");
		lblMessage.setOpaque(true);
		lblMessage.setBackground(new Color(255, 250, 250));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendPage(mainFrame.createNonMemberPage3_OK());
			}
		});
		btnOk.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JButton btnNotNow = new JButton("Not now");
		btnNotNow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getNMPage2());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		btnNotNow.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		GroupLayout gl_confirmationPanel = new GroupLayout(confirmationPanel);
		gl_confirmationPanel.setHorizontalGroup(
			gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_confirmationPanel.createSequentialGroup()
					.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_confirmationPanel.createSequentialGroup()
							.addGap(133)
							.addComponent(lblEatsmartMembership))
						.addGroup(gl_confirmationPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_confirmationPanel.createSequentialGroup()
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNotNow)))))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_confirmationPanel.setVerticalGroup(
			gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_confirmationPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblEatsmartMembership)
					.addGap(28)
					.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk)
						.addComponent(btnNotNow))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		confirmationPanel.setLayout(gl_confirmationPanel);
		setLayout(groupLayout);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
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
