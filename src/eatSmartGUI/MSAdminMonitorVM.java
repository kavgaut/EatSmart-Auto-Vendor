package eatSmartGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextArea;

public class MSAdminMonitorVM extends JPanel{
	
	private JTabbedPane adminTabbedPane;
	private JPanel monitorVMPanel;
	private JPanel checkSalesPanel;
	private JPanel introduceNewItemPanel;
	private JPanel changePricePanel;
	private JPanel addNutriInfoPanel;
	
	private JPanel vmStatusPanel;
	private JScrollPane msScrollPane;
	private JTable vm_ms_table;
	private String[][] data;
	private String[] headers;
	private int rowSelected;
	private MyTableModel model;
	
	private MyComponent pieComponent;
	private JPanel showPiePanel;
	
	private JPanel display1_panel;
	private GroupLayout gl_display1_panel;
	
	private MSAdminViewVM viewVM;
	private MSAdminManageVM manageVM;
	private MSAdminCheckSales sales;
	private MSAdminAddNewItem newItem;
	private MSAdminChangePrice changePrice;
	private MSAdminAddNutriInfo nutriInfo;
	
	private JPanel currentShowPanel;
	private JPanel currentAdminTab;
	
	private JLabel lblValidation ;
	private JButton btnViewSelectedVm;
	private JButton btnViewPieChart;
	
	public VMItemDaoImpl dao;
	public String selectedVM;
	private JPanel legendPanel;
	private JLabel label_black;
	private JLabel name1_label;
	private JLabel name2_label;
	private JLabel label_green;
	private JLabel name3_label;
	private JLabel label_yellow;
	private JLabel name4_label;
	private JLabel label_red;
	
	private String userName;
	private String currentUserID;
	private JLabel lblWelcomeUser;
	private JLabel lblLogout;
	private JLabel lblPieTitle;
	
