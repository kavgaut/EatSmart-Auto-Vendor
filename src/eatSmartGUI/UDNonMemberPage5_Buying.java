package eatSmartGUI;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDNonMemberPage5_Buying extends JPanel{
	
	JLabel total_amount_due_label =null;
	JLabel coin_entryValidation_label = null;
	private MainPageGUI mainFrame;
	private String passOnProdDispenseMessage;
	
	public UDNonMemberPage5_Buying(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		coin_entryValidation_label = new JLabel("     ");
		coin_entryValidation_label.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		coin_entryValidation_label.setForeground(new Color(220, 20, 60));
		coin_entryValidation_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblTotalAmountDue = new JLabel("Total Amount due: ");
		
		total_amount_due_label = new JLabel("$ 0");
		total_amount_due_label.setBackground(Color.WHITE);
		total_amount_due_label.setOpaque(true);
		
		JLabel lblEnterMessage = new JLabel("<html>Please enter the total due amount using coin/cash slot\n</html>");
		lblEnterMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setBackground(new Color(255, 255, 255));
		lblCheckOut.setOpaque(true);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(79, Short.MAX_VALUE)
					.addComponent(coin_entryValidation_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
					.addGap(75))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEnterMessage, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(151)
							.addComponent(lblTotalAmountDue)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(total_amount_due_label, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 165, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(226, Short.MAX_VALUE)
					.addComponent(lblCheckOut)
					.addGap(224))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblCheckOut)
					.addGap(49)
					.addComponent(lblEnterMessage)
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTotalAmountDue)
						.addComponent(total_amount_due_label))
					.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
					.addComponent(coin_entryValidation_label)
					.addGap(35))
		);
		setLayout(groupLayout);
		
	}

	public void setLabels(double amtDue, List<Integer> itemCodeIntList) {
		total_amount_due_label.setText(Double.toString(amtDue));
		passOnProdDispenseMessage= itemCodeIntList.toString();
	}
	
	public String getProdDispenseMessage(){
		return passOnProdDispenseMessage;
	}

}
