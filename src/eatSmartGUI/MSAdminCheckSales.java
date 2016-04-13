package eatSmartGUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

public class MSAdminCheckSales extends JPanel {

	private JLabel show_total_revenue;
	private JTable popular_table;
	private String[][] data;
	private String[] headers;
	private int rowSelected;
	private MyTableModel model;
	private JScrollPane popScrollPane;
	private JPanel table_panel;
	private JLabel show_ms_revenue;
	private JLabel show_vm_revenue;
	
	private EatSmartManagement manage;
	private List<SmartAutoVendor> msList;
	private List<SmartAutoVendor> vmList;
	private SmartAutoVendor station;
	private SmartAutoVendor machine;
	
	private JComboBox<String> ms_id_list;
	private JComboBox<String> vm_id_list;
	public VMItemDaoImpl dao;
	
	public MSAdminCheckSales() {
		
		
		manage = new EatSmartManagement();
		dao = new VMItemDaoImpl();
		
		//machine = new VendingMachine();
		msList = manage.getMSList();
		this.setSize(689, 439);
		//setBorder(new LineBorder(Color.black, 1));

		// steps to load a the table on page load itself
		// processTableData(result);
		headers = new String[] { "Item Code", "\t Item Name \t",
				"Total Sale Qty"};

		table_panel = new JPanel();

		loadTableData();

		table_panel.setLayout(new GridLayout(1, 1));
		table_panel.setLayout(new BoxLayout(table_panel,BoxLayout.PAGE_AXIS));
		table_panel.add(popScrollPane);
		table_panel.repaint();
		table_panel.revalidate();

		// end of steps

		JLabel lblTotalRevenue = new JLabel("Total Revenue");

		show_total_revenue = new JLabel("   ");
		show_total_revenue.setOpaque(true);
		show_total_revenue.setBackground(Color.white);
		show_total_revenue.setText(Double.toString(manage.getTotalRevenue()));

		show_total_revenue.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblIMSRevenue = new JLabel("MS Revenue\n");

		ms_id_list = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for(int i =0; i<msList.size(); i++)
			model.addElement(msList.get(i).getVendorID());
		ms_id_list.setModel(model);
		ms_id_list.setSelectedItem(null);
		
		vm_id_list = new JComboBox<String>();
		
		ms_id_list.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		        JComboBox<String> combo = (JComboBox<String>) event.getSource();
		        //String selectedMS = (String) combo.getSelectedItem();
		        int selectedIndex = (int) combo.getSelectedIndex();
		 
		        show_ms_revenue.setText(Double.toString(manage.getVMorMSRevenue(msList.get(selectedIndex))));
		        vmList = manage.getVMList(msList.get(selectedIndex));
		        
		        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		        for(int i =0; i<vmList.size(); i++)
					model.addElement(vmList.get(i).getVendorID());
		        vm_id_list.setModel(model);
		        show_vm_revenue.setText(Double.toString(manage.getVMorMSRevenue(vmList.get(vm_id_list.getSelectedIndex()))));
		        
		    }
		});
		
		vm_id_list.addActionListener(new ActionListener() {
			 
		    @Override
		    public void actionPerformed(ActionEvent event) {
		  
		        show_vm_revenue.setText(Double.toString(manage.getVMorMSRevenue(vmList.get(vm_id_list.getSelectedIndex()))));
		        
		    }
		});

		JLabel lbVMRevenue = new JLabel("VM Revenue");

		JLabel lblPerformanceAndSales = new JLabel(
				"Performance and Sales statistics");
		lblPerformanceAndSales.setBackground(new Color(210, 180, 140));
		lblPerformanceAndSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerformanceAndSales.setOpaque(true);

		JLabel lblRevenueDetails = new JLabel("Revenue Details");
		lblRevenueDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblRevenueDetails.setBackground(new Color(135, 206, 235));
		lblRevenueDetails.setOpaque(true);

		JLabel lblMostPopularTems = new JLabel("Most Popular Items");
		lblMostPopularTems.setHorizontalAlignment(SwingConstants.CENTER);
		lblMostPopularTems.setBackground(new Color(135, 206, 235));
		lblMostPopularTems.setOpaque(true);

		show_ms_revenue = new JLabel("   ");
		show_ms_revenue.setOpaque(true);
		show_ms_revenue.setHorizontalAlignment(SwingConstants.CENTER);
		show_ms_revenue.setBackground(Color.WHITE);

		show_vm_revenue = new JLabel("   ");
		show_vm_revenue.setOpaque(true);
		show_vm_revenue.setHorizontalAlignment(SwingConstants.CENTER);
		show_vm_revenue.setBackground(Color.WHITE);

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPerformanceAndSales, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(128, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblRevenueDetails, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
							.addComponent(lblMostPopularTems, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addGap(65))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lbVMRevenue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblIMSRevenue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(vm_id_list, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(ms_id_list, 0, 101, Short.MAX_VALUE)))
								.addComponent(lblTotalRevenue, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addComponent(show_total_revenue, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
									.addGap(84))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(show_vm_revenue, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addComponent(show_ms_revenue, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
									.addGap(86)))
							.addComponent(table_panel, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
							.addGap(18))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblPerformanceAndSales, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRevenueDetails, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMostPopularTems, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(table_panel, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(show_total_revenue)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblTotalRevenue)
									.addGap(33)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblIMSRevenue)
										.addComponent(ms_id_list, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(show_ms_revenue))
									.addGap(29)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbVMRevenue)
										.addComponent(vm_id_list, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(show_vm_revenue))))))
					.addGap(6))
		);

		GroupLayout gl_table_panel = new GroupLayout(table_panel);
		gl_table_panel.setHorizontalGroup(
			gl_table_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_table_panel.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addComponent(popScrollPane, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_table_panel.setVerticalGroup(
			gl_table_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_table_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(popScrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public void loadTableData() {
		// dummy data for now
		/*data = new String[2][3];
		String[][] temp = { { "101", "Veg Sandwich", "90"},
				{ "102", "Coke", "156"} };
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < temp[i].length; j++) {
				data[i][j] = temp[i][j];
			}
		}*/
		
		data = dao.getPopularTable();
		
		System.out.println(data);
		// Load table from here
		model = new MyTableModel(data, headers);
		popular_table = new JTable(model);
		popular_table.setFillsViewportHeight(true);
		popular_table.setRowSelectionAllowed(true);
		popular_table.setSelectionMode(0);
		popScrollPane = new JScrollPane();
		popScrollPane.setBounds(getX(), getY(), 593, 133);
		popScrollPane.setViewportView(popular_table);
		popScrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
	}
}