	public MSAdminMonitorVM(String userID, String userName) {
		
		this.userName = userName;
		this.currentUserID = userID;
		currentAdminTab = this;
		
		this.setSize(776, 567);
		setBorder(new LineBorder(Color.black, 5));
		
		adminTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		adminTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(adminTabbedPane, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(adminTabbedPane, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		monitorVMPanel = new JPanel();
		checkSalesPanel = new JPanel();
		introduceNewItemPanel = new JPanel();
		changePricePanel = new JPanel();
		addNutriInfoPanel = new JPanel();
		
		adminTabbedPane.add("<html>Monitor VM<html/>", monitorVMPanel);
		
		JLabel label = new JLabel("Welcome to EatSmart Remote Administration System\n");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("PT Serif Caption", Font.BOLD, 14));
		label.setBorder(new LineBorder(Color.GRAY, 2));
		label.setBackground(SystemColor.textHighlight);
		
		display1_panel = new JPanel();
		
	/*	JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				State stopAdminState = new AdminStopState(Application.mainFrame);
				stopAdminState.doAction(Login.adminRole);
			}
		});*/
		
		lblWelcomeUser = new JLabel("Welcome User");
		lblWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeUser.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblWelcomeUser.setForeground(Color.BLUE);
		lblWelcomeUser.setBackground(Color.WHITE);
		lblWelcomeUser.setOpaque(true);
		
		lblWelcomeUser.setText("Welcome  " + userName);
		
		lblLogout = new JLabel("Logout");
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				State stopAdminState = new AdminStopState(Application.mainFrame);
				stopAdminState.doAction(Login.adminRole);
				Application.mainFrame.isLoginSuccess = false;
			}
		});
		lblLogout.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblLogout.setOpaque(true);
		lblLogout.setForeground(Color.RED);
		lblLogout.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblLogout.setBackground(SystemColor.window);
		
		GroupLayout gl_monitorVMPanel = new GroupLayout(monitorVMPanel);
		gl_monitorVMPanel.setHorizontalGroup(
			gl_monitorVMPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_monitorVMPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_monitorVMPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(display1_panel, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_monitorVMPanel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 433, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblWelcomeUser, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblLogout, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_monitorVMPanel.setVerticalGroup(
			gl_monitorVMPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_monitorVMPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_monitorVMPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWelcomeUser)
						.addComponent(lblLogout))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(display1_panel, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel label_1 = new JLabel("Vending Machine Status");
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBackground(Color.WHITE);
		
		//steps to load a the table on page load itself
		//processTableData(result);
		dao = new VMItemDaoImpl();
		headers = new String[]{"VM ID", "Assigned Operator", "MS ID",  "VM Status", "Location"};

		vmStatusPanel = new JPanel();
				
		
		loadTableData();
				
		vmStatusPanel.setLayout(new GridLayout(1, 1));
		vmStatusPanel.setLayout(new BoxLayout(vmStatusPanel, BoxLayout.PAGE_AXIS));
		vmStatusPanel.add(msScrollPane);
		vmStatusPanel.repaint();
		vmStatusPanel.revalidate();
		
		showPiePanel = new JPanel();
		currentShowPanel = showPiePanel;
		pieComponent = new MyComponent();
		showPiePanel.setLayout(new BorderLayout());
		showPiePanel.add(pieComponent, BorderLayout.CENTER);
		
		JButton btnManageSelectedVm = new JButton("Manage selected VM");
		btnManageSelectedVm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRowIndex = vm_ms_table.getSelectedRow();
				if(selectedRowIndex == -1){
					lblValidation.setText("Please select a row first");
				}
				else{
					lblValidation.setText("");
					int selectedColumnIndex = vm_ms_table.getSelectedColumn();
					Object selectedObject = (Object) vm_ms_table.getModel().getValueAt(selectedRowIndex, 0);
					if(selectedObject!=null){
						selectedVM = selectedObject.toString();
						createManageVMPanel(selectedVM);
						legendPanel.setVisible(false);
						lblPieTitle.setVisible(false);
					}
				}
				
			}
		});
		btnManageSelectedVm.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		btnViewSelectedVm = new JButton("View selected VM");
		btnViewSelectedVm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRowIndex = vm_ms_table.getSelectedRow();
				if(selectedRowIndex == -1){
					lblValidation.setText("Please select a row first");
				}
				else{
					lblValidation.setText("");
					int selectedColumnIndex = vm_ms_table.getSelectedColumn();
					Object selectedObject = (Object) vm_ms_table.getModel().getValueAt(selectedRowIndex, 0);
					if(selectedObject!=null){
						String selectedVM = selectedObject.toString();
						createViewVMPanel(selectedVM);
						legendPanel.setVisible(false);
						lblPieTitle.setVisible(false);
					}
				}
			}
		});
		btnViewSelectedVm.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		btnViewPieChart = new JButton("Performance PieChart");
		btnViewPieChart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createShowPiePanel();
				legendPanel.setVisible(true);
				lblPieTitle.setVisible(true);
			}
		});
		btnViewPieChart.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		lblValidation = new JLabel("");
		lblValidation.setForeground(Color.RED);
		lblValidation.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		
		legendPanel = new JPanel();
		legendPanel.setVisible(true);
		
		lblPieTitle = new JLabel("<html>Item Sales Statistics</html>");
		lblPieTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPieTitle.setBackground(new Color(255, 255, 255));
		lblPieTitle.setOpaque(true);
		lblPieTitle.setVisible(true);
				
		//end of steps
		
		gl_display1_panel = new GroupLayout(display1_panel);
		gl_display1_panel.setHorizontalGroup(
			gl_display1_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_display1_panel.createSequentialGroup()
					.addGroup(gl_display1_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_display1_panel.createSequentialGroup()
							.addGap(285)
							.addComponent(label_1))
						.addGroup(gl_display1_panel.createSequentialGroup()
							.addGroup(gl_display1_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_display1_panel.createSequentialGroup()
									.addContainerGap()
									.addComponent(legendPanel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_display1_panel.createSequentialGroup()
									.addGap(15)
									.addComponent(lblPieTitle, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addComponent(showPiePanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_display1_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_display1_panel.createSequentialGroup()
									.addGap(76)
									.addComponent(lblValidation, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
								.addGroup(gl_display1_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_display1_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnViewSelectedVm)
										.addComponent(btnViewPieChart)
										.addComponent(btnManageSelectedVm))))))
					.addGap(33))
				.addGroup(gl_display1_panel.createSequentialGroup()
					.addContainerGap(70, Short.MAX_VALUE)
					.addComponent(vmStatusPanel, GroupLayout.PREFERRED_SIZE, 584, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		gl_display1_panel.setVerticalGroup(
			gl_display1_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_display1_panel.createSequentialGroup()
					.addGap(5)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(vmStatusPanel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_display1_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_display1_panel.createSequentialGroup()
							.addGroup(gl_display1_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_display1_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(lblValidation)
									.addGap(18)
									.addComponent(btnViewPieChart)
									.addGap(18)
									.addComponent(btnViewSelectedVm)
									.addGap(18)
									.addComponent(btnManageSelectedVm))
								.addComponent(showPiePanel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(13, Short.MAX_VALUE))
						.addGroup(gl_display1_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(lblPieTitle, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(legendPanel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addGap(16))))
		);
		
		label_black = new JLabel("  ");
		label_black.setBackground(Color.BLACK);
		label_black.setOpaque(true);
		
		name1_label = new JLabel("  ");
		name1_label.setBackground(Color.WHITE);
		name1_label.setOpaque(true);
		name1_label.setText(pieComponent.vmNameList.get(0));
		
		name2_label = new JLabel("  ");
		name2_label.setOpaque(true);
		name2_label.setBackground(Color.WHITE);
		name2_label.setText(pieComponent.vmNameList.get(1));
		
		label_green = new JLabel("  ");
		label_green.setOpaque(true);
		label_green.setBackground(Color.GREEN);
		
		name3_label = new JLabel("  ");
		name3_label.setOpaque(true);
		name3_label.setBackground(Color.WHITE);
		name3_label.setText(pieComponent.vmNameList.get(2));
		
		label_yellow = new JLabel("  ");
		label_yellow.setOpaque(true);
		label_yellow.setBackground(Color.YELLOW);
		
		name4_label = new JLabel("  ");
		name4_label.setOpaque(true);
		name4_label.setBackground(Color.WHITE);
		name4_label.setText(pieComponent.vmNameList.get(3));
		
		label_red = new JLabel("  ");
		label_red.setOpaque(true);
		label_red.setBackground(Color.RED);
		GroupLayout gl_legendPanel = new GroupLayout(legendPanel);
		gl_legendPanel.setHorizontalGroup(
			gl_legendPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_legendPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(name4_label, 0, 0, Short.MAX_VALUE)
						.addComponent(name3_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(name2_label, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addComponent(name1_label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(12)
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(label_black, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_red, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_yellow, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_green, GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE))
					.addGap(72))
		);
		gl_legendPanel.setVerticalGroup(
			gl_legendPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_legendPanel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_black)
						.addComponent(name1_label))
					.addGap(18)
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(name2_label)
						.addComponent(label_green))
					.addGap(18)
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_yellow)
						.addComponent(name3_label))
					.addGap(19)
					.addGroup(gl_legendPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_red, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(name4_label))
					.addGap(269))
		);
		legendPanel.setLayout(gl_legendPanel);
		
		
		GroupLayout gl_vmStatusPanel = new GroupLayout(vmStatusPanel);
		gl_vmStatusPanel.setHorizontalGroup(
			gl_vmStatusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_vmStatusPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msScrollPane, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_vmStatusPanel.setVerticalGroup(
			gl_vmStatusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_vmStatusPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(msScrollPane, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		vmStatusPanel.setLayout(gl_vmStatusPanel);
		display1_panel.setLayout(gl_display1_panel);
		monitorVMPanel.setLayout(gl_monitorVMPanel);
		adminTabbedPane.add("<html>Check Sales<html/>", checkSalesPanel);
		GroupLayout gl_checkSalesPanel = new GroupLayout(checkSalesPanel);
		gl_checkSalesPanel.setHorizontalGroup(
			gl_checkSalesPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_checkSalesPanel.setVerticalGroup(
			gl_checkSalesPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 452, Short.MAX_VALUE)
		);
		checkSalesPanel.setLayout(gl_checkSalesPanel);
		/** CHECK SALES*/
		checkSalesPanel.add(createCheckSalesPanel());
		
		adminTabbedPane.add("<html>Add New Item<html/>", introduceNewItemPanel);
		GroupLayout gl_introduceNewItemPanel = new GroupLayout(introduceNewItemPanel);
		gl_introduceNewItemPanel.setHorizontalGroup(
			gl_introduceNewItemPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_introduceNewItemPanel.setVerticalGroup(
			gl_introduceNewItemPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 532, Short.MAX_VALUE)
		);
		
		/** Add new item*/
		introduceNewItemPanel.setLayout(gl_introduceNewItemPanel);
		introduceNewItemPanel.add(createAddNewItemPanel());
		
		adminTabbedPane.add("Change Item Price", changePricePanel);
		GroupLayout gl_changePricePanel = new GroupLayout(changePricePanel);
		gl_changePricePanel.setHorizontalGroup(
			gl_changePricePanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_changePricePanel.setVerticalGroup(
			gl_changePricePanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 532, Short.MAX_VALUE)
		);
		changePricePanel.setLayout(gl_changePricePanel);
		/**CHANGE PRICE*/
		changePricePanel.add(createChangePricePanel());
		
		adminTabbedPane.add("Add Nutri Info", addNutriInfoPanel);
		GroupLayout gl_addNutriInfoPanel = new GroupLayout(addNutriInfoPanel);
		gl_addNutriInfoPanel.setHorizontalGroup(
			gl_addNutriInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 733, Short.MAX_VALUE)
		);
		gl_addNutriInfoPanel.setVerticalGroup(
			gl_addNutriInfoPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 532, Short.MAX_VALUE)
		);
		addNutriInfoPanel.setLayout(gl_addNutriInfoPanel);
		/**ADD NUTRI*/
		addNutriInfoPanel.add(createAddNutriInfo());
		
		
		ChangeListener changeListener =	new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e){
				if(e.getSource() instanceof JTabbedPane){
					JTabbedPane pane = (JTabbedPane) e.getSource();
					int index = pane.getSelectedIndex();
					System.out.println("the selected tabbed pane is:" + index);
					if(index == 1){
						checkSalesPanel.removeAll();
						checkSalesPanel.add(createCheckSalesPanel());
					}
					else if(index == 3){
						changePricePanel.removeAll();
						changePricePanel.add(createChangePricePanel());
					}
					else if(index == 4){
						addNutriInfoPanel.removeAll();
						addNutriInfoPanel.add(createAddNutriInfo());
					}
				}
				
			}
		};
		adminTabbedPane.addChangeListener(changeListener);
		
	}
	
	public void loadTableData(){
		//dummy data for now
		/*data = new String[3][4];
		String[][] temp = {{"VM1", "University Library", "MS1", "Good"},{"VM2", "University Gym", "MS1", "Excellent"}, {"VM3", "KP clinic", "MS2", "Average"}};
		for(int i=0; i<3; i++) {
		    for(int j=0; j<temp[i].length; j++) {
		        data[i][j] = temp[i][j];
		    }
		}
		System.out.println(data);*/
		data = dao.getMonitorTable(currentUserID);
		
		//Load table from here
		model = new MyTableModel(data, headers);
		vm_ms_table = new JTable(model);
		vm_ms_table.setFillsViewportHeight(true);
		vm_ms_table.setRowSelectionAllowed(true);
		vm_ms_table.setSelectionMode(0);
		msScrollPane = new JScrollPane();
		msScrollPane.setBounds(getX(), getY(), 610, 133);
		msScrollPane.setViewportView(vm_ms_table);
		msScrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
	}
	
	public void createManageVMPanel(String newVM){
		manageVM = new MSAdminManageVM(newVM);
		gl_display1_panel.replace(getCurrentShowPanel(), manageVM);
		currentShowPanel = manageVM;
		
	}
	
	public void createViewVMPanel(String selectedVM){
		viewVM = new MSAdminViewVM(selectedVM);
		gl_display1_panel.replace(getCurrentShowPanel(), viewVM);
		currentShowPanel = viewVM;
	}
	
	public void createShowPiePanel(){
		gl_display1_panel.replace(getCurrentShowPanel(), showPiePanel);
		currentShowPanel = showPiePanel;
	}
	
	
	/*
	 * 
	 */
	public JPanel createCheckSalesPanel(){
		sales = new MSAdminCheckSales();
		currentAdminTab = sales;
		return sales;
	}
	
	public JPanel createAddNewItemPanel(){
		newItem = new MSAdminAddNewItem();
		currentAdminTab= newItem;
		return newItem;
	}
	
	public JPanel createChangePricePanel(){
		changePrice = new MSAdminChangePrice();
		currentAdminTab = changePrice;
		return changePrice;
	}
	
	public JPanel createAddNutriInfo(){
		nutriInfo = new MSAdminAddNutriInfo();
		currentAdminTab = nutriInfo;
		return nutriInfo;
	}
	
	public JPanel getCurrentShowPanel(){
		return currentShowPanel;
	}
	
	public JPanel getLoggedMonitorPanel(){
		return this;
	}
	
	public JPanel getCurrentAdminTab(){
		return currentAdminTab;
	}
}
