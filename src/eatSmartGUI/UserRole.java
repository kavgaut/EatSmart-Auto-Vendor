package eatSmartGUI;

public class UserRole{
	String userId;
	String userPassword;
	String userRole;
	String userName;
	
	public void setUserId(String usrId){
		this.userId = usrId;
	}
	
	public String getUserId(){
		return userId;
	}
	
	public void setUserPassword(String usrPwd){
		this.userPassword = usrPwd;
	}
	
	public String getUserPassword(){
		return userPassword;
	}
	
	public void setUserRole(String userRole){
		this.userRole = userRole;
	}
	
	public String getUserRole(){
		return userRole;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public String getUserName(){
		return userName;
	}
}