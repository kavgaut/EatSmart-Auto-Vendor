package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDNonMemberPage3_OK extends JPanel {
	
	public JLabel amount_due;
	private JLabel lblDen10;
	private JLabel lblDen20;
	private JLabel lblDen30;
	private double TEN = 10;
	private double TWENTY = 20;
	private double THIRTY = 30;
	public boolean isDen10;
	public boolean isDen20;
	public boolean isDen30;
	public JLabel cash_auth_message;
	
	public UDNonMemberPage3_OK() {
		
		JLabel lblWelcome = new JLabel("Welcome to EatSmart Vending Machine\n");
		lblWelcome.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblWelcome.setBackground(new Color(173, 216, 230));
		lblWelcome.setOpaque(true);
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		/* This label is set empty here, so that after the card number entry is authenticated, if entry is wrong, this will display error message, if success, nothing
		 * cos, next page will be the member main menu*/
		cash_auth_message = new JLabel("      ");
		cash_auth_message.setHorizontalAlignment(SwingConstants.CENTER);
		cash_auth_message.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		cash_auth_message.setForeground(Color.RED);
		
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
					.addComponent(confirmationPanel, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(50, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addComponent(cash_auth_message, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblWelcome)
					.addGap(18)
					.addComponent(confirmationPanel, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cash_auth_message, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblEatsmartMembership = new JLabel("EatSmart Membership");
		lblEatsmartMembership.setBackground(new Color(255, 245, 238));
		lblEatsmartMembership.setOpaque(true);
		
		JLabel lblDenominationMesage = new JLabel("<html> To buy an EatSmart card, choose one of the following as <br/> your initial balance  <br/></html>");
		lblDenominationMesage.setOpaque(true);
		lblDenominationMesage.setBackground(new Color(255, 250, 250));
		lblDenominationMesage.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDen10 = new JLabel(Double.toString(TEN));
		lblDen10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				amount_due.setText(lblDen10.getText());
				isDen10 = true;
			}
		});
		lblDen10.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblDen10.setBackground(new Color(169, 169, 169));
		lblDen10.setOpaque(true);
		lblDen10.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblEntry = new JLabel("<html>Enter the chosen amount in the Cash/Coin entry slot<html/>");
		lblEntry.setOpaque(true);
		lblEntry.setBackground(new Color(255, 250, 250));
		lblEntry.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDen20 = new JLabel(Double.toString(TWENTY));
		lblDen20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				amount_due.setText(lblDen20.getText());
				isDen20 = true;
			}
		});
		lblDen20.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblDen20.setBackground(new Color(169, 169, 169));
		lblDen20.setOpaque(true);
		lblDen20.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDen30 = new JLabel(Double.toString(THIRTY));
		lblDen30.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				amount_due.setText(lblDen30.getText());
				isDen30 = true;
			}
		});
		lblDen30.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblDen30.setBackground(new Color(169, 169, 169));
		lblDen30.setOpaque(true);
		lblDen30.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTotalAmountDue = new JLabel("Total Amount Due: ");
		lblTotalAmountDue.setBackground(new Color(255, 255, 255));
		lblTotalAmountDue.setOpaque(true);
		
		amount_due = new JLabel("  ");
		amount_due.setForeground(new Color(0, 0, 0));
		amount_due.setBackground(new Color(255, 255, 255));
		amount_due.setOpaque(true);
		
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
							.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_confirmationPanel.createSequentialGroup()
									.addComponent(lblDen10, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDen20, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblDen30, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDenominationMesage, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
								.addComponent(lblEntry, 0, 0, Short.MAX_VALUE)
								.addGroup(gl_confirmationPanel.createSequentialGroup()
									.addComponent(lblTotalAmountDue)
									.addGap(18)
									.addComponent(amount_due, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_confirmationPanel.setVerticalGroup(
			gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_confirmationPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblEatsmartMembership)
					.addGap(18)
					.addComponent(lblDenominationMesage, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDen10, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDen20, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDen30, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEntry)
					.addGap(18)
					.addGroup(gl_confirmationPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalAmountDue)
						.addComponent(amount_due))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		confirmationPanel.setLayout(gl_confirmationPanel);
		setLayout(groupLayout);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
	}
	/**
	 * At this page, the user will enter amount in the coin/cash text field, after validation, 
	 * the next button beside it should lead to nmOKPage4 - i.e. NonMemberPage4_OK
	 */
	
}
