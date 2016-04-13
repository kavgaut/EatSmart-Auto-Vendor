package eatSmartGUI;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDNonMemberPage6_Complete extends JPanel{
	
	private boolean page5Set;
	JLabel show_change_due_label =null;
	private MainPageGUI mainFrame;
	
	public UDNonMemberPage6_Complete(MainPageGUI mainFrame){
		this.mainFrame = mainFrame;
		page5Set = true;
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel lblChangeDue = new JLabel("Change due");
		
		JLabel itrans_complete_label = new JLabel("Transaction Complete");
		itrans_complete_label.setBackground(new Color(255, 255, 255));
		itrans_complete_label.setOpaque(true);
		itrans_complete_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblDispenseMessage = new JLabel("<html>Click on the product dispense slot to collect your </br><br> <br> (1) purchased item(s) <br> (2) Change amount</html>");
		lblDispenseMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		show_change_due_label = new JLabel("     ");
		show_change_due_label.setOpaque(true);
		show_change_due_label.setBackground(Color.WHITE);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(86, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblChangeDue)
							.addGap(24)
							.addComponent(show_change_due_label, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(163))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblDispenseMessage)
							.addGap(68))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addComponent(itrans_complete_label, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
					.addGap(80))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(itrans_complete_label)
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChangeDue)
						.addComponent(show_change_due_label))
					.addGap(38)
					.addComponent(lblDispenseMessage, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(83, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	public boolean isPage5Set(){
		return page5Set;
	}
	
	public void setLabels(double amtDue, String message){
		show_change_due_label.setText(Double.toString(amtDue));
		mainFrame.product_dispense_label.setText(message);
	}
	
}
