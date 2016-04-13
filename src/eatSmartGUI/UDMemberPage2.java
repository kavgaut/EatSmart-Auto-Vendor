package eatSmartGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class UDMemberPage2 extends JPanel {
	private MainPageGUI mainFrame;
	public JLabel swipe_auth_message;
	
	public UDMemberPage2(MainPageGUI mainFrame) {
		this.mainFrame = mainFrame;
		JLabel lblHelloMember = new JLabel("Hello EatSmart User");
		lblHelloMember.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		lblHelloMember.setBackground(Color.WHITE);
		lblHelloMember.setOpaque(true);
		lblHelloMember.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblSwipeMessage = new JLabel("<html>Kindly swipe in the provided Card slot using <br/>your EatSmart Card.....</html>");
		lblSwipeMessage.setBackground(new Color(143, 188, 143));
		lblSwipeMessage.setOpaque(true);
		
		lblSwipeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		/* This label is set empty here, so that after the card number entry is authenticated, if entry is wrong, this will display error message, if success, nothing
		 * cos, next page will be the member main menu*/
		swipe_auth_message = new JLabel("");
		swipe_auth_message.setHorizontalAlignment(SwingConstants.CENTER);
		swipe_auth_message.setFont(new Font("Lucida Grande", Font.ITALIC, 13));
		swipe_auth_message.setForeground(Color.RED);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.goBackToMainPage(mainFrame.getTrackedCurrentPage());
			}
		});
		btnCancel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(56, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblHelloMember, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
							.addGap(96))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblSwipeMessage, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
							.addGap(86))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(swipe_auth_message, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
							.addGap(38))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCancel)
							.addGap(24))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblHelloMember)
					.addGap(67)
					.addComponent(lblSwipeMessage, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(swipe_auth_message, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setBorder(new LineBorder(Color.black, 5));
		this.setSize(526, 337);
	}
}
