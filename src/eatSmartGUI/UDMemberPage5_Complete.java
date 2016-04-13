package eatSmartGUI;

import java.awt.Color;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDMemberPage5_Complete extends JPanel{
	
	private boolean page5Set;
	JLabel show_card_bal_label = null;
	JLabel show_amt_debited_label = null;
	JLabel points_label =null;
	private MainPageGUI mainFrame;
	
	public UDMemberPage5_Complete(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		
		page5Set = true;
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel lblAmountDebited = new JLabel("Amount debited:  ");
		
		JLabel itrans_complete_label = new JLabel("Transaction Complete");
		itrans_complete_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewCardBalance = new JLabel("New Card Balance:  ");
		
		JLabel lblPoints = new JLabel("Points Collected:  ");
		
		show_card_bal_label = new JLabel("     ");
		show_card_bal_label.setBackground(Color.WHITE);
		show_card_bal_label.setOpaque(true);
		
		points_label = new JLabel("0");
		points_label.setBackground(Color.WHITE);
		points_label.setOpaque(true);
		
		JLabel lblDispenseMessage = new JLabel("<html>Click on the product dispense slot to collect your </br> purchased item(s)</html>");
		lblDispenseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		show_amt_debited_label = new JLabel("     ");
		show_amt_debited_label.setOpaque(true);
		show_amt_debited_label.setBackground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAmountDebited)
								.addComponent(lblNewCardBalance)
								.addComponent(lblPoints))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(show_amt_debited_label, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(show_card_bal_label, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
								.addComponent(points_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(itrans_complete_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(52, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addComponent(lblDispenseMessage, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(itrans_complete_label)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmountDebited)
						.addComponent(show_amt_debited_label))
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewCardBalance)
						.addComponent(show_card_bal_label))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPoints)
						.addComponent(points_label))
					.addGap(52)
					.addComponent(lblDispenseMessage, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(61, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	public boolean isPage5Set(){
		return page5Set;
	}
	
	public void setLabels(double amtToBeDebited, double cardBalance, int newPoints, List<Integer> itemCodeIntList){
		show_card_bal_label.setText(Double.toString(cardBalance));
		points_label.setText(Integer.toString(newPoints));
		show_amt_debited_label.setText(Double.toString(amtToBeDebited));
		mainFrame.product_dispense_label.setText(itemCodeIntList.toString());
	}
}
