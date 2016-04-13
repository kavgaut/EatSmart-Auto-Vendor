package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.event.MouseMotionAdapter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.SystemColor;

public class MSAdminAddNutriInfo extends JPanel{
	private JTextField calorie_txt;
	private JTextField sugar_txt;
	private JTextField carb_txt;
	private JTextField cholesterol_txt;
	private JTextField fat_txt;
	private JTextField protein_txt;
	List<Integer> itemList = null;
	ItemDaoImpl itemDao = null;
	Object cmboitem =null;
	JComboBox selectItem_comboBox = null;
	JLabel show_itemName_label = null;
	JTextArea ingredient_textArea = null;
	JCheckBox checkBoxGluten = null;
	JCheckBox checkBoxLowFat = null;
	JCheckBox checkBoxLowSugar = null;
	JLabel lblValidation;
	public ConfirmationDialog dialog;
	TextFieldValidator textValidate = new TextFieldValidator();

	
	public MSAdminAddNutriInfo() {
		this.setSize(711, 465);
		itemList = new ArrayList<Integer>();
		JLabel title_label = new JLabel("Add or Change Nutritional Info for an existing item");
		title_label.setOpaque(true);
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		title_label.setFont(new Font("Lao MN", Font.PLAIN, 14));
		title_label.setBackground(new Color(176, 196, 222));
		
		JLabel lblItemCode = new JLabel("Item Code\n");
		lblItemCode.setOpaque(true);
		lblItemCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemCode.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemCode.setBackground(Color.WHITE);
		
		selectItem_comboBox = new JComboBox();
		itemDao = new ItemDaoImpl();
		itemList = itemDao.getAllItemCodes();
		
		JLabel lblItemName = new JLabel("Item Name\n");
		lblItemName.setOpaque(true);
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblItemName.setBackground(Color.WHITE);
		
		show_itemName_label = new JLabel("  ");
		show_itemName_label.setOpaque(true);
		show_itemName_label.setHorizontalAlignment(SwingConstants.CENTER);
		show_itemName_label.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		show_itemName_label.setBackground(Color.WHITE);
		
		JLabel lblCalorie = new JLabel("Total Calorie value");
		lblCalorie.setOpaque(true);
		lblCalorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalorie.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCalorie.setBackground(Color.WHITE);
		
		calorie_txt = new JTextField();
		calorie_txt.setText("preload value here\n");
		calorie_txt.setColumns(10);
		
		JLabel lblIngredient = new JLabel("Ingredient Info");
		lblIngredient.setOpaque(true);
		lblIngredient.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredient.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblIngredient.setBackground(Color.WHITE);
		
		ingredient_textArea = new JTextArea();
		ingredient_textArea.setText("  ");
		
		JLabel lblSugar = new JLabel("Sugar");
		lblSugar.setOpaque(true);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSugar.setBackground(Color.WHITE);
		
		sugar_txt = new JTextField();
		sugar_txt.setColumns(10);
		
		JLabel label = new JLabel("g");
		
		JLabel lblCarbo = new JLabel("Carbohydrate");
		lblCarbo.setOpaque(true);
		lblCarbo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarbo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCarbo.setBackground(Color.WHITE);
		
		carb_txt = new JTextField();
		carb_txt.setColumns(10);
		
		JLabel label_2 = new JLabel("g");
		
		JLabel lblCholesterol = new JLabel("Cholesterol");
		lblCholesterol.setOpaque(true);
		lblCholesterol.setHorizontalAlignment(SwingConstants.CENTER);
		lblCholesterol.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCholesterol.setBackground(Color.WHITE);
		
		cholesterol_txt = new JTextField();
		cholesterol_txt.setColumns(10);
		
		JLabel label_4 = new JLabel("mg");
		
		JLabel lblFat = new JLabel("Total Fat");
		lblFat.setOpaque(true);
		lblFat.setHorizontalAlignment(SwingConstants.CENTER);
		lblFat.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblFat.setBackground(Color.WHITE);
		
		fat_txt = new JTextField();
		fat_txt.setColumns(10);
		
		JLabel label_6 = new JLabel("g");
		
		JLabel lblProtein = new JLabel("Protein");
		lblProtein.setOpaque(true);
		lblProtein.setHorizontalAlignment(SwingConstants.CENTER);
		lblProtein.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblProtein.setBackground(Color.WHITE);
		
		protein_txt = new JTextField();
		protein_txt.setColumns(10);
		
		JLabel label_8 = new JLabel("g");
		
		checkBoxGluten = new JCheckBox("Gluten Free");
		
		checkBoxLowFat = new JCheckBox("Low Fat");
		
		checkBoxLowSugar = new JCheckBox("Low Sugar");
		
		JButton btnSubmitChanges = new JButton("Submit Changes");
		btnSubmitChanges.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		btnSubmitChanges.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitChanges();
			}
		});
		
		for(int i=0; i<itemList.size();i++){
			selectItem_comboBox.addItem(itemList.get(i));
		}
		//on load
		updatedLabels();
		// on change		
		selectItem_comboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						updatedLabels();
					}
				});
		
		lblValidation = new JLabel("  ");
		lblValidation.setForeground(Color.RED);
		lblValidation.setOpaque(true);
		lblValidation.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidation.setFont(new Font("Lao MN", Font.ITALIC, 13));
		lblValidation.setBackground(SystemColor.window);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblValidation, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(178)
					.addComponent(title_label))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblItemCode, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(selectItem_comboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(137)
					.addComponent(lblCholesterol, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(cholesterol_txt, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblItemName, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(show_itemName_label, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(lblFat, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(fat_txt, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_6))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblCalorie, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(calorie_txt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(130)
					.addComponent(lblProtein, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(protein_txt, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_8))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblIngredient, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(ingredient_textArea, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(checkBoxGluten)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(checkBoxLowSugar)))
					.addGap(26)
					.addComponent(checkBoxLowFat, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblSugar, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(sugar_txt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(label)
					.addGap(168)
					.addComponent(btnSubmitChanges))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblCarbo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(14)
					.addComponent(carb_txt, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(label_2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(lblValidation)
					.addGap(5)
					.addComponent(title_label)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblItemCode))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblCholesterol))
								.addComponent(cholesterol_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(label_4))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(selectItem_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblItemName))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(show_itemName_label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblFat))
						.addComponent(fat_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label_6)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCalorie))
						.addComponent(calorie_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblProtein))
						.addComponent(protein_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label_8)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(lblIngredient))
						.addComponent(ingredient_textArea, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(checkBoxGluten)
							.addGap(15)
							.addComponent(checkBoxLowSugar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(checkBoxLowFat)))
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSugar)
						.addComponent(sugar_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(label))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnSubmitChanges)))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(5)
							.addComponent(lblCarbo))
						.addComponent(carb_txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(label_2))))
		);
		setLayout(groupLayout);
	}
	public void updatedLabels(){
		cmboitem = selectItem_comboBox.getSelectedItem();
		if(cmboitem!=null){
			Item item = itemDao.findById(Integer.parseInt(cmboitem.toString()));
			show_itemName_label.setText(item.getItemName());
			calorie_txt.setText(Integer.toString(item.getCalValue()));
			ingredient_textArea.setText(item.getIngredientInfo());
			sugar_txt.setText(item.getSugarVal());
			carb_txt.setText(item.getCarbohydrateValue());
			cholesterol_txt.setText(item.getCholestrolValue());
			fat_txt.setText(item.getTotalFatValue());
			protein_txt.setText(item.getProteinValue());
			if("Y".equals(item.getGlutenFree())){
				checkBoxGluten.setSelected(true);
			}else{
				checkBoxGluten.setSelected(false);
			}
			if("Y".equals(item.getLowSugar())){
				checkBoxLowFat.setSelected(true);
			}else{
				checkBoxLowFat.setSelected(false);
			}
			if("Y".equals(item.getLowFat())){
				checkBoxLowSugar.setSelected(true);
			}else{
				checkBoxLowSugar.setSelected(false);
			}
		}
	}
	
	public void submitChanges(){
		Item itemObj = itemDao.findById(Integer.parseInt(cmboitem.toString()));
		itemObj.setItemName(show_itemName_label.getText());
		itemObj.setCalValue(Integer.parseInt(calorie_txt.getText()));
		itemObj.setIngredientInfo(ingredient_textArea.getText());
		itemObj.setSugarValue(sugar_txt.getText());
		itemObj.setCarbohydrateValue(carb_txt.getText());
		itemObj.setCholestrolValue(cholesterol_txt.getText());
		itemObj.setTotalFatValue(fat_txt.getText());
		itemObj.setProteinValue(protein_txt.getText());
		if(checkBoxGluten.isSelected()){
			itemObj.setGlutenFree("Y");
		}
		if(checkBoxLowFat.isSelected()){
			itemObj.setLowSugar("Y");
		}
		if(checkBoxLowSugar.isSelected()){
			itemObj.setLowFat("Y");
		}
		
		if(itemObj.getIngredientInfo() == null || itemObj.getIngredientInfo().trim().equals("")){
			lblValidation.setText("Please enter the ingredient info before submitting changes");
		}
		else if((textValidate.validateTextField(itemObj.getIngredientInfo())) == false){
			lblValidation.setText("Please enter a correct value for the ingredient info");
		}
		else{
			itemDao.updatedItemNutritionalInfo(itemObj);
			lblValidation.setText("");
			dialog = new ConfirmationDialog();
			dialog.lblMessageLabel.setText("<html>The nutritional information has been <br/>updated for the item code:<html/>" + itemObj.getItemCode());
		}
		
	}
}
