package eatSmartGUI;

import java.util.ArrayList;

public class Login{
	AuthorizationStrategy strategy;
	ArrayList<String> userDetailsList;
	public MainPageGUI mainpageGui;
	public static RemoteUserRole adminRole;
	
	public Login(AuthorizationStrategy strategyA, MainPageGUI mainFrame){
		this.strategy = strategyA;
		this.mainpageGui = mainFrame;
	}
	
	public void authenticateUser(String usrId, String usrPassword){
		UserRoleDaoImpl usrroledao = new UserRoleDaoImpl();
		userDetailsList = usrroledao.getUserInfoById(usrId);
		UserRole userRole = new UserRole();
		String uId, uPwd;
		//uId = userDetailsList.get(0);
		//uPwd = userDetailsList.get(1);
		userRole.setUserId(userDetailsList.get(0));
		userRole.setUserPassword(userDetailsList.get(1));
		userRole.setUserRole(userDetailsList.get(2));
		userRole.setUserName(userDetailsList.get(3));
		if(UserRoleDaoImpl.loginResultFlag == true){
			UserRoleDaoImpl.loginResultFlag = false;
			if(userRole.getUserId().equals(usrId)){
				if(userRole.getUserPassword().equals(usrPassword)){
					Logger logger = Logger.getInstance();
					logger.logUserInfo(usrId);
					adminRole = new AdminRole();
					State adminStrtState = new AdminStartState();
					adminStrtState.doAction(adminRole);
					mainpageGui.loginChkLbl.setText("");
					mainpageGui.createMSAdminMainPage(userRole.getUserId(),userRole.getUserName());
				}		
				else {
				strategy.authorizeFailure();
				}
			}
		}
		else{
			strategy.authorizeFailure();
		}
		
	}
}