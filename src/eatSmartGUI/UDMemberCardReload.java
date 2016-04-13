package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class UDMemberCardReload extends JPanel implements PanelColleague{
	
	private MainPageGUI mainFrame;
	
	public JLabel cash_auth_message;
	private JButton btnBackToBuying;
	
	public JLabel cardBal_label;
	public double totalAmtDue;
	public List<Integer> itemCodeList;
	
	private double newBal;
	CashDao cashDao = null;
	
	public UDMemberCardReload(MainPageGUI mainFrame){
		
		this.mainFrame = mainFrame;
		
		itemCodeList  = new ArrayList<Integer>();
		
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
		
		cash_auth_message = new JLabel("");
		cash_auth_message.setForeground(Color.RED);
		cash_auth_message.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		cash_auth_message.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblCurrBal = new JLabel("Your Current Balance");
		
		cardBal_label = new JLabel("  ");
		cardBal_label.setBackground(Color.WHITE);
		cardBal_label.setOpaque(true);
		
		//Rearrange this code in another class or a method
		
		JLabel title_label = new JLabel("Reloading your card");
		title_label.setHorizontalAlignment(SwingConstants.CENTER);
		cashDao = new CashDaoImpl();
		btnBackToBuying = new JButton("Confirm Reload");
		btnBackToBuying.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Double.parseDouble(cardBal_label.getText()) >= totalAmtDue){
					newBal = Double.parseDouble(cardBal_label.getText());
					updateCardInfo(((Member)mainFrame.memberType).getCard().getCardId(),newBal);
					cashDao.insertCash(mainFrame.vm.getVMId(), mainFrame.totalVMCashCollection);
					mainFrame.totalVMCashCollection = 0;
					sendPage(mainFrame.createMemberPage4Buying("",totalAmtDue, itemCodeList, newBal));
					//mainFrame.createMemberPage4Buying(mainFrame.getCardReloadPage(),"",totalAmtDue, itemCodeList, newBal);
				}
			}
		});
		btnBackToBuying.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel lblMessage = new JLabel("<html>Please insert the amount in <br/>the cash/coin slot and then confirm<html/>\n");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addComponent(lblCurrBal)
							.addGap(18)
							.addComponent(cardBal_label, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMessage)
								.addComponent(title_label, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(171)
							.addComponent(btnBackToBuying))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(89)
							.addComponent(cash_auth_message, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(title_label)
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrBal)
						.addComponent(cardBal_label))
					.addGap(51)
					.addComponent(lblMessage)
					.addGap(37)
					.addComponent(btnBackToBuying)
					.addGap(27)
					.addComponent(cash_auth_message, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		
	}
	
	public void updateCardInfo(int cardId, double newBalalnce){
		CardDaoImpl dao = new CardDaoImpl();
		dao.updateCardBalance(cardId, newBalalnce);
	}
	
	
	public void setLabel(double CurrentBalance){
		cardBal_label.setText(Double.toString(CurrentBalance));
	}

	public void storeValues(double totalAmtDue, List<Integer> itemList) {
		this.totalAmtDue = totalAmtDue;
		this.itemCodeList = itemList;
	}
	
	
	public void sendPage(JPanel newPage){
		mainFrame.replacePage(this,newPage);
	}


	@Override
	public void setLabels(double amoutDue, double balance, int points,
			List<Integer> itemCodeList) {
		//No Op Method
	}


	@Override
	public void setLabels(double newBal, int pointsRem) {
		//No Op Method
		
	}


	@Override
	public void setLabels(String itemCode) {
		//No Op Method
		
	}


	@Override
	public void setLabels(double totalAmtDue, List<Integer> itemCodeList,
			double newBal) {
		//No Op Method
		
	}
}
