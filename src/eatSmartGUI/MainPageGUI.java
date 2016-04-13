package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;

public class MainPageGUI extends JFrame {

	// public static MainPageGUI mainFrame; // The frame itself

	private JTabbedPane tabbedPane;
	private JPanel vmPanel;
	private JPanel msPanel;
	private JPanel userDisplayPanel;
	private JPanel adminPanel;
	
	public boolean isLoginSuccess;

	private JLabel label1;
	private JTextField userIdField;
	private JLabel label2;
	private JPasswordField userPwdField;
	private JButton btnLogIn;
	private JLabel card_swipe_label;
	private JLabel cash_coin_insert_label;
	private JLabel new_card_dispense_label;
	private JLabel operator_login_label;
	private JTextField operator_key_txt;
	private JTextField card_swipe_txt;
	private JTextField cash_coin_insert_txt;
	private JLabel welcome_label;
	private JLabel selection_label;
	private JLabel card_swipe_button;
	private JLabel cash_coin_button;
	private JLabel operator_button;
	private JPanel productPanel;
	public JLabel product_dispense_label;
	private JLabel item_rack1_code_label;
	private JLabel item_rack2_code_label;
	private JLabel item_rack3_code_label;
	private JLabel item_rack1_image;
	private JLabel item_rack2_image;
	private JLabel item_rack3_image;
	private JSeparator separator_1;
	private JLabel product_display_label;
	private JPanel operationsPanel;

	private GroupLayout gl_vmPanel;
	private GroupLayout gl_adminPanel;
	private GroupLayout gl_msPanel;

	public JPanel trackCurrentPage;
	public VM vm;

	// declaration of all the replacing panels from other appropriate panels
	// (colleague classes)
	private UDMemberPage3_MainMenu mainmenu;
	private UDMemberPage4_Buying buyPage4;
	private UDMemberCardReload reloadPage;
	private UDMemberPage4_Info infoPage4;
	private UDMemberPage4_Suggestions suggestionsPage4;
	private UDMemberPage4_Redeem redeemPage4;
	private UDMemberPage5_Redeem redeemPage5;
	private UDMemberPage4_Popular popularPage4;
	private UDMemberPage4_History historyPage4;
	private UDMemberPage4_Favorites favoritesPage4;

	private UDNonMemberPage2 nmPage2;
	private UDNonMemberPage3_MainMenu nmMainMenu;
	private UDNonMemberPage4_Buying nmBuyPage4;

	// declaration of all the replacing panels on user inputs from mainFrame
	// (pages)
	private UDMemberPage2 page2;
	private UDMemberPage5_Complete buyPage5;

	private UDNonMemberPage3_OK nmOKPage3;
	private UDNonMemberPage4_OK nmOKPage4;
	private UDNonMemberPage5_Buying nmCheckoutPage5;
	private UDNonMemberPage6_Complete nmCompletePage6;

	// Admin main tabbed panes
	MSAdminMonitorVM admin;

	public boolean userMemberClick;
	// create user and non member classes and write the below in member or
	// nonmember click events accordingly
	public MemberType memberType = null;
	public Card card = null;
	public String VMId = "";
	public List<Integer> vMItemList = null;
	public String item_rack1_code = "";
	public String item_rack2_code = "";
	public String item_rack3_code = "";
	public JLabel loginChkLbl;
	public TextFieldValidator textFieldValidate = new TextFieldValidator();

	private JLabel VMID_label;
	public MemberTypeFactory factory = null;
	OperatorDaoImpl dao = null;
	public double totalVMCashCollection = 0;
	CashDao cashDao = null;
	private JLabel item_rack1_name;
	private JLabel item_rack2_name;
	private JLabel item_rack3_name;
	
	private String userId, usrPassword, userName;

