package eatSmartGUI;

import java.awt.Color;
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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MSAdminManageVM extends JPanel{
	
	private JLabel vm_id_label;
	String newVM;
	VMItem vmItem = null;
	VMItemDaoImpl dao = null;
	ItemDaoImpl itemDao = null;
	JComboBox existing_items_comboBox = null;
	JComboBox new_items_comboBox = null;
	
	public MSAdminManageVM(String VMId){
		this.setSize(386, 228);
		setBorder(new LineBorder(Color.black, 1));
		newVM = VMId;
		dao = new VMItemDaoImpl();
		itemDao = new ItemDaoImpl();
		vmItem = dao.getVMItem(newVM);
		JLabel lblVmId = new JLabel("VM ID: ");
		
		vm_id_label = new JLabel("   ");
		vm_id_label.setOpaque(true);
		vm_id_label.setBackground(Color.white);
		
		vm_id_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblItemCodes = new JLabel("Item to be Replaced\n");
		
		existing_items_comboBox = new JComboBox();
		
		JLabel lblNewItemTo = new JLabel("New Item to be added");
		
		new_items_comboBox = new JComboBox();
		updateVMListInfo();
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 int oldItemCode	= Integer.parseInt(existing_items_comboBox.getSelectedItem().toString());
			 int newItemCode	= Integer.parseInt(new_items_comboBox.getSelectedItem().toString());
			 dao.updateOldItemNewItem(oldItemCode, newItemCode, newVM);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(37)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVmId)
								.addComponent(lblItemCodes)
								.addComponent(lblNewItemTo, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(existing_items_comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(new_items_comboBox, 0, 91, Short.MAX_VALUE)
								.addComponent(vm_id_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(142)
							.addComponent(btnUpdate)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(22)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVmId)
						.addComponent(vm_id_label))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemCodes)
						.addComponent(existing_items_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewItemTo)
						.addComponent(new_items_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnUpdate)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	public void updateVMListInfo(){
		if(newVM.trim().length()>0){
			vm_id_label.setText(newVM);
			List<Integer> existingItemList = vmItem.getItemId();
			List<Integer> allItemList = itemDao.getAllItemCodes();
			List<Integer> newItemList = new ArrayList<Integer>();
			for(int i=0;i<existingItemList.size();i++){
				existing_items_comboBox.addItem(existingItemList.get(i));
			}
			allItemList.removeAll(existingItemList);
			for(int i=0;i<allItemList.size();i++){
				new_items_comboBox.addItem(allItemList.get(i));
			}
		}
	}
}
