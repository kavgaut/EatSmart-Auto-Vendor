package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JTextArea;

public class UDMemberPage4_Info extends JPanel implements PanelColleague{
	private JTextField item_entry_txt;
	private MainPageGUI mainFrame;
	private JPanel info_display_panel;
	Item item = null;
	int itemcode = 0;
	String itemName = "";
	double calVal = 0;
	String ingredientInf = "";
	String sugar = "";
	String carb ="";
	String cholestrol ="";
	String gluten ="";
	String protein ="";
	String lowFat = "";	
	ItemDaoImpl dao = new ItemDaoImpl();
	JLabel item_code_validation_label;
	JLabel show_itemcode_label;
	JLabel show_itemname_label;
	JLabel show_calorie_label;
	JLabel show_sugar_label;
	JLabel show_carbohydrate_label;
	JLabel show_cholestrol_label;
	JLabel show_fat_label;
	JTextArea ingredient_info_txtArea;
	JLabel show_protein;
	final String html1 = "<html><body style='width: ";
    final String html2 = "px'>";
	
	TextFieldValidator textValidate = new TextFieldValidator();
	
	public UDMemberPage4_Info(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		JLabel lblEnterItemCode = new JLabel("Enter Item Code");
		
		item_entry_txt = new JTextField();
		item_entry_txt.setColumns(10);
		
		info_display_panel = new JPanel();
		info_display_panel.setVisible(false);
		
		JLabel info_title_label = new JLabel("See Calorie and Ingredient information");
		info_title_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel item_entry_next_button = new JLabel("");
		item_entry_next_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//if condition - only after the item code entry validation is success
				// Another task here is  to retrieve values from db and set all the labels of this panel
				String itemCode = null;
				if(item_entry_txt.getText().trim().length()>0){
					itemCode = item_entry_txt.getText();
					if(textValidate.validateNumberField(itemCode)){
						item = dao.findById(Integer.parseInt(itemCode));
						if(item !=null){
							itemcode = item.getItemCode();
							show_itemcode_label.setText(Integer.toString(itemcode)); 
							itemName = item.getItemName();
							show_itemname_label.setText(itemName); 
							calVal = item.getCalValue();
							show_calorie_label.setText(Double.toString(calVal));
							ingredientInf = item.getIngredientInfo();
							ingredient_info_txtArea.setText(/*html1 + "100" + html2 +*/ ingredientInf);
							sugar = item.getSugarVal();
							show_sugar_label.setText(sugar);
							carb = item.getCarbohydrateValue();
							show_carbohydrate_label.setText(carb);
							cholestrol = item.getCholestrolValue();
							show_cholestrol_label.setText(cholestrol);
							lowFat = item.getTotalFatValue();
							show_fat_label.setText(lowFat);
							protein = item.getProteinValue();
							show_protein.setText(protein);
							info_display_panel.setVisible(true);
						}else{
							item_code_validation_label.setText("Please enter valid Item Code.");
						}
					}
					else{
						item_code_validation_label.setText("Please enter valid Item Code");
						System.out.println("cannot process request");
					}
					
				}else{
					item_code_validation_label.setText("Please enter Item Code");
				}
			}
		});
		item_entry_next_button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		item_entry_next_button.setIcon(new ImageIcon("/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/nextButton.jpg"));
		
		item_code_validation_label = new JLabel("    ");
		item_code_validation_label.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		item_code_validation_label.setForeground(Color.RED);
		item_code_validation_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel button_panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(69)
							.addComponent(info_title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(90)
							.addComponent(item_code_validation_label, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(105)
							.addComponent(lblEnterItemCode)
							.addGap(14)
							.addComponent(item_entry_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(item_entry_next_button))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(info_display_panel, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE))))
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(info_title_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(item_code_validation_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEnterItemCode)
							.addComponent(item_entry_txt, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(item_entry_next_button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(info_display_panel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_panel, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton goBackMainMenu_Button = new JButton("Go Back to Main Menu");
		goBackMainMenu_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//mainFrame.createMainMenuPage(mainFrame.getPage4Info());
				sendPage(mainFrame.createMainMenuPage());
			}
		});
		goBackMainMenu_Button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		button_panel.add(goBackMainMenu_Button);
		
		JLabel lblItemCode = new JLabel("Item Code: ");
		
		show_itemcode_label = new JLabel("  ");
		show_itemcode_label.setBackground(Color.WHITE);
		show_itemcode_label.setOpaque(true);
		
		JLabel lblItemName = new JLabel("Item Name:  ");
		
		show_itemname_label = new JLabel("  ");
		show_itemname_label.setOpaque(true);
		show_itemname_label.setBackground(Color.WHITE);
		
		JLabel lblTotalCalories = new JLabel("Total Calories: ");
		
		show_calorie_label = new JLabel("  ");
		show_calorie_label.setOpaque(true);
		show_calorie_label.setBackground(Color.WHITE);
		
		JLabel lblIngredi = new JLabel("Ingredient Info:");
		
		
		JLabel lblSugar = new JLabel("Sugar:");
		
		JLabel lblCarbohydrates = new JLabel("Carbohydrates:");
		
		JLabel lblCholestrol = new JLabel("Cholesterol:");
		
		JLabel lblTotalFat = new JLabel("Total Fat:");
		
		JLabel lblProtein = new JLabel("Protein:");
		
		show_sugar_label = new JLabel("  ");
		show_sugar_label.setOpaque(true);
		show_sugar_label.setBackground(Color.WHITE);
		
		show_carbohydrate_label = new JLabel("  ");
		show_carbohydrate_label.setOpaque(true);
		show_carbohydrate_label.setBackground(Color.WHITE);
		
		show_cholestrol_label = new JLabel("  ");
		show_cholestrol_label.setOpaque(true);
		show_cholestrol_label.setBackground(Color.WHITE);
		
		show_fat_label = new JLabel("  ");
		show_fat_label.setOpaque(true);
		show_fat_label.setBackground(Color.WHITE);
		
		show_protein = new JLabel("  ");
		show_protein.setOpaque(true);
		show_protein.setBackground(Color.WHITE);
		
		ingredient_info_txtArea = new JTextArea();
		ingredient_info_txtArea.setText("  ");
		ingredient_info_txtArea.setEditable(false);
		ingredient_info_txtArea.setLineWrap(true);
		ingredient_info_txtArea.setWrapStyleWord(true);
		
		GroupLayout gl_info_display_panel = new GroupLayout(info_display_panel);
		gl_info_display_panel.setHorizontalGroup(
			gl_info_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_info_display_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_info_display_panel.createSequentialGroup()
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTotalCalories)
								.addComponent(lblItemName)
								.addComponent(lblItemCode, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(show_itemcode_label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addComponent(show_itemname_label, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
								.addComponent(show_calorie_label, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
							.addGap(47)
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSugar)
								.addComponent(lblCarbohydrates)
								.addComponent(lblCholestrol, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_info_display_panel.createSequentialGroup()
							.addComponent(lblIngredi)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ingredient_info_txtArea, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProtein)
								.addComponent(lblTotalFat))))
					.addGap(18)
					.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(show_sugar_label, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(show_carbohydrate_label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(show_cholestrol_label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(show_fat_label, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
						.addComponent(show_protein, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		gl_info_display_panel.setVerticalGroup(
			gl_info_display_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_info_display_panel.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_info_display_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItemCode)
						.addComponent(lblSugar)
						.addComponent(show_itemcode_label)
						.addComponent(show_sugar_label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_info_display_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCarbohydrates)
							.addComponent(show_carbohydrate_label))
						.addGroup(gl_info_display_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblItemName)
							.addComponent(show_itemname_label)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_info_display_panel.createSequentialGroup()
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCholestrol)
								.addGroup(gl_info_display_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblTotalCalories)
									.addComponent(show_calorie_label)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_info_display_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_info_display_panel.createSequentialGroup()
									.addComponent(lblTotalFat)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblProtein))
								.addGroup(gl_info_display_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblIngredi)
									.addComponent(ingredient_info_txtArea, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_info_display_panel.createSequentialGroup()
							.addComponent(show_cholestrol_label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(show_fat_label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(show_protein)))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		info_display_panel.setLayout(gl_info_display_panel);
		setLayout(groupLayout);
		
	}
	
	public void sendPage(JPanel newPage){
		mainFrame.replacePage(this,newPage);
	}

	@Override
	public void setLabels(double amoutDue, double balance, int points,
			List<Integer> itemCodeList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabel(double CurrentBalance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeValues(double totalAmtDue, List<Integer> itemList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(double newBal, int pointsRem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(String itemCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		// TODO Auto-generated method stub
		
	}
}