	public MainPageGUI(VM vm) {

		factory = new MemberTypeFactory();
		this.vm = vm;
		this.VMId = vm.getVMId();
		isLoginSuccess = false;
		
		setTitle("EatSmart Auto Vendor Application");
		setSize(880, 700);
		setBackground(Color.gray);
		vMItemList = new ArrayList<Integer>();
		JPanel topPanel = new JPanel();
		getContentPane().add(topPanel);
		dao = new OperatorDaoImpl();
		// Create the tab pages
		createTab1();
		createTab2();
		cashDao = new CashDaoImpl();
		// createItemListOfVM(vmId);

		// Create tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("EatSmart Vending machine Simulation", vmPanel);

		productPanel = new JPanel();
		productPanel.setBorder(new LineBorder(Color.black, 2));
		operationsPanel = new JPanel();
		operationsPanel.setBorder(new LineBorder(Color.black, 2));

		VMID_label = new JLabel("  ");
		VMID_label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		VMID_label.setOpaque(true);
		VMID_label.setText(VMId);
		VMID_label.setBackground(Color.ORANGE);
		VMID_label.setForeground(Color.BLACK);
		gl_vmPanel = new GroupLayout(vmPanel);
		gl_vmPanel
				.setHorizontalGroup(gl_vmPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_vmPanel
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(productPanel,
												GroupLayout.PREFERRED_SIZE,
												218, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_vmPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																operationsPanel,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																userDisplayPanel,
																GroupLayout.DEFAULT_SIZE,
																609,
																Short.MAX_VALUE))
										.addGap(17))
						.addGroup(
								gl_vmPanel.createSequentialGroup().addGap(57)
										.addComponent(VMID_label)
										.addContainerGap(721, Short.MAX_VALUE)));
		gl_vmPanel
				.setVerticalGroup(gl_vmPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_vmPanel
										.createSequentialGroup()
										.addGap(8)
										.addComponent(VMID_label)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_vmPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_vmPanel
																		.createSequentialGroup()
																		.addComponent(
																				userDisplayPanel,
																				GroupLayout.PREFERRED_SIZE,
																				346,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				operationsPanel,
																				GroupLayout.DEFAULT_SIZE,
																				164,
																				Short.MAX_VALUE)
																		.addContainerGap(
																				62,
																				Short.MAX_VALUE))
														.addGroup(
																gl_vmPanel
																		.createSequentialGroup()
																		.addComponent(
																				productPanel,
																				GroupLayout.PREFERRED_SIZE,
																				584,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));

		card_swipe_label = new JLabel("CARD SWIPE SLOT");
		card_swipe_label.setHorizontalAlignment(SwingConstants.CENTER);
		card_swipe_label.setBackground(Color.WHITE);
		card_swipe_label.setOpaque(true);

		card_swipe_txt = new JTextField();
		card_swipe_txt.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		card_swipe_txt.setColumns(10);

		card_swipe_button = new JLabel("");
		card_swipe_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((userMemberClick == true) && (trackCurrentPage == page2)
						&& (card_swipe_txt.getText().trim().length() > 0)) {
					CardDaoImpl dao = new CardDaoImpl();
					String cardNum = card_swipe_txt.getText();
					if (textFieldValidate.validateNumberField(cardNum)) {
						int cardNo = Integer.parseInt(cardNum);
						card = dao.findById(cardNo);
						if (card != null && (card.getCardId() == cardNo)) {
							// member.setCardId(cardNo);
							// if(memberType instanceof Member){
							memberType.getCard().setCardId(cardNo);
							// }
							System.out.println("Card Authenticated");
							replacePage(getTrackedCurrentPage(),
									createMainMenuPage());
							card_swipe_txt.setText("");
						} else {
							System.out.println("Card Not Authenticated");
							page2.swipe_auth_message
									.setText("<html>Card Authentication Failed, Kindly swipe again correctly<html/>");
							card_swipe_txt.setText("");
						}
					} else {
						page2.swipe_auth_message
								.setText("<html>Card Authentication Failed, Kindly swipe again correctly<html/>");
						System.out.println("Cannot process the request");
					}
				}
			}
		});
		card_swipe_button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		card_swipe_button
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/images/nextButton.jpg"));

		cash_coin_insert_label = new JLabel("INSERT CASH/COIN");
		cash_coin_insert_label.setHorizontalAlignment(SwingConstants.CENTER);
		cash_coin_insert_label.setOpaque(true);
		cash_coin_insert_label.setBackground(Color.WHITE);

		cash_coin_insert_txt = new JTextField();
		cash_coin_insert_txt.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		cash_coin_insert_txt.setColumns(10);

		cash_coin_button = new JLabel("");
		cash_coin_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userMemberClick +
				// trackCurrentPage.getClass().toGenericString());
				// code for non-member buying item @Checkout Page 5
				if ((userMemberClick == false)
						&& (trackCurrentPage == nmCheckoutPage5)) {
					double price = 0;
					double amtLeft = 0;
					double enteredAmt = 0;
					if (cash_coin_insert_txt.getText().trim().length() > 0) {
						String cashCoinAmt = cash_coin_insert_txt.getText();
						if (textFieldValidate.validateDecimalField(cashCoinAmt)) {
							enteredAmt = Double
									.parseDouble(cash_coin_insert_txt.getText());
							totalVMCashCollection += enteredAmt;

							// price = nmBuyPage4.itemPrice;
							price = Double
									.parseDouble(nmCheckoutPage5.total_amount_due_label
											.getText());
							System.out.println("price--" + price);

							System.out.println("enteredAmt--" + enteredAmt);

							if (enteredAmt >= 0.5 && enteredAmt <= price + 1) {
								amtLeft = price - enteredAmt;
								System.out.println("amtLeft--" + amtLeft);
								System.out.println("check -->"
										+ (enteredAmt <= amtLeft));
								nmCheckoutPage5.total_amount_due_label
										.setText(Double.toString(amtLeft));
								if (amtLeft == 0.0 || amtLeft < 0.0) {
									double change = Math.abs(amtLeft);
									cashDao.insertCash(vm.getVMId(),
											totalVMCashCollection);
									totalVMCashCollection = 0;
									replacePage(
											getTrackedCurrentPage(),
											createNonMemberPage6_Complete(
													change,
													nmCheckoutPage5
															.getProdDispenseMessage()));

								}
							} else {
								nmCheckoutPage5.coin_entryValidation_label
										.setText("<html>Entered amount should not be less than 0.5$ and not more than <html/> "
												+ (price + 1));
							}
						} else {
							System.out.println("Cannot process request");
						}

					} else
						nmCheckoutPage5.coin_entryValidation_label
								.setText("Please insert amount to proceed");
				}

				// code for non-member buying card using coin/cash
				else if ((userMemberClick == false)
						&& (trackCurrentPage == nmOKPage3)) {
					double price = 0;
					double amtLeft = 0;
					double enteredAmt = 0;
					String cashCoinAmt = null;
					double cardBalalceAmt = 0;
					if (cash_coin_insert_txt.getText().trim().length() > 0)
						cashCoinAmt = cash_coin_insert_txt.getText();

					else
						nmOKPage3.cash_auth_message
								.setText("Please insert amount to proceed");
					if (textFieldValidate.validateDecimalField(cashCoinAmt)) {
						enteredAmt = Double.parseDouble(cashCoinAmt);
						price = Double.parseDouble(nmOKPage3.amount_due
								.getText());
						if (nmOKPage3.isDen10)
							cardBalalceAmt = 10;
						else if (nmOKPage3.isDen20)
							cardBalalceAmt = 20;
						else if (nmOKPage3.isDen30)
							cardBalalceAmt = 30;

						if (enteredAmt >= 0.5 && enteredAmt <= price) {
							amtLeft = price - enteredAmt;
							totalVMCashCollection += enteredAmt;
							System.out.println("amtLeft--" + amtLeft);
							System.out.println("check -->"
									+ (enteredAmt <= amtLeft));
							nmOKPage3.amount_due.setText(Double
									.toString(amtLeft));
							if (amtLeft == 0.0 || amtLeft < 0.0) {
								CardDaoImpl dao = new CardDaoImpl();
								/*
								 * CardDaoImpl dao = new CardDaoImpl();
								 * dao.insertCard(price, 0, member);
								 * System.out.println(member.getCardId());
								 * createNonMemberPage4_OK(member.getCardId());
								 * new_card_dispense_label
								 * .setText("Card No: "+member
								 * .getCardId()+" dispensed.");
								 */
								dao.insertCard(cardBalalceAmt, 0, memberType);
								System.out.println(memberType.getCard()
										.getCardId());
								cashDao.insertCash(vm.getVMId(),
										totalVMCashCollection);
								totalVMCashCollection = 0;
								replacePage(getTrackedCurrentPage(),
										createNonMemberPage4_OK(memberType
												.getCard().getCardId()));
								new_card_dispense_label.setText("Card No: "
										+ memberType.getCard().getCardId()
										+ " dispensed.");
							}
						} else {
							nmOKPage3.cash_auth_message
									.setText("<html>Entered amount should not be less than 0.5$ or more than<html/>"
											+ price);
							System.out
									.println("Entered amount should not be less than 0.5$ and not more than "
											+ price);
						}
					} else {
						nmOKPage3.cash_auth_message
								.setText("<html>Please enter a valid amount<html/>");
						System.out.println("cannot process request");
					}

				}

				// When a member reloads his card balance

				else if ((userMemberClick) && (trackCurrentPage == reloadPage)) {
					// System.out.println("userMemberClick" +
					// trackCurrentPage.toString());
					// System.out.println("Entry working at reload page");
					double updatedBalance = 0;
					double prevBalance = 0;
					double enteredAmt = 0;
					String cashCoinAmt = null;
					if (cash_coin_insert_txt.getText().trim().length() > 0) {
						cashCoinAmt = cash_coin_insert_txt.getText();
					} else
						reloadPage.cash_auth_message
								.setText("Please insert amount to proceed");
					if (textFieldValidate.validateDecimalField(cashCoinAmt)) {
						enteredAmt = Double.parseDouble(cashCoinAmt);
						totalVMCashCollection += enteredAmt;
						prevBalance = Double
								.parseDouble(reloadPage.cardBal_label.getText());
						updatedBalance = prevBalance + enteredAmt;
						reloadPage.cardBal_label.setText(Double
								.toString(updatedBalance));
					} else {
						reloadPage.cash_auth_message
								.setText("Please insert amount to proceed");
					}
				}

				cash_coin_insert_txt.setText("");

			}
		});
		cash_coin_button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		cash_coin_button
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/nextButton.jpg"));

		new_card_dispense_label = new JLabel("NEW CARD DISPENSE SLOT");
		new_card_dispense_label.setOpaque(true);
		new_card_dispense_label.setHorizontalAlignment(SwingConstants.CENTER);
		new_card_dispense_label.setBackground(Color.WHITE);
		new_card_dispense_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Application.mainFrame.getTrackedCurrentPage() == nmOKPage4) {
					new_card_dispense_label.setText("NEW CARD DISPENSE SLOT");
					goBackToMainPage(nmOKPage4);
				}
			}
		});

		operator_login_label = new JLabel("OPERATOR LOGIN");
		operator_login_label
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/operator.jpg"));
		operator_login_label.setOpaque(true);
		operator_login_label.setHorizontalAlignment(SwingConstants.LEFT);
		operator_login_label.setBackground(Color.WHITE);

		operator_login_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				operator_login_label.setText("OPERATOR LOGIN");
				operator_key_txt.setText("");
			}
		});
		operator_key_txt = new JTextField();
		operator_key_txt.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		operator_key_txt.setColumns(10);

		operator_button = new JLabel("");
		operator_button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		operator_button
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/nextButton.jpg"));
		operator_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (trackCurrentPage == userDisplayPanel) {
					if (operator_key_txt.getText().trim().length() > 0) {
						String opKey = operator_key_txt.getText();
						if (textFieldValidate.validateNumberField(opKey)) {
							int opId = Integer.parseInt(operator_key_txt
									.getText());
							if (dao.authenticateOperator(opId)) {
								double cashCollectionToday = cashDao
										.getCashDetails(vm.getVMId());
								operator_login_label.setText("ManageCash:"
										+ cashCollectionToday);
								System.out.println("Amount Retrieved: "
										+ cashCollectionToday);
							} else {
								System.out.println("Invalid Key.");
								operator_key_txt.setText("");
							}
						} else
							operator_key_txt.setText("");
					}
				} else
					operator_key_txt.setText("");
			}
		});
		GroupLayout gl_operationsPanel = new GroupLayout(operationsPanel);
		gl_operationsPanel
				.setHorizontalGroup(gl_operationsPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_operationsPanel
										.createSequentialGroup()
										.addGap(42)
										.addGroup(
												gl_operationsPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																new_card_dispense_label,
																GroupLayout.PREFERRED_SIZE,
																219,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_operationsPanel
																		.createSequentialGroup()
																		.addComponent(
																				card_swipe_label,
																				GroupLayout.PREFERRED_SIZE,
																				128,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				card_swipe_txt,
																				GroupLayout.PREFERRED_SIZE,
																				93,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(card_swipe_button)
										.addPreferredGap(
												ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_operationsPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_operationsPanel
																		.createSequentialGroup()
																		.addComponent(
																				operator_login_label,
																				GroupLayout.PREFERRED_SIZE,
																				157,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				operator_key_txt,
																				GroupLayout.PREFERRED_SIZE,
																				85,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				operator_button))
														.addGroup(
																gl_operationsPanel
																		.createSequentialGroup()
																		.addGap(12)
																		.addComponent(
																				cash_coin_insert_label,
																				GroupLayout.PREFERRED_SIZE,
																				134,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				cash_coin_insert_txt,
																				GroupLayout.PREFERRED_SIZE,
																				41,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				cash_coin_button)))
										.addGap(14)));
		gl_operationsPanel
				.setVerticalGroup(gl_operationsPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_operationsPanel
										.createSequentialGroup()
										.addGap(27)
										.addGroup(
												gl_operationsPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_operationsPanel
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				cash_coin_insert_label,
																				GroupLayout.PREFERRED_SIZE,
																				21,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				cash_coin_insert_txt,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																cash_coin_button,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_operationsPanel
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addGroup(
																				Alignment.TRAILING,
																				gl_operationsPanel
																						.createSequentialGroup()
																						.addComponent(
																								card_swipe_button,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED))
																		.addGroup(
																				Alignment.TRAILING,
																				gl_operationsPanel
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								card_swipe_label,
																								GroupLayout.PREFERRED_SIZE,
																								24,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								card_swipe_txt))))
										.addGap(18)
										.addGroup(
												gl_operationsPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_operationsPanel
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				operator_login_label,
																				GroupLayout.PREFERRED_SIZE,
																				51,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				operator_key_txt,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				new_card_dispense_label,
																				GroupLayout.PREFERRED_SIZE,
																				49,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_operationsPanel
																		.createSequentialGroup()
																		.addComponent(
																				operator_button,
																				GroupLayout.PREFERRED_SIZE,
																				31,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(11)))
										.addGap(19)));
		operationsPanel.setLayout(gl_operationsPanel);

		item_rack1_code_label = new JLabel("code");
		item_rack1_code_label.setBackground(new Color(255, 240, 245));
		item_rack1_code_label.setOpaque(true);
		// item_rack1_code_label.setText(item_rack1_code);

		item_rack2_code_label = new JLabel("code");
		item_rack2_code_label.setBackground(new Color(255, 240, 245));
		item_rack2_code_label.setOpaque(true);
		// item_rack2_code_label.setText(item_rack2_code);

		item_rack3_code_label = new JLabel("code");
		item_rack3_code_label.setBackground(new Color(255, 240, 245));
		item_rack3_code_label.setOpaque(true);
		// item_rack3_code_label.setText(item_rack3_code);

		separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);

		product_dispense_label = new JLabel(
				"<html>PRODUCT AND CHANGE <BR/> DISPENSE SLOT </html>");
		product_dispense_label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// two conditions below- also add is prodDispenseSlot has
				// itemlist printed
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
		});
		product_dispense_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// in addition to isPage5 Set, it must also check if Product
				// dispense slot.gettext not equal to "Product Dispense Slot"
				// same with mouse moved event - Hand cursor should appear only
				// if the two conditions are satisfied
				// RIGHT NOW IT GIVES NULL POINTER EXCEPTION IF BELOW CONDITION
				// FAILS - FIG OUT WHY
				if (Application.mainFrame.getTrackedCurrentPage() == buyPage5) {
					goBackToMainPage(buyPage5);
					product_dispense_label.setText("PRODUCT DISPENSE SLOT");
				}
				if (Application.mainFrame.getTrackedCurrentPage() == nmCompletePage6) {
					goBackToMainPage(nmCompletePage6);
					product_dispense_label.setText("PRODUCT DISPENSE SLOT");
				}

				// SImilarly if page5 of nonmember Buying is set call goback
				// tomainmenu asn pass nmCheckoutPage5
			}
		});
		product_dispense_label.setOpaque(true);
		product_dispense_label.setHorizontalAlignment(SwingConstants.CENTER);
		product_dispense_label.setForeground(Color.BLACK);
		product_dispense_label.setBackground(SystemColor.textHighlight);

		product_display_label = new JLabel("Product Display");
		product_display_label.setHorizontalAlignment(SwingConstants.CENTER);

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);

		item_rack1_image = new JLabel("   ");
		item_rack1_image
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/items.png"));

		item_rack2_image = new JLabel("");
		item_rack2_image
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/items.png"));

		item_rack3_image = new JLabel("");
		item_rack3_image
				.setIcon(new ImageIcon(
						"/Users/kavyagautam/Design Patterns/EatSmartAutoVendor/src/EatSmartGUI/items.png"));

		item_rack1_name = new JLabel("name");
		item_rack1_name.setBackground(new Color(255, 240, 245));
		item_rack1_name.setOpaque(true);

		item_rack2_name = new JLabel("name");
		item_rack2_name.setBackground(new Color(255, 240, 245));
		item_rack2_name.setOpaque(true);

		item_rack3_name = new JLabel("name");
		item_rack3_name.setBackground(new Color(255, 240, 245));
		item_rack3_name.setOpaque(true);

		loadItemDisplaySection();

		GroupLayout gl_productPanel = new GroupLayout(productPanel);
		gl_productPanel
				.setHorizontalGroup(gl_productPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_productPanel
										.createSequentialGroup()
										.addGroup(
												gl_productPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_productPanel
																		.createSequentialGroup()
																		.addGap(14)
																		.addGroup(
																				gl_productPanel
																						.createParallelGroup(
																								Alignment.LEADING,
																								false)
																						.addComponent(
																								product_dispense_label,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								separator_3,
																								GroupLayout.DEFAULT_SIZE,
																								179,
																								Short.MAX_VALUE)))
														.addGroup(
																gl_productPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_productPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								gl_productPanel
																										.createSequentialGroup()
																										.addGap(22)
																										.addComponent(
																												item_rack3_image)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addGroup(
																												gl_productPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_productPanel
																																		.createSequentialGroup()
																																		.addComponent(
																																				item_rack3_code_label)
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED,
																																				53,
																																				Short.MAX_VALUE))
																														.addComponent(
																																item_rack3_name,
																																GroupLayout.DEFAULT_SIZE,
																																83,
																																Short.MAX_VALUE)))
																						.addGroup(
																								gl_productPanel
																										.createSequentialGroup()
																										.addGap(21)
																										.addGroup(
																												gl_productPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addComponent(
																																item_rack1_image,
																																GroupLayout.PREFERRED_SIZE,
																																72,
																																GroupLayout.PREFERRED_SIZE)
																														.addComponent(
																																item_rack2_image))
																										.addGroup(
																												gl_productPanel
																														.createParallelGroup(
																																Alignment.LEADING)
																														.addGroup(
																																gl_productPanel
																																		.createSequentialGroup()
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addGroup(
																																				gl_productPanel
																																						.createParallelGroup(
																																								Alignment.LEADING)
																																						.addComponent(
																																								item_rack2_name,
																																								GroupLayout.DEFAULT_SIZE,
																																								GroupLayout.DEFAULT_SIZE,
																																								Short.MAX_VALUE)
																																						.addComponent(
																																								item_rack1_name,
																																								GroupLayout.DEFAULT_SIZE,
																																								89,
																																								Short.MAX_VALUE)
																																						.addComponent(
																																								item_rack1_code_label)))
																														.addGroup(
																																gl_productPanel
																																		.createSequentialGroup()
																																		.addPreferredGap(
																																				ComponentPlacement.RELATED)
																																		.addComponent(
																																				item_rack2_code_label)))))
																		.addGap(30))
														.addGroup(
																gl_productPanel
																		.createSequentialGroup()
																		.addGap(49)
																		.addComponent(
																				product_display_label)))
										.addContainerGap()));
		gl_productPanel
				.setVerticalGroup(gl_productPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_productPanel
										.createSequentialGroup()
										.addGap(23)
										.addComponent(product_display_label)
										.addGroup(
												gl_productPanel
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_productPanel
																		.createSequentialGroup()
																		.addGap(18)
																		.addComponent(
																				item_rack1_image)
																		.addGap(28)
																		.addComponent(
																				item_rack2_image,
																				GroupLayout.PREFERRED_SIZE,
																				86,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(40)
																		.addComponent(
																				item_rack3_image,
																				GroupLayout.PREFERRED_SIZE,
																				86,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(56))
														.addGroup(
																gl_productPanel
																		.createSequentialGroup()
																		.addGap(28)
																		.addComponent(
																				item_rack1_code_label)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				item_rack1_name,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(57)
																		.addComponent(
																				item_rack2_code_label)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				item_rack2_name,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				item_rack3_code_label)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				item_rack3_name,
																				GroupLayout.PREFERRED_SIZE,
																				39,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(63)))
										.addComponent(separator_3,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(product_dispense_label,
												GroupLayout.PREFERRED_SIZE, 76,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(47, Short.MAX_VALUE)));
		productPanel.setLayout(gl_productPanel);

		welcome_label = new JLabel("Welcome to EatSmart Vending Machine");
		welcome_label.setBackground(SystemColor.textHighlight);
		welcome_label.setOpaque(true);
		welcome_label.setHorizontalAlignment(SwingConstants.CENTER);

		selection_label = new JLabel("Select one of the following:");
		selection_label.setOpaque(true);
		selection_label.setHorizontalAlignment(SwingConstants.CENTER);
		selection_label.setBackground(SystemColor.activeCaptionBorder);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.menuText);

		JLabel lblEatsmartMember = new JLabel("EATSMART MEMBER");
		lblEatsmartMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userMemberClick = true;
				memberType = factory.createMemberType("Member");
				replacePage(getTrackedCurrentPage(), createMemberPage2());
			}
		});
		lblEatsmartMember.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblEatsmartMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblEatsmartMember.setBackground(Color.WHITE);
		lblEatsmartMember.setOpaque(true);

		JLabel lblNonmember = new JLabel("NON-MEMBER");
		lblNonmember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userMemberClick = false;
				memberType = factory.createMemberType("Non-Member");
				replacePage(getTrackedCurrentPage(), createNonMemberPage2());
			}
		});
		lblNonmember.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblNonmember.setBackground(Color.WHITE);
		lblNonmember.setOpaque(true);

		lblNonmember.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_userDisplayPanel = new GroupLayout(userDisplayPanel);
		gl_userDisplayPanel
				.setHorizontalGroup(gl_userDisplayPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_userDisplayPanel
										.createSequentialGroup()
										.addGap(15)
										.addGroup(
												gl_userDisplayPanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																separator_2,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																563,
																Short.MAX_VALUE)
														.addComponent(
																selection_label,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																563,
																Short.MAX_VALUE))
										.addGap(17))
						.addGroup(
								gl_userDisplayPanel
										.createSequentialGroup()
										.addContainerGap(210, Short.MAX_VALUE)
										.addComponent(welcome_label,
												GroupLayout.PREFERRED_SIZE,
												262, GroupLayout.PREFERRED_SIZE)
										.addGap(123))
						.addGroup(
								gl_userDisplayPanel
										.createSequentialGroup()
										.addContainerGap(202, Short.MAX_VALUE)
										.addGroup(
												gl_userDisplayPanel
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addComponent(
																lblNonmember,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblEatsmartMember,
																Alignment.LEADING,
																GroupLayout.PREFERRED_SIZE,
																193,
																GroupLayout.PREFERRED_SIZE))
										.addGap(200)));
		gl_userDisplayPanel.setVerticalGroup(gl_userDisplayPanel
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_userDisplayPanel
								.createSequentialGroup()
								.addGap(22)
								.addComponent(welcome_label,
										GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addGap(34)
								.addComponent(selection_label,
										GroupLayout.PREFERRED_SIZE, 26,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(separator_2,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblEatsmartMember,
										GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE).addGap(26)
								.addComponent(lblNonmember)
								.addContainerGap(119, Short.MAX_VALUE)));
		userDisplayPanel.setLayout(gl_userDisplayPanel);
		vmPanel.setLayout(gl_vmPanel);
		tabbedPane.addTab("EatSmart Remote Administration", msPanel);

		adminPanel = new JPanel();
		adminPanel.setBorder(new LineBorder(Color.black, 5));

		label1 = new JLabel("Username:");
		label1.setBounds(10, 15, 150, 20);

		userIdField = new JTextField();
		userIdField.setBounds(10, 35, 150, 20);

		label2 = new JLabel("Password:");
		label2.setBounds(10, 60, 150, 20);

		userPwdField = new JPasswordField();
		userPwdField.setBounds(10, 80, 150, 20);

		btnLogIn = new JButton("Log In");
		btnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Login login = new Login(new BasicAuthorizationStrategy(
						Application.mainFrame), Application.mainFrame);
				userId = userIdField.getText();
				usrPassword = String.valueOf(userPwdField.getPassword());
				login.authenticateUser(userId, usrPassword);
			}
		});
		btnLogIn.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		loginChkLbl = new JLabel("");
		gl_adminPanel = new GroupLayout(adminPanel);
		gl_adminPanel
				.setHorizontalGroup(gl_adminPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								Alignment.TRAILING,
								gl_adminPanel
										.createSequentialGroup()
										.addContainerGap(226, Short.MAX_VALUE)
										.addGroup(
												gl_adminPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addComponent(
																				label1,
																				GroupLayout.PREFERRED_SIZE,
																				114,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				userIdField,
																				GroupLayout.PREFERRED_SIZE,
																				205,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addComponent(
																				label2,
																				GroupLayout.PREFERRED_SIZE,
																				114,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				userPwdField,
																				GroupLayout.PREFERRED_SIZE,
																				205,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addGap(102)
																		.addComponent(
																				btnLogIn))
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				loginChkLbl,
																				GroupLayout.PREFERRED_SIZE,
																				431,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(178)));
		gl_adminPanel
				.setVerticalGroup(gl_adminPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_adminPanel
										.createSequentialGroup()
										.addGap(107)
										.addGroup(
												gl_adminPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																label1,
																GroupLayout.PREFERRED_SIZE,
																31,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addGap(1)
																		.addComponent(
																				userIdField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(29)
										.addGroup(
												gl_adminPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																label2,
																GroupLayout.PREFERRED_SIZE,
																34,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_adminPanel
																		.createSequentialGroup()
																		.addGap(3)
																		.addComponent(
																				userPwdField,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(69)
										.addComponent(btnLogIn)
										.addGap(38)
										.addComponent(loginChkLbl,
												GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(200, Short.MAX_VALUE)));
		adminPanel.setLayout(gl_adminPanel);
		gl_msPanel = new GroupLayout(msPanel);
		gl_msPanel.setHorizontalGroup(gl_msPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_msPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(adminPanel, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(7, Short.MAX_VALUE)));
		gl_msPanel.setVerticalGroup(gl_msPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_msPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(adminPanel, GroupLayout.DEFAULT_SIZE,
								570, Short.MAX_VALUE).addContainerGap()));
		msPanel.setLayout(gl_msPanel);
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE));
		topPanel.setLayout(gl_topPanel);
		/*
		 * adminTabbedPane.addChangeListener(new ChangeListener(){
		 * 
		 * @Override public void stateChanged(ChangeEvent e){ if(e.getSource()
		 * instanceof JTabbedPane){ JTabbedPane pane = (JTabbedPane)
		 * e.getSource(); int index = pane.getSelectedIndex();
		 */
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					JTabbedPane pane = (JTabbedPane) e.getSource();
					int index = pane.getSelectedIndex();
					System.out.println("The new tab index is:" + index);
					if (index == 0) {
						loadItemDisplaySection();
					}
					/*if(index == 1){
						if(isLoginSuccess){
							//Login login = new Login(new BasicAuthorizationStrategy(
							//Application.mainFrame), Application.mainFrame);
							//login.authenticateUser(userId, usrPassword);
							gl_msPanel.replace(admin.getCurrentAdminTab(), new MSAdminMonitorVM(userId,userName ));
						}
						else gl_msPanel.replace(adminPanel, adminPanel);
					}*/
				}
			}

		});
	}

	// First page for Tab1 (Vending Machine default page)
	public void createTab1() {
		vmPanel = new JPanel();
		userDisplayPanel = new JPanel();
		userDisplayPanel.setBorder(new LineBorder(Color.black, 2));
		vmPanel.add(userDisplayPanel);
		trackCurrentPage = userDisplayPanel;
	}

	// First page for Tab2 (Admin Login page)
	public void createTab2() {
		msPanel = new JPanel();
	}

	/**
	 * These set of methods below are reusable for both Member and Non member
	 * 
	 */
	// Special Method for Cancel Transaction -Leading to Member-NonMember page
	public void goBackToMainPage(JPanel currentPage) {
		gl_vmPanel.replace(currentPage, userDisplayPanel);
		trackCurrentPage = userDisplayPanel;
	}

	// Common method to create Main Menu based on member or non member
	// instantiation
	public JPanel createMainMenuPage() {
		if (userMemberClick) {
			mainmenu = new UDMemberPage3_MainMenu(Application.mainFrame);
			trackCurrentPage = mainmenu;
			return trackCurrentPage;
		} else {
			nmMainMenu = new UDNonMemberPage3_MainMenu(Application.mainFrame);
			trackCurrentPage = nmMainMenu;
			return trackCurrentPage;
		}
	}

	// Page 4 - Ingredient info show page
	public JPanel createMemberPage4Info() {
		infoPage4 = new UDMemberPage4_Info(Application.mainFrame);
		trackCurrentPage = infoPage4;
		return trackCurrentPage;
	}

	// Page 4 - Ask Suggestions page
	public JPanel createMemberPage4_Suggestions() {
		suggestionsPage4 = new UDMemberPage4_Suggestions(Application.mainFrame);
		trackCurrentPage = suggestionsPage4;
		return trackCurrentPage;
	}

	// Page 4 - Most Popular items list
	public JPanel createMemberPage4_Popular() {
		popularPage4 = new UDMemberPage4_Popular(Application.mainFrame);
		trackCurrentPage = popularPage4;
		return trackCurrentPage;
	}

	/**
	 * Set of all exclusive Member creation methods
	 */
	// // Page 2 - UserInterfaceDisplay page after clicking on "EatSmart Member"
	// label
	public JPanel createMemberPage2() {
		page2 = new UDMemberPage2(Application.mainFrame);
		trackCurrentPage = page2;
		return trackCurrentPage;

	}

	// page 4 - item selection page from Buy Item
	public JPanel createMemberPage4Buying(String itemCode, double totalAmtDue,
			List<Integer> itemCodeList, double newBalance) {
		buyPage4 = new UDMemberPage4_Buying(Application.mainFrame);
		buyPage4.setLabels(itemCode);
		buyPage4.setLabels(totalAmtDue, itemCodeList, newBalance);
		trackCurrentPage = buyPage4;
		return trackCurrentPage;
	}

	// Reload button click during the buying session if card balace is < 0
	public JPanel createMemberCardReload(double currentBalance,
			double totalAmtDue, List<Integer> itemList) {
		reloadPage = new UDMemberCardReload(Application.mainFrame);
		reloadPage.setLabel(currentBalance);
		reloadPage.storeValues(totalAmtDue, itemList);
		trackCurrentPage = reloadPage;
		return trackCurrentPage;
	}

	// page 5 - After debit from card - message display
	public JPanel createMemberPage5Complete(double amtToBeDebited,
			double cardBalance, int newPoints, List<Integer> itemCodeIntList) {
		buyPage5 = new UDMemberPage5_Complete(Application.mainFrame);
		buyPage5.setLabels(amtToBeDebited, cardBalance, newPoints,
				itemCodeIntList);
		trackCurrentPage = buyPage5;
		return trackCurrentPage;
	}

	// Page 4 - Redeem points page
	public JPanel createMemberPage4_Redeem() {
		redeemPage4 = new UDMemberPage4_Redeem(Application.mainFrame);
		trackCurrentPage = redeemPage4;
		return trackCurrentPage;
	}

	// Page 5 - After redeeming points
	public JPanel createMemberPage5_Redeem(double bal, int points) {
		redeemPage5 = new UDMemberPage5_Redeem(Application.mainFrame);
		redeemPage5.setLabels(bal, points);
		trackCurrentPage = redeemPage5;
		return trackCurrentPage;
	}

	// Page 4 - Member History
	public JPanel createMemberPage4_History() {
		historyPage4 = new UDMemberPage4_History(Application.mainFrame);
		trackCurrentPage = historyPage4;
		return trackCurrentPage;
	}

	// Page4 - My Favorites page
	public JPanel createMemberPage4_Favorites() {
		favoritesPage4 = new UDMemberPage4_Favorites(Application.mainFrame);
		trackCurrentPage = favoritesPage4;
		return trackCurrentPage;
	}

	/**
	 * Start of all Non Member creation methods
	 * 
	 * */
	// Page 2 - Page after clicking on Non Member
	public JPanel createNonMemberPage2() {
		nmPage2 = new UDNonMemberPage2(Application.mainFrame);
		trackCurrentPage = nmPage2;
		return trackCurrentPage;
	}

	// Page 3 - Page after a Non member clicks on OK to buy a card
	public JPanel createNonMemberPage3_OK() {
		nmOKPage3 = new UDNonMemberPage3_OK();
		trackCurrentPage = nmOKPage3;
		return trackCurrentPage;
	}

	// Page 3 - Page after a Non member clicks on OK to buy a card
	public JPanel createNonMemberPage4_OK(int cardId) {
		nmOKPage4 = new UDNonMemberPage4_OK();
		nmOKPage4.setLabels(cardId);
		trackCurrentPage = nmOKPage4;
		return trackCurrentPage;
	}

	// Page 4 - Page after a Non member selects Buy Item from his main menu
	public JPanel createNonMemberPage4_Buying(String itemCode) {
		nmBuyPage4 = new UDNonMemberPage4_Buying(Application.mainFrame);
		nmBuyPage4.setLabels(itemCode);
		trackCurrentPage = nmBuyPage4;
		return trackCurrentPage;
	}

	// Page 5 - of buying page where user is allowed to enter thru cash/coin txt
	// field
	public JPanel createNonMemberPage5_checkOut(double amtDue,
			List<Integer> itemCodeIntList) {
		nmCheckoutPage5 = new UDNonMemberPage5_Buying(Application.mainFrame);
		nmCheckoutPage5.setLabels(amtDue, itemCodeIntList);
		trackCurrentPage = nmCheckoutPage5;
		return trackCurrentPage;
	}

	// Page 6 - After the buying transaction is complete
	public JPanel createNonMemberPage6_Complete(double change, String message) {
		nmCompletePage6 = new UDNonMemberPage6_Complete(Application.mainFrame);
		nmCompletePage6.setLabels(change, message);
		trackCurrentPage = nmCompletePage6;
		return trackCurrentPage;
	}

	/**
	 * Some Getter methods to return pages as needed
	 * 
	 * @return JPanel page
	 */
	// method to return tab 1
	public JPanel getVMPanel() {
		return vmPanel;
	}

	// method to return tab 2
	public JPanel getMSPanel() {
		return msPanel;
	}

	public JPanel getPage4Buying() {
		return buyPage4;
	}

	public JPanel getPage4Info() {
		return infoPage4;
	}

	public JPanel getPage4Suggestions() {
		return suggestionsPage4;
	}

	public JPanel getPage4Redeem() {
		return redeemPage4;
	}

	public JPanel getPage5Redeem() {
		return redeemPage5;
	}

	public JPanel getPage4Popular() {
		return popularPage4;
	}

	public JPanel getPage4History() {
		return historyPage4;
	}

	public JPanel getPage4Favorites() {
		return favoritesPage4;
	}

	public JPanel getNMPage4Buying() {
		return nmBuyPage4;
	}

	public JPanel getNMPage2() {
		return nmPage2;
	}

	public JPanel getMemberMainMenu() {
		return mainmenu;
	}

	public JPanel getNonMemberMainMenu() {
		return nmMainMenu;
	}

	public JPanel getTrackedCurrentPage() {
		return trackCurrentPage;
	}

	public JPanel getCardReloadPage() {
		return reloadPage;
	}

	public VM getCUrrentVM() {
		return vm;
	}

	/**
	 * These set of methods are for creating pages in Remote Admin system
	 */

	// public void createMSAdminMainPage(){
	// MSAdminMainPage adminMainPage = new MSAdminMainPage();
	// gl_msPanel.replace(adminPanel,adminMainPage);
	// }

	public void createMSAdminMainPage(String userID, String userName) {
		admin = new MSAdminMonitorVM(userID, userName);
		this.userId = userID;
		this.userName = userName;
		gl_msPanel.replace(adminPanel, admin);
		isLoginSuccess = true;
	}

	public void goBackToAdminLoginPage() {
		gl_msPanel.replace(admin.getLoggedMonitorPanel(), adminPanel);
		userIdField.setText("");
		userPwdField.setText("");
	}

	public void replacePage(JPanel oldPage, JPanel newPage) {
		gl_vmPanel.replace(oldPage, newPage);
	}

	public void loadItemDisplaySection() {
		vMItemList = vm.getFoodItemSection().getActiveItemIdList(vm.getVMId());
		ItemDao dao = new ItemDaoImpl();
		Item itemObject;

		if (vMItemList.size() > 0) {
			item_rack1_code = Integer.toString(vMItemList.get(0));
			item_rack2_code = Integer.toString(vMItemList.get(1));
			item_rack3_code = Integer.toString(vMItemList.get(2));

			item_rack1_code_label.setText(item_rack1_code);
			item_rack2_code_label.setText(item_rack2_code);
			item_rack3_code_label.setText(item_rack3_code);

			itemObject = dao.findById(vMItemList.get(0));
			item_rack1_name.setText(itemObject.getItemName());

			itemObject = dao.findById(vMItemList.get(1));
			item_rack2_name.setText(itemObject.getItemName());

			itemObject = dao.findById(vMItemList.get(2));
			item_rack3_name.setText(itemObject.getItemName());

		}
	}

}
