package eatSmartGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDNonMemberPage4_OK extends JPanel {
	private double TEN = 10;
	private double TWENTY = 20;
	private double THIRTY = 30;
	JLabel lblNewLabel = null;
	
	
	public UDNonMemberPage4_OK() {
		
		JLabel lblDispenseMessage = new JLabel("EatSmart Card Dispensing\n");
		lblDispenseMessage.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblDispenseMessage.setBackground(new Color(173, 216, 230));
		lblDispenseMessage.setOpaque(true);
		lblDispenseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel = new JLabel("<html>Please click on the 'New Card Dispense Slot' to collect your new EatSmart Card  <html/>");
		lblNewLabel.setIcon(new ImageIcon("/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/eatSmartGUI/collectCard.jpg"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(113)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(lblDispenseMessage, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
					.addContainerGap(96, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblDispenseMessage)
					.addGap(56)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(130))
		);
		setLayout(groupLayout);
	}
	public void setLabels(int cardId){
		lblNewLabel.setText("<html>Please click on the 'New Card Dispense Slot' to collect your new EatSmart Card with Card No: "+cardId+" <html/>");
	}
	
	/**
	 * At this page, the user will click on New Card Dispense Slot, and
	 * then the click on it should call page 1- i.e. member - nonmember page
	 * Have booleans for everypage in mainpage gui class. and check for every click handler before handling
	 */
}
