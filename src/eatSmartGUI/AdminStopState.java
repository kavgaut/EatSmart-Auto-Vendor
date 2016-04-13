package eatSmartGUI;

public class AdminStopState implements State{
	MainPageGUI mainPage;
	
	public AdminStopState(MainPageGUI mainFrame){
		this.mainPage = mainFrame;
	}
	
	public void doAction(RemoteUserRole remoteUser){
		remoteUser.setState(this);
		mainPage.goBackToAdminLoginPage();
	}
	
}