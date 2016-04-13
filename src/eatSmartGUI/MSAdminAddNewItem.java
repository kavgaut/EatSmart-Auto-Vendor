package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.event.MouseMotionAdapter;

public class MSAdminAddNewItem extends JPanel{
	private JTextField itemName_txt;
	private JTextField calorie_txt;
	private JTextField sugar_txt;
	private JTextField carbohydrate_txt;
	private JTextField cholesterol_txt;
	private JTextField total_fat_txt;
	private JTextField protein_txt;
	private JTextField price_txt;
	private Object cmboitem;
	Item item = null;
	ItemDaoImpl dao = null;
	String itemName = null;
	String itemType;
	int calorieValue;
	String ingredientInfo; 
	String sugarContent;
	String carbContent;
	String cholestrolContent;
	String fatContent;
	String ProteinContent;
	String glutenFree = "N"; 
	String lowFat = "N"; 
	String lowSugar = "N";
	double itemPrice;
	
	TextFieldValidator textValidate = new TextFieldValidator();
	ConfirmationDialog dialog;
	
	public MSAdminAddNewItem(){
		this.setSize(711, 467);
		dao = new ItemDaoImpl();
		JLabel title_label = new JLabel("Introduce a new Item into EatSmart database");
		title_label.setFont(new Font("Lao MN", Font.PLAIN, 14));
		title_label.setOpaque(true);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setBackground(new Color(176, 196, 222));
		
		JLabel lblItemCode = new JLabel("Item Code\n");
		lblItemCode.setOpaque(true);
		lblItemCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemCode.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemCode.setBackground(Color.WHITE);
		
		JLabel show_autoItemCode_label = new JLabel("   ");
		show_autoItemCode_label.setOpaque(true);
		show_autoItemCode_label.setHorizontalAlignment(SwingConstants.CENTER);
		show_autoItemCode_label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		show_autoItemCode_label.setBackground(Color.WHITE);
		
		JLabel lblItemName = new JLabel("Item Name\n");
		lblItemName.setOpaque(true);
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemName.setBackground(Color.WHITE);
		
		itemName_txt = new JTextField();
		itemName_txt.setColumns(10);
		
		JLabel lblItemType = new JLabel("Item Type\n");
		lblItemType.setOpaque(true);
		lblItemType.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemType.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemType.setBackground(Color.WHITE);
		
		JLabel lblTotalCalorieVaue = new JLabel("Total Calorie value");
		lblTotalCalorieVaue.setOpaque(true);
		lblTotalCalorieVaue.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalCalorieVaue.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblTotalCalorieVaue.setBackground(Color.WHITE);
		
		JComboBox selectType_comboBox = new JComboBox();
		selectType_comboBox.setModel(new DefaultComboBoxModel(new String[] {"DRINKS", "PACKAGED", "PERISHABLE"}));
		selectType_comboBox.setSelectedIndex(0);
		
		calorie_txt = new JTextField();
		calorie_txt.setColumns(10);
		
		JLabel lblIngredientInfo = new JLabel("Ingredient Info");
		lblIngredientInfo.setOpaque(true);
		lblIngredientInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblIngredientInfo.setBackground(Color.WHITE);
		
		JTextArea ingredient_textArea = new JTextArea();
		ingredient_textArea.setText("  ");
		
		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setOpaque(true);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSugar.setBackground(Color.WHITE);
		
		sugar_txt = new JTextField();
		sugar_txt.setColumns(10);
		
		JLabel lblCarbohydrate = new JLabel("Carbohydrate");
		lblCarbohydrate.setOpaque(true);
		lblCarbohydrate.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarbohydrate.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCarbohydrate.setBackground(Color.WHITE);
		
		JLabel lblCholesterol = new JLabel("Cholesterol");
		lblCholesterol.setOpaque(true);
		lblCholesterol.setHorizontalAlignment(SwingConstants.CENTER);
		lblCholesterol.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCholesterol.setBackground(Color.WHITE);
		
		JLabel lbl_totalFat = new JLabel("Total Fat");
		lbl_totalFat.setOpaque(true);
		lbl_totalFat.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_totalFat.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lbl_totalFat.setBackground(Color.WHITE);
		
		JLabel lblProtein = new JLabel("Protein");
		lblProtein.setOpaque(true);
		lblProtein.setHorizontalAlignment(SwingConstants.CENTER);
		lblProtein.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblProtein.setBackground(Color.WHITE);
		
		carbohydrate_txt = new JTextField();
		carbohydrate_txt.setColumns(10);
		
		cholesterol_txt = new JTextField();
		cholesterol_txt.setColumns(10);
		
		total_fat_txt = new JTextField();
		total_fat_txt.setColumns(10);
		
		protein_txt = new JTextField();
		protein_txt.setColumns(10);
		
		JLabel lblG = new JLabel("g");
		
		JLabel label = new JLabel("g");
		
		JLabel lblMg = new JLabel("mg");
		
		JLabel label_2 = new JLabel("g");
		
		JLabel label_3 = new JLabel("g");
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setOpaque(true);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblPrice.setBackground(Color.WHITE);
		
		price_txt = new JTextField();
		price_txt.setColumns(10);
		
		JCheckBox chckbxGlutenFree = new JCheckBox("Gluten Free");
		
		JCheckBox chckbxLowFat = new JCheckBox("Low Fat");
		
		JCheckBox chckbxLowSugar = new JCheckBox("Low Sugar");
		
		JLabel lblValidation = new JLabel("    ");
		lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidation.setForeground(Color.RED);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		selectType_comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selectType_comboBox.getSelectedItem()!=null){
					//cmboitem = selectType_comboBox.getSelectedItem();
				}
			}
		});
		
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String priceVal = null;
				cmboitem = selectType_comboBox.getSelectedItem();
				lblValidation.setText("");
				if(cmboitem!=null){
					if("Perishable".equalsIgnoreCase(cmboitem.toString())){
						item = new PerishableFoods();
						itemType = "Perishable";
					}
					if("Packaged".equalsIgnoreCase(cmboitem.toString())){
						item = new PackagedFood();
						itemType = "Packaged";
					}
					if("Drinks".equalsIgnoreCase(cmboitem.toString())){
						item = new Drinks();
						itemType = "Drinks";
					}
				}
				
				if(itemName_txt.getText().trim().length()>0){
					itemName = itemName_txt.getText();
				}
				if(calorie_txt.getText().trim().length()>0){
					calorieValue = Integer.parseInt(calorie_txt.getText());
				}
				if(ingredient_textArea.getText().trim().length()>0){
					ingredientInfo = ingredient_textArea.getText();
				}
				if(sugar_txt.getText().trim().length()>0){
					sugarContent = sugar_txt.getText();
				}
				if(carbohydrate_txt.getText().trim().length()>0){
					carbContent = carbohydrate_txt.getText();
				}
				if(cholesterol_txt.getText().trim().length()>0){
					cholestrolContent = cholesterol_txt.getText();
				}
				if(total_fat_txt.getText().trim().length()>0){
					fatContent = total_fat_txt.getText();
				}
				if(protein_txt.getText().trim().length()>0){
					ProteinContent = protein_txt.getText();
				}
				if(chckbxGlutenFree.isSelected()){
					glutenFree = "Y";
				}
				if(chckbxLowFat.isSelected()){
					lowFat = "Y";
				}
				if(chckbxLowSugar.isSelected()){
					lowSugar = "Y";
				}
				if(price_txt.getText().trim().length()>0){
					priceVal = price_txt.getText();
					
				}
				
				if(itemName == null || itemName.trim().equals("")){
					lblValidation.setText("Please enter values for Item name, price and ingredient info and click on submit");
				}
				else if(priceVal == null || priceVal.trim().equals("")){
					lblValidation.setText("Please enter values for Item name, price and ingredient info and click on submit");
				}
				else if(ingredientInfo == null || ingredientInfo.trim().equals("")){
					lblValidation.setText("Please enter values for Item name, price and ingredient info and click on submit");
				}
				else{
					if((textValidate.validateTextField(itemName)) && (textValidate.validateTextField(ingredientInfo))
							&& (textValidate.validateDecimalField(priceVal))){
						itemPrice = Double.parseDouble(priceVal);
						dao.insertItem(itemName, itemType, calorieValue, ingredientInfo, sugarContent, carbContent, cholestrolContent, fatContent, ProteinContent, glutenFree, lowFat, lowSugar, itemPrice);
						lblValidation.setText("");
						dialog = new ConfirmationDialog();
						dialog.lblMessageLabel.setText("<html>The new item has been added to the <br/>database with the item code:<html/>" + dao.newGeneratedItemCode);
				
					}
					else if((textValidate.validateTextField(itemName)) == false){
						lblValidation.setText("Please enter a correct value for item name, ingredient info and item price");
					}
					else if((textValidate.validateTextField(ingredientInfo)) == false){
						lblValidation.setText("Please enter a correct value for item name, ingredient info and item price");
					}
					else if((textValidate.validateTextField(priceVal)) == false){
						lblValidation.setText("Please enter a correct value for item name, ingredient info and item price");
					}
					
				}
				
				itemName_txt.setText("");
				calorie_txt.setText("");
				ingredient_textArea.setText("");
				sugar_txt.setText("");
				carbohydrate_txt.setText("");
				cholesterol_txt.setText("");
				total_fat_txt.setText("");
				protein_txt.setText("");
				price_txt.setText("");
				chckbxGlutenFree.setSelected(false);
				chckbxLowFat.setSelected(false);
				chckbxLowSugar.setSelected(false);
					
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(167)
							.addComponent(title_label, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblItemCode, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
											.addGap(51)
											.addComponent(show_autoItemCode_label, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblItemName, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
											.addGap(51)
											.addComponent(itemName_txt, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblItemType, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
											.addGap(51)
											.addComponent(selectType_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblTotalCalorieVaue, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addGap(36)
											.addComponent(calorie_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
									.addGap(27)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lbl_totalFat, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(total_fat_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCholesterol, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(cholesterol_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblMg, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblCarbohydrate, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(carbohydrate_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(label, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(btnSubmit)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblProtein, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(protein_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblIngredientInfo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSugar, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
									.addGap(36)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(sugar_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblG))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(ingredient_textArea, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
												.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxLowSugar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
												.addComponent(chckbxLowFat, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
												.addComponent(chckbxGlutenFree))))))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addComponent(lblValidation, GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
					.addGap(54))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(title_label)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblValidation)
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblItemCode)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(show_autoItemCode_label)
							.addComponent(lblCarbohydrate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(carbohydrate_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblItemName))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(itemName_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCholesterol, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
							.addComponent(cholesterol_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblMg)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblItemType))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(selectType_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lbl_totalFat, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(5)
									.addComponent(lblTotalCalorieVaue))
								.addComponent(calorie_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblProtein, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
									.addComponent(protein_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_3))))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(total_fat_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_2))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIngredientInfo)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(1)
									.addComponent(ingredient_textArea, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(price_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSugar)
								.addComponent(sugar_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblG)
								.addComponent(btnSubmit)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(chckbxGlutenFree)
							.addGap(18)
							.addComponent(chckbxLowFat)
							.addGap(18)
							.addComponent(chckbxLowSugar)))
					.addGap(24))
		);
		setLayout(groupLayout);
	}
}
