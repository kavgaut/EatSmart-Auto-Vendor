package eatSmartGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MSAdminChangePrice extends JPanel{
	private JTextField newPrice_txt;
	List<Integer> itemList = null;
	Object cmboitem =  null;
	ItemDaoImpl itemDao = null;
	JLabel lblValidation;
	public ConfirmationDialog dialog;
	
	public MSAdminChangePrice(){
		this.setSize(711, 465);
		itemList = new ArrayList<Integer>();
		JLabel title_label = new JLabel("Change an Item's price");
		title_label.setOpaque(true);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBackground(Color.WHITE);
		
		JLabel lblItemCode = new JLabel("Item Code\n");
		lblItemCode.setOpaque(true);
		lblItemCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemCode.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemCode.setBackground(Color.WHITE);
		
		JComboBox<Integer> itemCode_comboBox = new JComboBox<>();
		itemDao = new ItemDaoImpl();
		itemList = itemDao.getAllItemCodes();
		
		JLabel lblItemPrice = new JLabel("Item Price\n");
		lblItemPrice.setOpaque(true);
		lblItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemPrice.setBackground(Color.WHITE);
		
		newPrice_txt = new JTextField();
		newPrice_txt.setText("fetch its old value here");
		newPrice_txt.setColumns(10);
		
		for(int i=0; i<itemList.size();i++){
			itemCode_comboBox.addItem(itemList.get(i));
		}
		// on load
		cmboitem = itemCode_comboBox.getSelectedItem();
		if(cmboitem!=null){
			Item item = itemDao.findById(Integer.parseInt(cmboitem.toString()));
			newPrice_txt.setText(Double.toString(item.getItemPrice()));
		}
		// on change		
		itemCode_comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cmboitem = itemCode_comboBox.getSelectedItem();
				if(cmboitem!=null){
					Item item = itemDao.findById(Integer.parseInt(cmboitem.toString()));
					newPrice_txt.setText(Double.toString(item.getItemPrice()));
				}
			}
		});
		
		JButton btnChange = new JButton("Change");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int itemID = Integer.parseInt(cmboitem.toString());
				if(newPrice_txt.getText().trim().length()>0){
					double priceVal = Double.parseDouble(newPrice_txt.getText());
					lblValidation.setText("");
					itemDao.updateItemPrice(itemID,priceVal);
					dialog = new ConfirmationDialog();
					dialog.lblMessageLabel.setText("New price has been updated for the item code: " + itemID);
				}
				else lblValidation.setText("Please enter a price value");
				
			}
		});
		
		lblValidation = new JLabel("");
		lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblValidation.setForeground(Color.RED);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(90)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblItemCode, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblItemPrice, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(newPrice_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemCode_comboBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(331, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(504, Short.MAX_VALUE)
					.addComponent(btnChange)
					.addGap(116))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(207)
					.addComponent(title_label, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(167, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(104, Short.MAX_VALUE)
					.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(title_label, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemCode, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(itemCode_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemPrice, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(newPrice_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(63)
					.addComponent(btnChange)
					.addContainerGap(146, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
	}
}
