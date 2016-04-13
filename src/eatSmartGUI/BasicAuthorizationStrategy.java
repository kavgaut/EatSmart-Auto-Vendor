package eatSmartGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;

public class BasicAuthorizationStrategy implements AuthorizationStrategy{
	public MainPageGUI mainPageGUI;
	public BasicAuthorizationStrategy(MainPageGUI mainFrame){
		this.mainPageGUI = mainFrame;
	}
	
	public void authorizeFailure(){
		mainPageGUI.loginChkLbl.setForeground(Color.RED);
		mainPageGUI.loginChkLbl.setHorizontalAlignment(SwingConstants.CENTER);
		mainPageGUI.loginChkLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPageGUI.loginChkLbl.setText("UserName or Password is incorrect. Please login again");
	}
}