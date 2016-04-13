package eatSmartGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MSAdminViewVM extends JPanel{
	
	private JLabel vm_id_label;
	String selectedVM;
	VMItemDaoImpl dao = null;
	JComboBox items_list_comboBox = null;
	Object cmboitem = null;
	VMItem vmItem = null;
	JLabel rackQty_label = null;
	JLabel label = null;
	
	public MSAdminViewVM(String newSelectedVM){
		this.setSize(386, 228);
		setBorder(new LineBorder(Color.black, 1));
		selectedVM = newSelectedVM;
		dao = new VMItemDaoImpl();
		vmItem = dao.getVMItem(selectedVM);
		JLabel lblVmId = new JLabel("VM ID: ");
		
		vm_id_label = new JLabel("   ");
		vm_id_label.setOpaque(true);
		vm_id_label.setBackground(Color.white);
		
		vm_id_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblItemCodes = new JLabel("Items in this VM");
		
		items_list_comboBox = new JComboBox();
		
		JLabel lblRackQty = new JLabel("Rack Quantity");
		
		JButton btnRestock = new JButton("Restock");
		btnRestock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rackQty = vmItem.getRackQty().get(Integer.parseInt(cmboitem.toString()));
				if(rackQty<10){
					dao.updateRackQty(Integer.parseInt(cmboitem.toString()), selectedVM, 10);
					updateVMItemRackStatusInfo();
				}
			}
		});
		rackQty_label = new JLabel("   ");
		rackQty_label.setOpaque(true);
		rackQty_label.setHorizontalAlignment(SwingConstants.CENTER);
		rackQty_label.setBackground(Color.WHITE);
		
		JLabel lblStatus = new JLabel("Status");
		
		label = new JLabel("   ");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBackground(Color.WHITE);
		updateVMListInfo();
		// on load
		updateVMItemRackStatusInfo();
		// on change		
		items_list_comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateVMItemRackStatusInfo();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVmId)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(vm_id_label, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
							.addGap(105))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblRackQty, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblItemCodes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblStatus, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
								.addComponent(rackQty_label, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
								.addComponent(items_list_comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(101, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(195, Short.MAX_VALUE)
					.addComponent(btnRestock)
					.addGap(95))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVmId)
						.addComponent(vm_id_label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemCodes)
						.addComponent(items_list_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRackQty)
						.addComponent(rackQty_label))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatus)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(btnRestock)
					.addGap(17))
		);
		setLayout(groupLayout);
	}
	
	public void updateVMListInfo(){
		if(selectedVM.trim().length()>0){
			vm_id_label.setText(selectedVM);
			List<Integer> itemList = vmItem.getItemId();
			for(int i=0;i<itemList.size();i++){
				items_list_comboBox.addItem(itemList.get(i));
			}
		}
	}
	public void updateVMItemRackStatusInfo(){
		vmItem = dao.getVMItem(selectedVM);
		cmboitem = items_list_comboBox.getSelectedItem();
		if(cmboitem!=null){
			rackQty_label.setText(Integer.toString(vmItem.getRackQty().get(Integer.parseInt(cmboitem.toString()))));
			label.setText(vmItem.getItem_status().get(Integer.parseInt(cmboitem.toString())));
		}
	}
}
